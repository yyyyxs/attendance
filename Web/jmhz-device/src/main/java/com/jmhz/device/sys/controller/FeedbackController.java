package com.jmhz.device.sys.controller;

import com.jmhz.device.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p> .
 * <p> Created at 2014/4/28 18:47
 *
 * @author Charkey
 * @version 1.0
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseController {

    @RequestMapping
    public String index(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "feedback");
        model.addAttribute("subMenu", "feedbackAll");
        return "/jsp/system/feedback/feedback.jsp";
    }
}
