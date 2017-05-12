package com.jmhz.device.controller;


import com.jmhz.device.backModel.StatisticBack;
import com.jmhz.device.backModel.StatisticsBack;
import com.jmhz.device.backModel.StatisticsYPBack;
import com.jmhz.device.service.*;
import com.jmhz.device.util.ErrorCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjfzuhqc on 2015/10/25.
 */
@Controller
@RequestMapping("/mobilestatistics")
public class StatisticsMobileController extends BaseController{
    @Autowired
    private deviceServiceI deviceService;
    @Autowired
    private FaultServiceI FaultService;

    @Autowired
    private UpdatesuccessServiceI UpdatesuccessService;

    @ResponseBody
    @RequestMapping(value = "/deviceStatistics")//需提供类型(年份或者地点),条件(具体年份和哪间实验室)
    public StatisticsBack getDeviceStatistics(@RequestParam(value="type") String type
            , @RequestParam(value="condition") String condition){
        StatisticsBack statisticsBack =new StatisticsBack();
        int unuseCount=0;
        int usedCount=0;
        int discardCount=0;
        int repairCount=0;
        int updateCount=0;
        if("year".equals(type))
        {
            unuseCount=deviceService.getYeartoCount(condition,"0");
            usedCount=deviceService.getYeartoCount(condition, "1");
            discardCount=deviceService.getYeartoCount(condition, "2");
            repairCount=deviceService.getYeartoCount(condition,"3");
            updateCount=deviceService.getYeartoCount(condition,"4");
        }else if("position".equals(type)) {
            unuseCount = deviceService.getPositiontoCount(condition, "0");
            usedCount = deviceService.getPositiontoCount(condition, "1");
            discardCount = deviceService.getPositiontoCount(condition, "2");
            repairCount=deviceService.getPositiontoCount(condition,"3");
            updateCount=deviceService.getPositiontoCount(condition,"4");
        }else
        {
            statisticsBack.setSuccess(false);
            statisticsBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
            return statisticsBack;
        }
        statisticsBack.setUnused(unuseCount);
        statisticsBack.setUsing(usedCount);
        statisticsBack.setDiscard(discardCount);
        statisticsBack.setMaintain(repairCount);
        statisticsBack.setUpdate(updateCount);
        statisticsBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        return statisticsBack;
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatistics")
    public StatisticBack getUpdateStatistics() {
        int wait = UpdatesuccessService.getCount("0");
        int being = UpdatesuccessService.getCount("1");
        int already = UpdatesuccessService.getCount("2");
        StatisticBack statisticBack =new StatisticBack();
        if(wait==0&&being==0&&already==0)
        {
            statisticBack.setSuccess(false);
            return statisticBack;
        }
        statisticBack.setWait(wait);
        statisticBack.setBeing(being);
        statisticBack.setAlready(already);
        statisticBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        return statisticBack;
    }

    @ResponseBody
    @RequestMapping(value = "/repairStatistics")
    public StatisticBack getRepairStatistics() {
        int wait = FaultService.getRepairCount("0");
        int being = FaultService.getRepairCount("1");
        int already = FaultService.getRepairCount("2");
        StatisticBack statisticBack =new StatisticBack();
        if(wait==0&&being==0&&already==0)
        {
            statisticBack.setSuccess(false);
            return statisticBack;
        }
        statisticBack.setWait(wait);
        statisticBack.setBeing(being);
        statisticBack.setAlready(already);
        statisticBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        return statisticBack;
    }
   /* @ResponseBody
    @RequestMapping(value = "/repairStatistics")
    public StatisticsBack getRepairStatistics() {
        int unsolvedCount = FaultService.getRepairCount("0");
        int doingCount = FaultService.getRepairCount("1");
        int finishCount = FaultService.getRepairCount("2");
        StatisticsBack statisticsBack =new StatisticsBack();
        if(unsolvedCount==0&&doingCount==0&&finishCount==0)
        {
            statisticsBack.setSuccess(false);
            return statisticsBack;
        }
        Statistics statisticDataUnupdate = new Statistics("未维修",unsolvedCount,"#FFB752");
        Statistics statisticDataUpdating = new Statistics("正在维修",doingCount,"#68BC31");
        Statistics statisticDataUpdated  = new Statistics("已维修",finishCount,"#AF4E96");
        ArrayList<Statistics> statisticDatas =new ArrayList<Statistics>();
        statisticDatas.add(statisticDataUnupdate);
        statisticDatas.add(statisticDataUpdating);
        statisticDatas.add(statisticDataUpdated);
        statisticsBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        statisticsBack.setStatistics(statisticDatas);
        return statisticsBack;
    }*/

    @ResponseBody
    @RequestMapping(value = "/yearORpositionStatistics")//需提供是要统计所有(年份或者地点)
    public StatisticsYPBack getyearORpositionStatistics(@RequestParam(value="type") String type) {
        StatisticsYPBack statisticsYPBack = new StatisticsYPBack();
        int yp1;
        int yp2;
        int yp3;
        int yp4;
        int yp5;
        int yp6=0;
        if ("year".equals(type))
        {
            yp1 = deviceService.getCountBybuyYear("2011");
            yp2 = deviceService.getCountBybuyYear("2012");
            yp3 = deviceService.getCountBybuyYear("2013");
            yp4 = deviceService.getCountBybuyYear("2014");
            yp5 = deviceService.getCountBybuyYear("2015");
        }else if("position".equals(type)){
            yp1 = deviceService.getCountByposition("0");
            yp2 = deviceService.getCountByposition("1");
            yp3 = deviceService.getCountByposition("2");
            yp4 = deviceService.getCountByposition("3");
            yp5 = deviceService.getCountByposition("4");
            yp6 = deviceService.getCountByposition("5");
        }else
        {
            statisticsYPBack.setSuccess(false);
            statisticsYPBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
            return statisticsYPBack;
        }
        statisticsYPBack.setYp1(yp1);
        statisticsYPBack.setYp2(yp2);
        statisticsYPBack.setYp3(yp3);
        statisticsYPBack.setYp4(yp4);
        statisticsYPBack.setYp5(yp5);
        statisticsYPBack.setYp6(yp6);
        statisticsYPBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        return statisticsYPBack;
    }
}
