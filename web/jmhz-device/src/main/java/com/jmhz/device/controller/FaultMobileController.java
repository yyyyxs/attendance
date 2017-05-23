package com.jmhz.device.controller;

import com.jmhz.device.backModel.FaultBack;
import com.jmhz.device.model.FaultApply;
import com.jmhz.device.model.Faultrepair;
import com.jmhz.device.service.*;
import com.jmhz.device.util.ErrorCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/mobilefault")
public class FaultMobileController extends BaseController {

    /**
     * 分页配置
     */
    public final static String curPageDefaultValue = "1";
    public final static String curRowsDefaultValue = "5";

    @Autowired
    private deviceServiceI deviceService;

    @Autowired
    private FaultServiceI faultService;


    @ResponseBody
    @RequestMapping(value = "/addfault", method = RequestMethod.POST)//维修申报接口
    public FaultBack addUpgrade(@RequestParam(value = "deviceId") int deviceId,
                                @RequestParam(value = "userId") int userId,
                                @RequestParam(value = "deviceName") String deviceName,
                                @RequestParam(value = "deviceType") String deviceType,
                                @RequestParam(value = "deviceUser") String deviceUser,
                                @RequestParam(value = "applytime") String applytime,
                                @RequestParam(value = "faultDescribe") String faultDescribe
    ) {
        FaultApply apply = new FaultApply();
        apply.setDeviceId(deviceId);
        apply.setUserId(userId);
        apply.setDeviceName(deviceName);
        apply.setDeviceType(deviceType);
        apply.setDeviceUser(deviceUser);
        apply.setApplytime(applytime);
        apply.setFaultDescribe(faultDescribe);
        int said = faultService.add(apply);
        FaultBack faultBack = new FaultBack();
        if (said >= 0) {
            faultBack.setSuccess(true);
            faultBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            faultBack.setSuccess(false);
            faultBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return faultBack;

    }
    @ResponseBody
    @RequestMapping(value = "/getfaultlistbyuser")//获得设备维修申请记录列表,普通用户
    public FaultBack getfaultlistbyuser(@RequestParam("userId") int userId,
                                  @RequestParam("approve") String approve,
                                  @RequestParam(value = "page", defaultValue = curPageDefaultValue) String page,
                                  @RequestParam(value = "rows", defaultValue = curRowsDefaultValue) String rows
    ) {
        FaultBack faultBack = new FaultBack();
        List<FaultApply> applys = faultService.getAllapplyByapprove(page, rows, userId, approve);
        if (applys.size() != 0) {
            int curpage = Integer.parseInt(page);
            int currows = Integer.parseInt(rows);
            faultBack.setTotal(faultService.getCountByapprove(userId, approve).intValue());
            faultBack.setCurPage(curpage);
            faultBack.setCurRows(currows);
            faultBack.setCurPageCount(applys.size());
            faultBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
            faultBack.setApplys(applys);
            faultBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            faultBack.setSuccess(false);
            faultBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return faultBack;
    }

    @ResponseBody
    @RequestMapping(value = "/getfaultlistbymanager")//获得设备维修申请记录列表,管理员
    public FaultBack getfaultlistbymanagaer(@RequestParam("approve") String approve,
                                  @RequestParam(value = "page", defaultValue = curPageDefaultValue) String page,
                                  @RequestParam(value = "rows", defaultValue = curRowsDefaultValue) String rows
    ) {
        FaultBack faultBack = new FaultBack();
        List<FaultApply> applys = faultService.getAllapplyByapprove(page, rows, approve);
        if (applys.size() != 0) {
            int curpage = Integer.parseInt(page);
            int currows = Integer.parseInt(rows);
            faultBack.setTotal(faultService.getCountByapprove(approve).intValue());
            faultBack.setCurPage(curpage);
            faultBack.setCurRows(currows);
            faultBack.setCurPageCount(applys.size());
            faultBack.setApplys(applys);
            faultBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            faultBack.setSuccess(false);
            faultBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return faultBack;
    }

    @ResponseBody
    @RequestMapping(value = "/approveopinion", method = RequestMethod.POST)//管理员审核，给出审核意见
    public FaultBack approveopinion(@RequestParam("id") int id,
                                    @RequestParam("status") String status,
                                    @RequestParam("approve") String approve,
                                    @RequestParam("approveRemark") String approveRemark
    ) {
        System.out.print("0022"+status);
        FaultBack faultBack = new FaultBack();
        FaultApply apply = faultService.getapplyById(id);
        apply.setStatus(status);
        apply.setApprove(approve);
        apply.setApproveRemark(approveRemark);
        boolean fid = faultService.update(apply);
        if (fid) {
            faultBack.setSuccess(true);
            faultBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            faultBack.setSuccess(false);
            faultBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return faultBack;
    }


    @ResponseBody
    @RequestMapping(value = "/addfaultlog", method = RequestMethod.POST)//添加维修报告
    public FaultBack addlog(@RequestParam("applyId") int applyId,
                            @RequestParam("deviceName") String deviceName,
                            @RequestParam("deviceUser") String deviceUser,
                            @RequestParam("deviceType") String deviceType,
                            @RequestParam("dealStatus") String dealStatus,
                            @RequestParam("cost") String cost,
                            @RequestParam("repairpart") String repairpart,
                            @RequestParam("repairResult") String repairResult
    ) {
        FaultBack faultBack = new FaultBack();
        Faultrepair log = new Faultrepair();
        log.setApplyId(applyId);
        log.setDeviceName(deviceName);
        log.setDeviceUser(deviceUser);
        log.setDealStatus(dealStatus);
        log.setCost(cost);
        log.setRepairpart(repairpart);
        log.setRepairResult(repairResult);

        int fid = faultService.addlog(log);
        FaultApply apply = faultService.getapplyById(applyId);
        apply.setLogmark(1);
        faultService.update(apply);

        if (fid >= 0) {
            faultBack.setSuccess(true);
            faultBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            faultBack.setSuccess(false);
            faultBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return faultBack;
    }


    @ResponseBody
    @RequestMapping(value = "/showlog")//显示维修报告
    public FaultBack showlog(@RequestParam("applyId") int applyId) {
        FaultBack faultBack = new FaultBack();
        Faultrepair log = faultService.getlogByapplyID(applyId);
        faultBack.setLog(log);
        if (log != null) {
            faultBack.setSuccess(true);
            faultBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        } else {
            faultBack.setSuccess(false);
            faultBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
        }
        return faultBack;
    }




}
