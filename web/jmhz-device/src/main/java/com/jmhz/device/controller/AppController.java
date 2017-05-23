package com.jmhz.device.controller;

import com.jmhz.device.backModel.AppBack;
import com.jmhz.device.model.Tapp;
import com.jmhz.device.service.AppServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/app")
public class AppController extends BaseController {

    @Autowired
    private AppServiceI appService;


    @ResponseBody
    @RequestMapping(value = "/getStartAndroidAppInfo")
    public AppBack checkForUpdates(){

        AppBack appBack = new AppBack();
        Tapp tapp = appService.getStartAndroidAppInfo();
        appBack.setTapp(tapp);
        return appBack;
    }
}
