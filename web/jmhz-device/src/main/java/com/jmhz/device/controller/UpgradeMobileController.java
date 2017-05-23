package com.jmhz.device.controller;

import com.jmhz.device.backModel.*;
import com.jmhz.device.model.*;
import com.jmhz.device.service.upgradeServiceI;
import com.jmhz.device.service.*;
import com.jmhz.device.util.ErrorCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/mobileupgrade")
public class UpgradeMobileController extends BaseController {

    /**
     * 分页配置
     */
    public final static String curPageDefaultValue = "1";
    public final static String curRowsDefaultValue = "5";

    @Autowired
    private upgradeServiceI upgradeService;
    @Autowired
    private deviceServiceI deviceService;



    @ResponseBody
    @RequestMapping(value = "/addUpgrade", method = RequestMethod.POST)//升级申报接口
    public UpgradeAddBack addUpgrade(@RequestParam(value = "deviceId") int deviceId,
                                     @RequestParam(value = "userId") int userId,
                                     @RequestParam(value = "deviceName") String deviceName,
                                     @RequestParam(value = "deviceType") String deviceType,
                                     @RequestParam(value = "deviceUser") String deviceUser,
                                     @RequestParam(value = "applytime") String applytime,
                                     @RequestParam(value = "upgradeDescribe") String upgradeDescribe
    ) {
        upgradeApply upgradeApplys = new upgradeApply();
        upgradeApplys.setDeviceId(deviceId);
        upgradeApplys.setUserId(userId);
        upgradeApplys.setDeviceName(deviceName);
        upgradeApplys.setDeviceType(deviceType);
        upgradeApplys.setDeviceUser(deviceUser);
        upgradeApplys.setApplytime(applytime);
        upgradeApplys.setUpgradeDescribe(upgradeDescribe);
        System.out.print(upgradeApplys);
        int said = upgradeService.addupgrade(upgradeApplys);//asfasf
        UpgradeAddBack upgradeAddBack = new UpgradeAddBack();
        if (said >= 0) {
            upgradeAddBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            upgradeAddBack.setSuccess(false);
            upgradeAddBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return upgradeAddBack;

    }

    @ResponseBody
    @RequestMapping(value = "/getupgradelistbyuser")//获得设备维修申请记录列表,普通用户
    public UpgradeBack getupgradelistbyuser(@RequestParam("userId") int userId,
                                        @RequestParam("approve") String approve,
                                        @RequestParam(value = "page", defaultValue = curPageDefaultValue) String page,
                                        @RequestParam(value = "rows", defaultValue = curRowsDefaultValue) String rows
    ) {
       UpgradeBack upgradeBack = new UpgradeBack();
        List<upgradeApply> applys = upgradeService.getAllapplyByapprove(page, rows, userId, approve);
        if (applys.size() != 0) {
            int curpage = Integer.parseInt(page);
            int currows = Integer.parseInt(rows);
            upgradeBack.setTotal(upgradeService.getCountByapprove(userId, approve).intValue());
            upgradeBack.setCurPage(curpage);
            upgradeBack.setCurRows(currows);
            upgradeBack.setCurPageCount(applys.size());
            upgradeBack.setApplys(applys);
            upgradeBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            upgradeBack.setSuccess(false);
            upgradeBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return upgradeBack;
    }

    @ResponseBody
    @RequestMapping(value = "/getupgradelistbymanager")//获得设备维修申请记录列表,管理员
    public UpgradeBack getupgradelistbymanagaer(@RequestParam("approve") String approve,
                                            @RequestParam(value = "page", defaultValue = curPageDefaultValue) String page,
                                            @RequestParam(value = "rows", defaultValue = curRowsDefaultValue) String rows
    ) {
        UpgradeBack upgradeBack = new UpgradeBack();
        List<upgradeApply> applys = upgradeService.getAllapplyByapprove(page, rows, approve);
        if (applys.size() != 0) {
            int curpage = Integer.parseInt(page);
            int currows = Integer.parseInt(rows);
            upgradeBack.setTotal( upgradeService.getCountByapprove(approve).intValue());
            upgradeBack.setCurPage(curpage);
            upgradeBack.setCurRows(currows);
            upgradeBack.setCurPageCount(applys.size());
            upgradeBack.setApplys(applys);
            upgradeBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            upgradeBack.setSuccess(false);
            upgradeBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return upgradeBack;
    }

    @ResponseBody
    @RequestMapping(value = "/approveopinion")//管理员审核，给出审核意见
    public UpgradeBack approveopinion(@RequestParam("id") int id,
                                    @RequestParam("status") String status,
                                    @RequestParam("approve") String approve,
                                    @RequestParam("approveRemark") String approveRemark
    ) {
        UpgradeBack upgradeBack = new UpgradeBack();
        upgradeApply apply = upgradeService.getapplyById(id);
        apply.setStatus(status);
        apply.setApprove(approve);
        apply.setApproveRemark(approveRemark);
        boolean fid = upgradeService.update(apply);
        if (fid) {
            upgradeBack.setSuccess(true);
            upgradeBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            upgradeBack.setSuccess(false);
            upgradeBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return upgradeBack;
    }


    @ResponseBody
    @RequestMapping(value = "/addupgradelog")//添加升级报告
    public UpgradeBack addlog(@RequestParam("applyId") int applyId,
                            @RequestParam("deviceName") String deviceName,
                            @RequestParam("deviceUser") String deviceUser,
                            @RequestParam("deviceType") String deviceType,
                            @RequestParam("dealStatus") String dealStatus,
                            @RequestParam("cost") String cost,
                            @RequestParam("upgradepart") String upgradepart,
                            @RequestParam("upgradeResult") String upgradeResult
    ) {
       UpgradeBack upgradeBack = new UpgradeBack();
        upgraderepair log = new upgraderepair();
        log.setApplyId(applyId);
        log.setDeviceName(deviceName);
        log.setDeviceUser(deviceUser);
        log.setDealStatus(dealStatus);
        log.setCost(cost);
        log.setUpgradepart(upgradepart);
        log.setupgradeResult(upgradeResult);

        int fid = upgradeService.addlog(log);
        upgradeApply apply = upgradeService.getapplyById(applyId);
        apply.setLogmark(1);
        upgradeService.update(apply);

        if (fid >= 0) {
            upgradeBack.setSuccess(true);
            upgradeBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            upgradeBack.setSuccess(false);
            upgradeBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return upgradeBack;
    }

    @ResponseBody
    @RequestMapping(value = "/showlog")//显示升级报告
    public UpgradeAddBack showlog(@RequestParam("applyId") int applyId) {
        UpgradeAddBack upgradeAddBack = new UpgradeAddBack();
        upgraderepair log = upgradeService.getlogByapplyID(applyId);
        upgradeAddBack.setLog(log);
        if (log != null) {
            upgradeAddBack.setSuccess(true);
            upgradeAddBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            upgradeAddBack.setSuccess(false);
            upgradeAddBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return upgradeAddBack;
    }
}