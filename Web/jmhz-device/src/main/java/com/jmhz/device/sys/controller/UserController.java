package com.jmhz.device.sys.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.controller.BaseController;
import com.jmhz.device.model.device;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.sys.bind.annotation.CurrentUser;
import com.jmhz.device.sys.entity.Organization;
import com.jmhz.device.sys.entity.Role;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.sys.pageEntity.UserInfo;
import com.jmhz.device.sys.service.IOrganizationService;
import com.jmhz.device.sys.service.IRoleService;
import com.jmhz.device.sys.service.IUserService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p> .
 * <p> Created at 2014/4/20 11:48
 *
 * @author Charkey
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private IRoleService roleService;


    @RequestMapping(value = "/studentInfo", method = RequestMethod.GET)
    public String studentInfoShow(Model model, HttpServletRequest request) {
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        Long teacherId = new Long(user.getTeacherId());
        User teacher = userService.findOne(teacherId);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("teacher", teacher);
        model.addAttribute("navMenu", "studentInfo");
        model.addAttribute("subMenu", "studentInfoLook");
        return "/jsp/info/studentInfoShow.jsp";
    }

    @RequestMapping(value = "/teacherInfo", method = RequestMethod.GET)
    public String teachertInfoShow(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "teacherInfo");
        model.addAttribute("subMenu", "teacherInfoLook");
        return "/jsp/info/teacherInfoShow.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/liststudent", method = RequestMethod.GET)
    public JsonModel getAllstudent(String page, String rows, String _search, String filters, HttpSession session, HttpServletRequest request) {
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        int userId = user.getId().intValue();
        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            List<User> users;
            users = userService.findAll(Integer.parseInt(page), Integer.parseInt(rows), userId);
            Long count = userService.getCount(userId);
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(users);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");


        } else {
            //查询
            session.setAttribute("farmerFilters", filters);
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            List<User> users = new ArrayList<User>();
            //users = userService.findAll(Integer.parseInt(page), Integer.parseInt(rows), userId);
            users = userService.findAll(Integer.parseInt(page), Integer.parseInt(rows), userId, filtersClass);
            //Long count = userService.getCount(userId);
            Long count = userService.getCount(filtersClass, userId);
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(users);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }
        return jsonModel;
    }


    @RequestMapping(value = "/showUser", method = RequestMethod.GET)
    public String showUser(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "user");
        model.addAttribute("subMenu", "userView");
        return "/jsp/system/user/index.jsp";
    }



    @ResponseBody
    @RequiresPermissions("user:view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonModel list(String page, String rows, Model model) {

        JsonModel jsonModel = new JsonModel();
        List<User> users;
        users = userService.findAll(Integer.parseInt(page), Integer.parseInt(rows));
        Long count = userService.getCount();
        int pages = count.intValue() / Integer.parseInt(rows) + 1;
        jsonModel.setSuccess(true);
        jsonModel.setMsg("success");
        jsonModel.setDataObj(users);
        jsonModel.setTotalpages(pages);
        jsonModel.setCurrentpage(Integer.parseInt(page));
        jsonModel.setTotalrecords(count.intValue());
        jsonModel.setPagerows(Integer.parseInt(rows));
        jsonModel.setSort("");
        jsonModel.setOrder("");

        return jsonModel;
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        List<Role> roleList = roleService.findAll();
        List<User> userList = userService.findTeacher();
        List<Organization> organizationList = organizationService.findAll();
        model.addAttribute("roleList", roleList);
        model.addAttribute("userList", userList);
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("currentUser", new User());
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "user");
        model.addAttribute("subMenu", "userCreate");
        return "/jsp/system/user/create.jsp";
    }
    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(User user, RedirectAttributes redirectAttributes) {
        if (userService.findByUsername(user.getUsername()) == null) {//用户名尚未存在
            if (user.getLocked()) {
                user.setLocked(false);
            } else {
                user.setLocked(true);
            }
            userService.createUser(user);
            redirectAttributes.addFlashAttribute("msg", "新增成功");
            return "redirect:/user/create";
        }
        //用户名已存在
        redirectAttributes.addFlashAttribute("msg", "添加失败：用户名已存在！请重新添加！");
        return "redirect:/user/create";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        //处理role
        List<Role> roleList = roleService.findAll();
        Map<Long, String> roleMap = new HashMap<Long, String>();
        for (Role role : roleList) {
            roleMap.put(role.getId(), role.getRole());
        }//结束

        //处理organization
        List<Organization> organizationList = organizationService.findAll();
        Map<Long, String> organizationMap = new HashMap<Long, String>();
        for (Organization organization : organizationList) {
            organizationMap.put(organization.getId(), organization.getName());
        }//结束

        model.addAttribute("roleList", roleList);
        model.addAttribute("organizationList", organizationList);
        User user = userService.findOne(id);
        if (user.getUsername().equals("simastudio")) {
            redirectAttributes.addFlashAttribute("errorMsg", "不能修改系统维护管理员！");
            return "redirect:/user/showUser";
        }
        model.addAttribute("roleIds", user.getRoleIds().split(","));
        UserInfo currentUser = new UserInfo();
        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setTel(user.getTel());
        currentUser.setLocked(user.getLocked());
//        currentUser.setOrganizationId(user.getOrganizationId());
        currentUser.setCity(user.getCity());
        currentUser.setTown(user.getTown());
        currentUser.setAuth_level(user.getAuth_level());
        //设置组织信息
//        currentUser.setOrgName(organizationMap.get(user.getOrganizationId()));
        String[] roleIds = user.getRoleIds().split(",");
        StringBuilder roleNames = new StringBuilder();
        for (String roleId : roleIds) {
            roleNames.append(roleMap.get(Long.parseLong(roleId)) + ",");
        }
        String names = roleNames.toString().substring(0, roleNames.length() - 1);
        currentUser.setRoleNames(names);

        model.addAttribute("currentUser", currentUser);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "user");
        model.addAttribute("subMenu", "userView");
        return "/jsp/system/user/edit.jsp";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@CurrentUser User curUser, UserInfo userInfo, @RequestParam(value = "roleIds", required = false) String roleIds,
                         RedirectAttributes redirectAttributes) {
        User user = userService.findOne(userInfo.getId());

        user.setUsername(userInfo.getUsername());
        user.setRoleIds(roleIds == null ? user.getRoleIds() : roleIds);
//        user.setOrganizationId(userInfo.getOrganizationId());
        user.setTel(userInfo.getTel());
        user.setCity(userInfo.getCity());
        user.setTown(userInfo.getTown());
        user.setAuth_level(userInfo.getAuth_level());
        if (userInfo.getLocked()) {
            user.setLocked(false);
        } else {
            user.setLocked(true);
        }
        userService.updateUser(user);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/user/showUser";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("currentUser", userService.findOne(id));
        model.addAttribute("op", "删除");
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "user");
        model.addAttribute("subMenu", "userView");
        return "/jsp/system/user/del.jsp";
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteUser(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/user/showUser";
    }


    @RequiresPermissions("user:update")
    @RequestMapping(value = "/changePassword/{id}", method = RequestMethod.GET)
    public String showChangePasswordForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("currentUser", userService.findOne(id));
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "user");
        model.addAttribute("subMenu", "userView");
        return "/jsp/system/user/changePwd.jsp";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/changePassword/{id}", method = RequestMethod.POST)
    public String changePassword(@PathVariable("id") Long id,
                                 @RequestParam("newPassword") String newPassword, RedirectAttributes redirectAttributes) {
        //String uuid = UUID.randomUUID();
        userService.changePassword(id, newPassword);
        redirectAttributes.addFlashAttribute("msg", "修改密码成功");
        return "redirect:/user/showUser";
    }

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/inLineModify", method = RequestMethod.POST)
    public String inLineModify(@RequestParam("id") Long id,
                               @RequestParam("username") String username,
                               @RequestParam("tel") String tel,
                               @RequestParam("locked") boolean locked,
                               @RequestParam("oper") String oper) {
        if (oper.equals("edit")) {
            User user = userService.findOne(id);
            user.setUsername(username);
            user.setTel(tel);
            user.setLocked(locked);
            userService.updateUser(user);
        }
        return "redirect:/user/showUser";
    }


}

