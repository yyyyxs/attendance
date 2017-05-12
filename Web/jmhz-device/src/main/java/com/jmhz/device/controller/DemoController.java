package com.jmhz.device.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p> .
 * <p> Created at 2014/4/27 23:04
 *
 * @author Charkey
 * @version 1.0
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @RequiresPermissions("demo:view")
    @RequestMapping("/index")
    public String index() {
        return "/jsp/demo/index.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/appendResource")
    public String appendResource() {
        return "/jsp/demo/appendResource.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/blank")
    public String blank() {
        return "/jsp/demo/blank.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/jqgrid")
    public String jqgrid() {
        return "/jsp/demo/jqgrid.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/jqueryValidate")
    public String jqueryValidate() {
        return "/jsp/demo/jqueryValidate.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/jspTemplate")
    public String jspTemplate() {
        return "/jsp/demo/jsp-template.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/login")
    public String login() {
        return "/jsp/demo/login.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/mqcard")
    public String mqcard() {
        return "/jsp/demo/mqcard.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/mqgroupcard")
    public String mqgroupcard() {
        return "/jsp/demo/mqgroupcard.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/mqgroupstatus")
    public String mqgroupstatus() {
        return "/jsp/demo/mqgroupstatus.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/mqstatus")
    public String mqstatus() {
        return "/jsp/demo/mqstatus.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/test")
    public String test() {
        return "/jsp/demo/test.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/zTree")
    public String zTree() {
        return "/jsp/demo/zTree.jsp";
    }

    @RequiresPermissions("demo:view")
    @RequestMapping("/villageInfo")
    public String villageInfo() {
        return "/jsp/demo/villageInfo.jsp";
    }

    @RequestMapping("/index/new")
    public String indexNew() {
        return "/jsp/system/indexNew.jsp";
    }

}
