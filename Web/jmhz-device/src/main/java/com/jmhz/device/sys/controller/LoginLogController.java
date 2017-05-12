package com.jmhz.device.sys.controller;

import com.jmhz.device.controller.BaseController;
import com.jmhz.device.sys.entity.LoginLog;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.sys.service.ILoginLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * <p> .
 * <p> Created at 2014/4/20 11:48
 *
 * @author Charkey
 * @version 1.0
 */
@Controller
@RequestMapping("/audit")
public class LoginLogController extends BaseController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ILoginLogService loginLogService;

    @RequiresPermissions("audit:view")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "system");
        model.addAttribute("subMenu", "audit");
        return "/jsp/system/audit/index.jsp";
    }

    @ResponseBody
    @RequiresPermissions("audit:view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonModel list(int page, int rows, Model model) {
        List<LoginLog> loginLogList = loginLogService.getAllLoginLog(page, rows, "false", "");
        Long count = loginLogService.countAll();
        int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        jsonModel.setDataObj(loginLogList);
        jsonModel.setPagerows(rows);
        jsonModel.setCurrentpage(page);
        jsonModel.setTotalpages(pages);
        jsonModel.setTotalrecords(count.intValue());
        return jsonModel;
    }

    @RequiresPermissions("audit:delete")
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public JsonModel edit(@RequestParam("id") int id, @RequestParam("oper") String oper) {
        if (oper.equals("del")) {

        }
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }


}
