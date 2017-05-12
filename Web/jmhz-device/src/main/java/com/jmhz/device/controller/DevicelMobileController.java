package com.jmhz.device.controller;

import com.jmhz.device.backModel.*;
import com.jmhz.device.model.device;
import com.jmhz.device.model.Tappeal;
import com.jmhz.device.model.Tappealrate;
import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.service.AppealRateServiceI;
import com.jmhz.device.service.deviceServiceI;
import com.jmhz.device.service.AppealServiceI;
import com.jmhz.device.service.FarmerServiceI;
import com.jmhz.device.service.GroupServiceI;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.util.ErrorCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lw on 2015-04-10.
 */
@Controller
@RequestMapping("/mobiledevicemg")
public class DevicelMobileController extends BaseController {

    /**
     * 分页配置
     */
    public final static String curPageDefaultValue = "1";
    public final static String curRowsDefaultValue = "5";

    @Autowired
    private deviceServiceI deviceService;
    @Autowired
    private AppealServiceI appealService;
    @Autowired
    private FarmerServiceI farmerService;
    @Autowired
    private AppealRateServiceI appealRateService;

    @ResponseBody
    @RequestMapping(value = "/getmydevicebyUUID")//根据UUID返回单个设备
    public DevicesBack getDevicebyUUID(@RequestParam("UUID") String UUID) {
        device devices = new device();
        DevicesBack devicesBack = new DevicesBack();
        devices=deviceService.getdeviceByuuid(UUID);
        if(devices!=null)
        {
            devicesBack.setDevice(devices);
            devicesBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        }else
        {
            devicesBack.setSuccess(false);
            devicesBack.setErrcode(ErrorCodeUtils.EC_PARAMETERS_OUTOFBOUND);
        }

        return devicesBack;
    }
    @ResponseBody
    @RequestMapping(value = "/getmydevicebyID")//根据ID返回单个设备
    public DevicesBack getDevicebyID(@RequestParam("id") int id) {
        device devices = new device();
        DevicesBack devicesBack = new DevicesBack();
        devices=deviceService.getdeviceById(id);
        if(devices!=null)
        {
            devicesBack.setDevice(devices);
            devicesBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        }else
        {
            devicesBack.setSuccess(false);
            devicesBack.setErrcode(ErrorCodeUtils.EC_PARAMETERS_OUTOFBOUND);
        }

        return devicesBack;
    }

