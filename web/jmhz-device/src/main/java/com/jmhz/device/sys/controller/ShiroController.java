package com.jmhz.device.sys.controller;

import com.jmhz.device.Constants;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.sys.bind.annotation.CurrentUser;
import com.jmhz.device.sys.entity.LoginLog;
import com.jmhz.device.sys.entity.Resource;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.sys.service.ILoginLogService;
import com.jmhz.device.sys.service.IResourceService;
import com.jmhz.device.sys.service.IUserService;
import com.jmhz.device.util.BrowserInfoUtil;
import com.jmhz.device.util.IpUtil;
import com.jmhz.device.util.OSInfoUtil;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ShiroController {

    @Autowired
    private IResourceService resourceService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ILoginLogService loginLogService;


    private static final Logger logger = Logger.getLogger(ShiroController.class);

    @RequestMapping(value = {"/{login:login;?.*}"}, method = RequestMethod.GET)
    public String loginForm(HttpServletRequest request, ModelMap model) {
        logger.info(System.getProperty("gaoqiaowebapp.root"));
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);

        //登录失败了 提取错误消息
        Exception shiroLoginFailureEx =
                (Exception) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        if (shiroLoginFailureEx != null) {
            model.addAttribute(Constants.ERROR, shiroLoginFailureEx.getMessage());
        }
//        if (SecurityUtils.getSubject().isAuthenticated()) {
//            return "redirect:/";
//        }
        //如果用户直接到登录页面 先退出一下
        //原因：isAccessAllowed实现是subject.isAuthenticated()---->即如果用户验证通过 就允许访问
        // 这样会导致登录一直死循环
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && subject.isAuthenticated()) {
            subject.logout();
        }


        //如果同时存在错误消息 和 普通消息  只保留错误消息
        if (model.containsAttribute(Constants.ERROR)) {
            model.remove(Constants.MESSAGE);
        }


        return "/jsp/system/loginNew.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonModel loginPost(User currUser, HttpSession session, Model model, HttpServletRequest request) throws Exception {

        JsonModel jsonModel = new JsonModel();
        //TODO 是否进行邮箱验证的判断
        Subject user = SecurityUtils.getSubject();
        logger.debug("In Method login post");
        UsernamePasswordToken token = new UsernamePasswordToken(currUser.getUsername(), currUser.getPassword());
        String rememberme = request.getParameter("rememberMe");
        if (org.apache.commons.lang3.StringUtils.isNotBlank(rememberme)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        try {
            //会调用 shiroDbRealm 的认证方法 org.springrain.frame.shiro.ShiroDbRealm.doGetAuthenticationInfo(AuthenticationToken)
            user.login(token);
        } catch (UnknownAccountException uae) {
            jsonModel.setMsg("用户不存在");
            jsonModel.setSuccess(false);
            logger.debug("jsonModel " + jsonModel.toString());
            return jsonModel;
//            model.addAttribute("message", "用户名或密码错误！");
//            return "/jsp/system/loginNew.jsp";
        } catch (IncorrectCredentialsException ice) {
            jsonModel.setMsg("用户名或密码错误！");
            jsonModel.setSuccess(false);
            logger.debug("jsonModel " + jsonModel.toString());
            return jsonModel;
//            model.addAttribute("message", "用户名或密码错误！");
//            return "/jsp/system/loginNew.jsp";
        } catch (LockedAccountException lae) {

            jsonModel.setMsg("账号被锁定！");
            jsonModel.setSuccess(false);
            logger.debug("jsonModel " + jsonModel.toString());
            return jsonModel;
//            model.addAttribute("message", "账号被锁定!");
//            return "/jsp/system/loginNew.jsp";
        } catch (ExcessiveAttemptsException eae) {
            jsonModel.setMsg("登录失败次数过多！请5分钟后再试！！");
            jsonModel.setSuccess(false);
            logger.debug("jsonModel " + jsonModel.toString());
            return jsonModel;
//            model.addAttribute("message", "登录失败次数过多！请5分钟后再试！");
//            return "/jsp/system/loginNew.jsp";
        } catch (Exception e) {
            jsonModel.setMsg("未知错误,请联系管理员！");
            jsonModel.setSuccess(false);
            logger.debug("jsonModel " + jsonModel.toString());
            logger.debug("Exception " + e.toString());
            logger.debug(e);
            return jsonModel;
//            model.addAttribute("message", "未知错误,请联系管理员.");
//            return "/jsp/system/loginNew.jsp";
        }

        String IP = IpUtil.getIpAddr(request);
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(IP);
        loginLog.setUser(currUser.getUsername());
        loginLog.setIpinfo(IpUtil.getIpInfo(IP));
        loginLog.setUseragent(request.getHeader("user-agent"));
        loginLog.setBrowser(BrowserInfoUtil.getBrowserInfo(request));
        loginLog.setOsinfo(OSInfoUtil.getOperateSystemInfo(request));
        SimpleDateFormat sdf = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
        sdf.applyPattern("yyyy年MM月dd日 HH时mm分ss秒");
        loginLog.setLogintime(sdf.format(new Date()));
        loginLogService.loginLog(loginLog);

        //保存到会话中
        session.setAttribute(Constants.CURRENT_USER, userService.findByUsername(currUser.getUsername()));

        jsonModel.setMsg("登入成功");
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    @RequestMapping("/")
    public String index(@CurrentUser User loginUser, HttpSession session, Model model) {
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            logger.debug("!SecurityUtils.getSubject().isAuthenticated()");
            return "redirect:/login";
        }
        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<Resource> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "/jsp/system/index.jsp";
    }

    @RequestMapping("/index")
    public String indexBackup(@CurrentUser User loginUser, HttpSession session, Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/unauth")
    public String unauth(Model model) throws Exception {
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/login";
        }
        return "/unauth";

    }

}
