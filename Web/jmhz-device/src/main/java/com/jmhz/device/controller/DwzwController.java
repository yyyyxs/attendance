package com.jmhz.device.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright: Copyright (c) 2013-2015 SimaStudio
 * Usage:
 *
 * @author Charkey
 * @date 2015/5/11 20:43
 */
@Controller
@RequestMapping("/dwzw")
public class DwzwController extends BaseController {

    @RequestMapping("/dwgk")
    public String dwgk(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "dwzw");
        model.addAttribute("subMenu", "dwgk");
        return "/jsp/dwzw/dwgk/dwgk_send.jsp";
    }

    @RequestMapping("/dwxx")
    public String dwxx(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "dwzw");
        model.addAttribute("subMenu", "dwxx");
        return "/jsp/dwzw/dwxx/dwxx_view.jsp";    }

    @RequestMapping("/zwgk")
    public String zwgk(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "dwzw");
        model.addAttribute("subMenu", "zwgk");
        return "/jsp/dwzw/zwgk/zwgk_send.jsp";
    }

    @RequestMapping("/zwxx")
    public String zwxx(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "dwzw");
        model.addAttribute("subMenu", "zwxx");
        return "/jsp/dwzw/zwxx/zwxx_view.jsp";
    }
}