    @ResponseBody                   //返回该用户id的所有设备，page是当前页数，rows每页显示条数
    @RequestMapping(value = "/getmydevicebyuserId")
    public DeviceBack getmydevice(@RequestParam(value = "userId") int userId,
                                  @RequestParam(value = "page", defaultValue = curPageDefaultValue) String page,
                                  @RequestParam(value = "rows", defaultValue = curRowsDefaultValue) String rows) {

        DeviceBack deivceBack = new DeviceBack();
        List<device> devices = deviceService.getAlldevice(page, rows, userId);
        if(devices.size()!=0)
        {
            int curpage = Integer.parseInt(page);
            int currows = Integer.parseInt(rows);
            deivceBack.setTotal(deviceService.getCount(userId).intValue());
            deivceBack.setCurPage(curpage);
            deivceBack.setCurRows(currows);
            deivceBack.setCurPageCount(devices.size());
            deivceBack.setDevices(devices);
        }
        else {
            deivceBack.setSuccess(false);
            deivceBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return deivceBack;
    }

    @ResponseBody                   //管理员，查看所有设备,参数同上
    @RequestMapping(value = "/getalldevice")
    public DeviceBack getALLdevice(
                                  @RequestParam(value = "page", defaultValue = curPageDefaultValue) String page,
                                  @RequestParam(value = "rows", defaultValue = curRowsDefaultValue) String rows) {

        DeviceBack deivceBack = new DeviceBack();
        List<device> devices = deviceService.getAlldevice(page, rows);
        if(devices.size()!=0){
            int curpage = Integer.parseInt(page);
            int currows = Integer.parseInt(rows);
            deivceBack.setTotal(deviceService.getCount().intValue());
            deivceBack.setCurPage(curpage);
            deivceBack.setCurRows(currows);
            deivceBack.setCurPageCount(devices.size());
            deivceBack.setDevices(devices);
        }else {
            deivceBack.setSuccess(false);
            deivceBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return deivceBack;
    }

    @ResponseBody                   //返回该用户id的所有设备，page是当前页数，rows每页显示条数
    @RequestMapping(value = "/getdeviceByCondition")
    public DeviceBack getdeviceByCondition(@RequestParam(value = "condition1") String condition1,
                                           @RequestParam(value = "condition2") String condition2,
                                           @RequestParam(value = "page", defaultValue = curPageDefaultValue) String page,
                                           @RequestParam(value = "rows", defaultValue = curRowsDefaultValue) String rows) {

        DeviceBack deivceBack = new DeviceBack();
        List<device> devices = deviceService.getdeviceByCondition(condition1, condition2, page, rows);
        if (devices.size() != 0) {
            int curpage = Integer.parseInt(page);
            int currows = Integer.parseInt(rows);
            deivceBack.setTotal(deviceService.getCountByCondition(condition1, condition2).intValue());
            deivceBack.setCurPage(curpage);
            deivceBack.setCurRows(currows);
            deivceBack.setCurPageCount(devices.size());
            deivceBack.setDevices(devices);
        }else {
            deivceBack.setSuccess(false);
            deivceBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }


        return deivceBack;
    }

    @ResponseBody//更新（用户权限下）
    @RequestMapping(value = "/updatebyuser", method = RequestMethod.POST)
    public DeviceBack updateDevice(@RequestParam("id") int id,
                                   @RequestParam("position") String position,
                                   @RequestParam("internalMemory") String internalMemory,
                                   @RequestParam("graphicsCard") String graphicsCard,
                                   @RequestParam("otherInfo") String otherInfo) {
        DeviceBack deviceBack = new DeviceBack();
        device device = deviceService.getdeviceById(id);
        device.setPosition(position);
        device.setInternalMemory(internalMemory);
        device.setGraphicsCard(graphicsCard);
        device.setOtherInfo(otherInfo);
        boolean fid = deviceService.update(device);
        if (fid) {
            deviceBack.setSuccess(true);
        } else {
            deviceBack.setSuccess(false);
            deviceBack.setErrcode(ErrorCodeUtils.EC_PARAMETERS_OUTOFBOUND);

        }
        return deviceBack;

    }

    @ResponseBody//更新（管理员权限下）
    @RequestMapping(value = "/updatebymanager", method = RequestMethod.POST)
    public DeviceBack updateDevice(@RequestParam("id") int id,
                                   @RequestParam("position") String position,
                                   @RequestParam("deviceUser") String deviceUser,
                                   @RequestParam("internalMemory") String internalMemory,
                                   @RequestParam("graphicsCard") String graphicsCard,
                                   @RequestParam("otherInfo") String otherInfo) {
        DeviceBack deviceBack = new DeviceBack();
        device device = deviceService.getdeviceById(id);
        device.setPosition(position);
        device.setDeviceUser(deviceUser);
        device.setInternalMemory(internalMemory);
        device.setGraphicsCard(graphicsCard);
        device.setOtherInfo(otherInfo);
        boolean fid = deviceService.update(device);
        if (fid) {
            deviceBack.setSuccess(true);
        } else {
            deviceBack.setSuccess(false);
            deviceBack.setErrcode(ErrorCodeUtils.EC_PARAMETERS_OUTOFBOUND);

        }
        return deviceBack;

    }


    @ResponseBody
    @RequestMapping(value = "/DeleltebyID", method = RequestMethod.POST)
    public DevicesBack DeleltebyID(@RequestParam("id") int id) {
        device devices;
        DevicesBack devicesBack = new DevicesBack();
        devices = deviceService.getdeviceById(id);
        devices.setDeleteFlag(1);
        boolean fid = deviceService.update(devices);
        if (fid) {
            devicesBack.setSuccess(true);
        } else {
            devicesBack.setSuccess(false);
            devicesBack.setErrcode(ErrorCodeUtils.EC_PARAMETERS_OUTOFBOUND);
        }

        return devicesBack;
    }



}
