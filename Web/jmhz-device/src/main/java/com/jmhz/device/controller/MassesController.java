package com.jmhz.device.controller;

import com.jmhz.device.model.Tappeal;
import com.jmhz.device.model.Tappealmark;
import com.jmhz.device.model.Tappealrate;
import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 锋情 on 2014/5/11.
 */
@Controller
@RequestMapping("/masses")
public class MassesController {
    @Autowired
    private AppealServiceI appealService;
    @Autowired
    private AppealRateServiceI appealRateService;
    @Autowired
    private FarmerServiceI farmerService;
    @Autowired
    private GroupServiceI groupService;
    @Autowired
    private TappealmarkServiceI tappealmarkService;

    @RequestMapping(value = "/queryAppeal", method = RequestMethod.GET)
    public String mqGroupCardInput() {
        return "/jsp/masses/queryAppeal.jsp";
    }


    @ResponseBody
    @RequestMapping(value = "/queryMassesAppeal/{query}", method = RequestMethod.GET)
    public JsonModel querySpecialFamily(@PathVariable(value = "query") String query, String page, String rows, String _search, String filters) {
        System.out.println(query);
        JsonModel jsonModel = new JsonModel();
        if (query.equals("0,0,0")) {
            return jsonModel;
        }
        String[] queryList = query.split(",");

        // if (_search.equals("false")) {
        List<Tappeal> tappeals;
        List<Tappeal> tappealsNew = new ArrayList<Tappeal>();
        tappeals = appealService.getMassesAppeal(page, rows, queryList);
        System.out.println(queryList[2]);
        if (!queryList[2].equals("0")) {
            System.out.println(queryList[2]);
            for (int i = 0; i < tappeals.size(); i++) {
                //个人
                if (tappeals.get(i).getProposer().equals("0")) {
                    if (farmerService.getFarmerMassesById(tappeals.get(i).getAppealid()).getContactnumber().equals(queryList[2])) {
                        tappealsNew.add(tappeals.get(i));
                    }
                } else {
                    //集体
                    if (groupService.getGroupMassesById(tappeals.get(i).getAppealid()).getGroupchargertel().equals(queryList[2])) {
                        tappealsNew.add(tappeals.get(i));
                    }
                }

            }

            int pages = tappealsNew.size() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tappealsNew);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(tappealsNew.size());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }
        // Long count = farmerService.getSpecialFamilyCount(query);
        Long count = appealService.getMassesAppealCount(queryList);
        int pages = count.intValue() / Integer.parseInt(rows) + 1;
        jsonModel.setSuccess(true);
        jsonModel.setMsg("success");
        jsonModel.setDataObj(tappeals);
        jsonModel.setTotalpages(pages);
        jsonModel.setCurrentpage(Integer.parseInt(page));
        jsonModel.setTotalrecords(count.intValue());
        jsonModel.setPagerows(Integer.parseInt(rows));
        jsonModel.setSort("");
        jsonModel.setOrder("");
        return jsonModel;
//        } else {
//            //查询
//            filters = filters.replace("&quot;", "\"");
//            Filters filtersClass = JSON.parseObject(filters, Filters.class);
//            //清理诉求信息 与 数据库一致
//            for (int i = 0; i < filtersClass.getRules().size(); i++) {
//                //性别
//                if (filtersClass.getRules().get(i).getField().equals("gender")) {
//                    if (filtersClass.getRules().get(i).getData().equals("男")) {
//                        filtersClass.getRules().get(i).setData("0");
//                    } else {
//                        filtersClass.getRules().get(i).setData("1");
//                    }
//                }
//                //政治面貌
//                if (filtersClass.getRules().get(i).getField().equals("politicalstatus")) {
//                    if (filtersClass.getRules().get(i).getData().equals("群众")) {
//                        filtersClass.getRules().get(i).setData("0");
//                    } else if (filtersClass.getRules().get(i).getData().equals("共青团员")) {
//                        filtersClass.getRules().get(i).setData("1");
//                    } else if (filtersClass.getRules().get(i).getData().equals("中共党员")) {
//                        filtersClass.getRules().get(i).setData("2");
//                    }
//                }
//            }
//
//            List<Tfarmer> farmers = new ArrayList<Tfarmer>();
//            farmers = farmerService.querySpecialFamily(page, rows, query, filtersClass);
//
//            Long count = farmerService.getSpecialFamilyCount(query, filtersClass);
//            int pages = count.intValue() / Integer.parseInt(rows) + 1;
//            jsonModel.setSuccess(true);
//            jsonModel.setMsg("success");
//            jsonModel.setDataObj(farmers);
//            jsonModel.setTotalpages(pages);
//            jsonModel.setCurrentpage(Integer.parseInt(page));
//            jsonModel.setTotalrecords(count.intValue());
//            jsonModel.setPagerows(Integer.parseInt(rows));
//            jsonModel.setSort("");
//            jsonModel.setOrder("");
//            return jsonModel;
//        }

    }

    @RequestMapping("/toviewpage/{id}")
    public String toviewpage(@PathVariable("id") int id, Model model) {
        Tappeal tappeal = appealService.getAppealById(id);
        if (tappeal.getProposer().equals("0")) {
            List<Tappealmark> tappealmarkList = tappealmarkService.getAllAppealmark(id);
            List<Tappealrate> tappealrateList = appealRateService.getAllAppealRate(tappeal.getId());
            //农户数据
            model.addAttribute("appeal", tappeal);
            model.addAttribute("farmer", farmerService.getFarmerMassesById(tappeal.getAppealid()));
            model.addAttribute("tappealmarks", tappealmarkList);
            model.addAttribute("tappealrates", tappealrateList);
            model.addAttribute("marksize", tappealmarkList.size());
            model.addAttribute("ratesize", tappealrateList.size());
            //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
            model.addAttribute("navMenu", "appeal");
            model.addAttribute("subMenu", "appealView");
            model.addAttribute("curDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            return "/jsp/masses/appealmassesshow.jsp";// 跳转到个人诉求状态界面
        } else {
            List<Tappealmark> tappealmarkList = tappealmarkService.getAllAppealmark(id);
            List<Tappealrate> tappealrateList = appealRateService.getAllAppealRate(tappeal.getId());
            //集体数据
            model.addAttribute("appeal", tappeal);
            model.addAttribute("group", groupService.getGroupMassesById(tappeal.getAppealid()));
            model.addAttribute("tappealmarks", tappealmarkList);
            model.addAttribute("tappealrates", tappealrateList);
            model.addAttribute("marksize", tappealmarkList.size());
            model.addAttribute("ratesize", tappealrateList.size());
            //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
            model.addAttribute("navMenu", "appeal");
            model.addAttribute("subMenu", "appealView");
            model.addAttribute("curDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            return "/jsp/masses/appealgroupshow.jsp";// 跳转到个人诉求状态界面
        }

    }


    @ResponseBody
    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public JsonModel rate(@RequestParam("appealId") int appealId,
                          @RequestParam("rateLevel") int rateLevel,
                          @RequestParam("rateContent") String rateContent,
                          Model model) {

        Tappealrate newAppealRate = new Tappealrate(appealId, rateLevel, rateContent, new Date());
        int rsid = appealRateService.add(newAppealRate);
        model.addAttribute("appeal", newAppealRate);
        JsonModel jsonModel = new JsonModel();
        if (rsid == -1) {
            jsonModel.setSuccess(false);
        } else {
            jsonModel.setSuccess(true);
        }
        return jsonModel;
    }

    @RequestMapping(value = "/createAppeal", method = RequestMethod.GET)
    public String createAppeal() {
        return "/jsp/masses/createAppeal.jsp";
    }

    @RequestMapping(value = "/addAppeal", method = RequestMethod.POST)
    @ResponseBody
    public String addAppeal(@RequestParam(value = "name") String name,
                            @RequestParam(value = "town") String town,
                            @RequestParam(value = "village") String village,
                            @RequestParam(value = "telephone") String telephone,
                            @RequestParam(value = "appealtype") String appealtype,
                            @RequestParam(value = "affairtype") String affairtype,
                            @RequestParam(value = "hardshipappeal") String hardshipappeal) {

        Tappeal tappeal = new Tappeal();
        Tfarmer tfarmer = farmerService.getFarmerByNameAndTel(name,telephone);
        if (tfarmer != null){
            tappeal.setAppealid(tfarmer.getId());
        }else {
            Tfarmer newTfarmer = new Tfarmer();
            newTfarmer.setHouseholdername(name);
            newTfarmer.setTown(town);
            newTfarmer.setVillage(village);
            newTfarmer.setContactnumber(telephone);
            newTfarmer.setCity("沙县");
            tappeal.setAppealid(farmerService.add(newTfarmer));
        }


        tappeal.setAppealname(name);
        tappeal.setCity("沙县");
        tappeal.setTown(town);
        tappeal.setVillage(village);
        tappeal.setProposer("0");
        tappeal.setAppealtype(appealtype);
        tappeal.setAffairtype(affairtype);
        tappeal.setStatus("0");
        tappeal.setHardshipappeal(hardshipappeal);
        tappeal.setAppealtel(telephone);
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        tappeal.setCreatedate(format.format(new Date()));
        int aid = appealService.add(tappeal);
        if (aid >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }

    }
}
