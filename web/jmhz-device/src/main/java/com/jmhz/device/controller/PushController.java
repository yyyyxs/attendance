package com.jmhz.device.controller;

import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.push.BroadcastMessagePushClient;
import com.jmhz.device.push.SingleMessagePushClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/push")
public class PushController {

    @RequestMapping(value = "/broadcast", method = RequestMethod.GET)
    public String broadcastMessagePushView(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "push");
        model.addAttribute("subMenu", "broadcastMsg");
        return "/jsp/push/broadcastmessage.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/broadcast", method = RequestMethod.POST)
    public JsonModel broadcastMessagePush(String title, String content) {
        boolean pushRes = BroadcastMessagePushClient.pushMessage(title, content);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(pushRes);
        jsonModel.setMsg("推送结果");
        return jsonModel;
    }

    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public String singleMessagePushView(Model model) {

        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "push");
        model.addAttribute("subMenu", "singleMsg");
        return "/jsp/push/singlemessage.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/single", method = RequestMethod.POST)
    public JsonModel singleMessagePush(Long channelId, String userId, String title, String content) {
        boolean pushRes = SingleMessagePushClient.pushMessage(channelId, userId, title, content);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(pushRes);
        jsonModel.setMsg("推送结果");
        return jsonModel;
    }

}
