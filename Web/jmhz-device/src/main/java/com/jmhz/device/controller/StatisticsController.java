package com.jmhz.device.controller;

import com.jmhz.device.backModel.Statistics;
import com.jmhz.device.service.*;
import com.jmhz.device.model.device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.ArrayList;



/**
 * Created by fjfzuhqc on 2015/10/11.
 */
@Controller
@RequestMapping("/statistics")//控制器名称
public class StatisticsController extends BaseController {

    @Autowired
    private ServletContext servletContext;

    /**
     * 导出excel存储的目录
     */
    private String contextRootPath;

    @PostConstruct
    public void afterPropertiesSet() {
        contextRootPath = servletContext.getRealPath("/");
    }



    @Autowired
    private  FaultServiceI FaultService;

    @Autowired
    private UpdatesuccessServiceI UpdatesuccessService;

    @Autowired
    private upgradeServiceI upgradeService;

    @Autowired
    private deviceServiceI deviceService;



    @RequestMapping(value = "/devicestatistics", method = RequestMethod.GET)//value方法名称
    public String devicestatistics(Model model) {
        int deviceCount = deviceService.getCount().intValue();//总设备量
        //获取设备某一状态数量 0-使用中，1-废弃，2-是维修，3-升级，4-闲置
        int deviceUsingCount = deviceService.getStatusCount("0");
        int deviceDiscardCount = deviceService.getStatusCount("1");
        int deviceRepairCount = deviceService.getStatusCount("2");
        int deviceUpdateCount = deviceService.getStatusCount("3");
        int deviceUnuseCount = deviceService.getStatusCount("4");
        int usingRatio = 0;
        int discardRatio = 0;
        int repairRatio = 0;
        int updateRatio = 0;
        int unuseRatio = 0;
        if (deviceCount != 0){
            usingRatio = 100 * deviceUsingCount / deviceCount;
            discardRatio = 100 * deviceDiscardCount / deviceCount;
            repairRatio = 100 * deviceRepairCount / deviceCount;
            updateRatio = 100 * deviceUpdateCount / deviceCount;
            unuseRatio = 100 * deviceUnuseCount / deviceCount;
        }
        model.addAttribute("deviceCount", deviceCount);
        model.addAttribute("deviceUsingCount", deviceUsingCount);
        model.addAttribute("usingRatio", usingRatio);
        model.addAttribute("deviceDiscardCount", deviceDiscardCount);
        model.addAttribute("discardRatio", discardRatio);
        model.addAttribute("deviceRepairCount", deviceRepairCount);
        model.addAttribute("repairRatio", repairRatio);
        model.addAttribute("deviceUpdateCount", deviceUpdateCount);
        model.addAttribute("updateRatio", updateRatio);
        model.addAttribute("deviceUnuseCount", deviceUnuseCount);
        model.addAttribute("unuseRatio", unuseRatio);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "statistics");
        model.addAttribute("subMenu", "devicestatistics");
        return "/jsp/statistics/devicestatistics.jsp";
    }
    @RequestMapping(value ="/repairstatistics" , method = RequestMethod.GET)
    public String repairstatistics(Model model) {
        String condition = "";
        int unsolvedCount = FaultService.getRepairCount("0");
        int doingCount = FaultService.getRepairCount("1");
        int finishCount = FaultService.getRepairCount("2");
       /** int total = unsolvedCount  + doingCount  + finishCount;
        int[] countlist = new int[4];
        if (total != 0) {
            countlist[0] = total;
            countlist[1] = 100 * unsolvedCount / total;
            countlist[2] = 100 * doingCount / total;
            countlist[3] = 100 * finishCount / total;

        } else {
            countlist[0] = 0;
            countlist[1] = 0;
            countlist[2] = 0;
            countlist[3] = 0;
        }
        */
        //model.addAttribute("countlist", countlist);
        model.addAttribute("unsolvedCount", unsolvedCount);
        model.addAttribute("doingCount", doingCount);
        model.addAttribute("finishCount", finishCount);
        model.addAttribute("navMenu", "statistics");
        model.addAttribute("subMenu", "repairstatistics");
        return "/jsp/statistics/repairstatistics.jsp";
    }

    @RequestMapping(value ="/updatestatistics" , method = RequestMethod.GET)
    public String updatestatistics(Model model) {
        int unsolvedCount = upgradeService.getCount("0");
        int doingCount = upgradeService.getCount("1");
        int finishCount = upgradeService.getCount("2");
        /**
        int unsolvedCount = UpdatesuccessService.getCount("0");
        int doingCount = UpdatesuccessService.getCount("1");
        int finishCount = UpdatesuccessService.getCount("2");
        int total = unsolvedCount  + doingCount  + finishCount;
        int[] countlist = new int[4];
        if (total != 0) {
            countlist[0] = total;
            countlist[1] = 100 * unsolvedCount / total;
            countlist[2] = 100 * doingCount / total;
            countlist[3] = 100 * finishCount / total;

        } else {
            countlist[0] = 0;
            countlist[1] = 0;
            countlist[2] = 0;
            countlist[3] = 0;
        }*/
       // model.addAttribute("countlist", countlist);
        model.addAttribute("unsolvedCount", unsolvedCount);
        model.addAttribute("doingCount", doingCount);
        model.addAttribute("finishCount", finishCount);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "statistics");
        model.addAttribute("subMenu", "updatestatistics");
        return "/jsp/statistics/updatestatistics.jsp";
    }
    @RequestMapping(value ="/yearandposition" , method = RequestMethod.GET)
    public String yearandposition(Model model) {
       // String condition = "";
        //各年份设备数量
        int y2011Count = deviceService.getCountBybuyYear("2011");
        int y2012Count = deviceService.getCountBybuyYear("2012");
        int y2013Count = deviceService.getCountBybuyYear("2013");
        int y2014Count = deviceService.getCountBybuyYear("2014");
        int y2015Count = deviceService.getCountBybuyYear("2015");

       //各实验室设备数量 0-5分别表示301-306
        int p301Count = deviceService.getCountByposition("0");
        int p302Count = deviceService.getCountByposition("1");
        int p303Count = deviceService.getCountByposition("2");
        int p304Count = deviceService.getCountByposition("3");
        int p305Count = deviceService.getCountByposition("4");
        int p306Count = deviceService.getCountByposition("5");

        model.addAttribute("y2011Count", y2011Count);
        model.addAttribute("y2012Count", y2012Count);
        model.addAttribute("y2013Count", y2013Count);
        model.addAttribute("y2014Count", y2014Count);
        model.addAttribute("y2015Count", y2015Count);
        //model.addAttribute("countlist1", countlist1);
        model.addAttribute("p301Count", p301Count);
        model.addAttribute("p302Count", p302Count);
        model.addAttribute("p303Count", p303Count);
        model.addAttribute("p304Count", p304Count);
        model.addAttribute("p305Count", p305Count);
        model.addAttribute("p306Count", p306Count);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "statistics");
        model.addAttribute("subMenu", "yearandposition");
        return "/jsp/statistics/yearandposition.jsp";
    }
    @RequestMapping(value = "/showPie", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Statistics> showPie( @RequestParam(value = "selectFirst")String selectFirst,//条件统计的一级条件，年份或者地点
                            @RequestParam(value = "selectSecond")String selectSecond//条件统计二级条件，具体年份或地点
                           ) {
        int unuseCount=0;
        int usedCount=0;
        int discardCount=0;
        int repairCount=0;
        int updateCount=0;
//        System.out.print("进入showPie"+selectSecond);
        if("time".equals(selectFirst))
        {
            unuseCount=deviceService.getYeartoCount(selectSecond,"0");
            usedCount=deviceService.getYeartoCount(selectSecond, "1");
            discardCount=deviceService.getYeartoCount(selectSecond, "2");
            repairCount=deviceService.getYeartoCount(selectSecond,"3");
            updateCount=deviceService.getYeartoCount(selectSecond,"4");
        }else if("position".equals(selectFirst)) {
            unuseCount = deviceService.getPositiontoCount(selectSecond, "0");
            usedCount = deviceService.getPositiontoCount(selectSecond, "1");
            discardCount = deviceService.getPositiontoCount(selectSecond, "2");
            repairCount=deviceService.getPositiontoCount(selectSecond,"3");
            updateCount=deviceService.getPositiontoCount(selectSecond,"4");
        }else
        {
            return null;
        }
//        System.out.print("进入showPie"+unuseCount);
        Statistics statisticDataUnuse = new Statistics("闲置",unuseCount,"#FFB752");
        Statistics statisticDataUsed = new Statistics("使用中",usedCount,"#68BC31");
        Statistics statisticDataDiscard = new Statistics("废弃",discardCount,"#AF4E96");
        Statistics statisticDataRepair = new Statistics("维修",repairCount,"#C1D4ED");
        Statistics statisticDataUpdate = new Statistics("升级",updateCount,"#DA5430");
        ArrayList<Statistics> statisticData=new ArrayList<Statistics>();
        statisticData.add(statisticDataUnuse);
        statisticData.add(statisticDataUsed);
        statisticData.add(statisticDataDiscard);
        statisticData.add(statisticDataRepair);
        statisticData.add(statisticDataUpdate);
        return statisticData;
    }


}
