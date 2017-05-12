package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.pageModel.MqCard;
import com.jmhz.device.model.Tappeal;
import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.model.Tfarmervisit;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.*;
import com.jmhz.device.sys.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 锋情 on 2014/4/18.
 */
@Controller
@RequestMapping("/farmer")
public class FarmerController extends BaseController {
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
    private FamerDataExportServiceI famerDataExportService;
    @Autowired
    private FarmerServiceI farmerService;

    @Autowired
    private AppealServiceI appealService;

    @Autowired
    private FarmerVisitServiceI farmerVisitService;
    @Autowired
    private TappealmarkServiceI tappealmarkService;

    @RequestMapping(value = "/mqcard", method = RequestMethod.GET)
    public String mqCardInput(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerCreate");
        return "/jsp/farmer/mqcard.jsp";
    }

    @Transactional
    @RequestMapping(value = "/addMqcard", method = RequestMethod.POST)
    public String mqCardAdd(MqCard mqCard, Model model,HttpServletRequest request) {
        //根据用户名+号码，判断用户是否存在
        //int aid = -1;
        if (farmerService.getFarmer(mqCard.getHouseholdername(),mqCard.getContactnumber()) == null) {
            Tfarmer tfarmer = new Tfarmer();
            Tappeal tappeal = new Tappeal();
            Tfarmervisit tfarmervisit = new Tfarmervisit();
            BeanUtils.copyProperties(mqCard, tfarmer);
            BeanUtils.copyProperties(mqCard, tappeal);
            //将账号的县、镇信息获取
            User user = (User) request.getAttribute(Constants.CURRENT_USER);
            tfarmer.setCity(user.getCity());
            tfarmer.setTown(user.getTown());


            if (tfarmer.getSnackprovince() != null ){
                if (tfarmer.getSnackprovince().equals("0")){
                    tfarmer.setSnackprovince("");
                }
            }
            if (tfarmer.getSnackcity() != null ){
                if (tfarmer.getSnackcity().equals("0")){
                    tfarmer.setSnackcity("");
                }
            }
            if (tfarmer.getSnackarea() != null){
                if (tfarmer.getSnackarea().equals("0")){
                    tfarmer.setSnackarea("");
                }
            }
            if (tfarmer.getWorkprovince() != null){
                if (tfarmer.getWorkprovince().equals("0")){
                    tfarmer.setWorkprovince("");
                }
            }
            if (tfarmer.getWorkcity() != null){
                if (tfarmer.getWorkcity().equals("0")){
                    tfarmer.setWorkcity("");
                }
            }
            if (tfarmer.getWorkarea() != null){
                if (tfarmer.getWorkarea().equals("0")){
                    tfarmer.setWorkarea("");
                }
            }
            int fid = farmerService.add(tfarmer);
            //判断是否添加诉求
            if (!mqCard.getHardshipappeal().equals("")){
                tappeal.setCity(user.getCity());
                tappeal.setTown(user.getTown());
                tappeal.setAppealtel(mqCard.getContactnumber());
                tappeal.setAppealid(fid);
                tappeal.setVillage(tfarmer.getVillage());
                tappeal.setProposer("0");//"0"为farmer;"1"为group
                tappeal.setAppealname(tfarmer.getHouseholdername());
                tappeal.setStatus("0");//"0"为未解决
                SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
                tappeal.setCreatedate(format.format(new Date()));
                appealService.add(tappeal);
            }
            if (!mqCard.getVisittime().equals("")) {
                BeanUtils.copyProperties(mqCard, tfarmervisit);
                tfarmervisit.setFarmerid(fid);
                tfarmervisit.setInserttime(new Date());
                farmerVisitService.add(tfarmervisit);
            }
            if (fid >= 0) {
                model.addAttribute("addres", "1");
            } else {
                model.addAttribute("addres", "0");
            }
        } else {
            model.addAttribute("addres", "2");
        }

        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerCreate");
        return "/jsp/farmer/mqcard.jsp";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String farmerShow(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerView");
        return "/jsp/farmer/list.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllFarmer", method = RequestMethod.GET)
    public JsonModel getAllFarmer(String page, String rows, String _search, String filters, HttpSession session) {

        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            List<Tfarmer> farmers;
            farmers = farmerService.getAllFarmer(page, rows);

            Long count = farmerService.getCount();
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(farmers);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        } else {
            //查询
            session.setAttribute("farmerFilters", filters);
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);

            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++) {
                //性别
                if (filtersClass.getRules().get(i).getField().equals("gender")) {
                    if (filtersClass.getRules().get(i).getData().equals("男")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //政治面貌
                if (filtersClass.getRules().get(i).getField().equals("politicalstatus")) {
                    if (filtersClass.getRules().get(i).getData().equals("群众")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (filtersClass.getRules().get(i).getData().equals("共青团员")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (filtersClass.getRules().get(i).getData().equals("中共党员")) {
                        filtersClass.getRules().get(i).setData("2");
                    }
                }
            }

            List<Tfarmer> farmers = new ArrayList<Tfarmer>();
            farmers = farmerService.getAllFarmer(page, rows, filtersClass);

            Long count = farmerService.getCount(filtersClass);
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(farmers);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getAllFarmerByPlant", method = RequestMethod.GET)
    public JsonModel getAllFarmerByPlant(String page, String rows, String _search, String filters) {

        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            List<Tfarmer> farmers;
            farmers = farmerService.getAllFarmerByPlant(page, rows);

            Long count = farmerService.getCountByPlant();
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(farmers);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        } else {
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++) {
                //性别
                if (filtersClass.getRules().get(i).getField().equals("gender")) {
                    if (filtersClass.getRules().get(i).getData().equals("男")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //政治面貌
                if (filtersClass.getRules().get(i).getField().equals("politicalstatus")) {
                    if (filtersClass.getRules().get(i).getData().equals("群众")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (filtersClass.getRules().get(i).getData().equals("共青团员")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (filtersClass.getRules().get(i).getData().equals("中共党员")) {
                        filtersClass.getRules().get(i).setData("2");
                    }
                }
            }

            List<Tfarmer> farmers = new ArrayList<Tfarmer>();
            farmers = farmerService.getAllFarmerByPlant(page, rows, filtersClass);

            Long count = farmerService.getCountByPlant(filtersClass);
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(farmers);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }

    }

    @ResponseBody
    @RequestMapping("/modify")
    public String modify(Tfarmer tfarmer, @RequestParam("oper") String oper) {
        if (oper.equals("edit")) {
            //查看全部信息
            return "/jsp/farmer/mqcard.jsp";
        } else if (oper.equals("del")) {
            //删除
            farmerService.delFarmer(tfarmer);
            List<Tappeal> Tappeals = appealService.getAppealId(tfarmer.getId());
            appealService.delAppealByFarmerId(tfarmer.getId());
            for (Tappeal t : Tappeals){
                int count = appealService.getCountByUuid(t.getUuid());
                if (count <= 1){
                    //删除诉求的备注
                    tappealmarkService.delAppealmarkByUuid(t.getUuid());
                }

            }
            return "/jsp/farmer/list.jsp";
        } else if (oper.equals("add")) {
            return "/jsp/farmer/mqcard.jsp";
        }
        return "/jsp/farmer/list.jsp";
    }

    @RequestMapping("/toviewpage/{id}")
    public String toviewpage(@PathVariable("id") int id, Model model) {
        Tfarmer tfarmer = farmerService.getFarmerById(id);
        model.addAttribute("farmer", tfarmer);
        if (tfarmer.getRepresentation() != null) {
            model.addAttribute("representationlist", tfarmer.getRepresentation().split(","));
        }
        if (tfarmer.getSpecialfamily() != null) {
            model.addAttribute("specialfamilylist", tfarmer.getSpecialfamily().split(","));
        }
        if (tfarmer.getHousingsituation() != null) {
            model.addAttribute("housingsituationlist", tfarmer.getHousingsituation().split(","));
        }
        List<Tfarmervisit> tfarmervisitList = farmerVisitService.getAllVisitList(id);
        model.addAttribute("farmerVisitList", tfarmervisitList);
        model.addAttribute("farmerVisitSize", tfarmervisitList.size());
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerView");
        return "/jsp/farmer/farmershow.jsp";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateFarmer(MqCard mqCard, Model model,HttpServletRequest request) {
        Tfarmer tfarmer = new Tfarmer();
        BeanUtils.copyProperties(mqCard, tfarmer);
        //将账号的县、镇信息获取
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        tfarmer.setCity(user.getCity());
        tfarmer.setTown(user.getTown());
        if (tfarmer.getSnackprovince() != null ){
            if (tfarmer.getSnackprovince().equals("0")){
                tfarmer.setSnackprovince("");
            }
        }
        if (tfarmer.getSnackcity() != null ){
            if (tfarmer.getSnackcity().equals("0")){
                tfarmer.setSnackcity("");
            }
        }
        if (tfarmer.getSnackarea() != null){
            if (tfarmer.getSnackarea().equals("0")){
                tfarmer.setSnackarea("");
            }
        }
        if (tfarmer.getWorkprovince() != null){
            if (tfarmer.getWorkprovince().equals("0")){
                tfarmer.setWorkprovince("");
            }
        }
        if (tfarmer.getWorkcity() != null){
            if (tfarmer.getWorkcity().equals("0")){
                tfarmer.setWorkcity("");
            }
        }
        if (tfarmer.getWorkarea() != null){
            if (tfarmer.getWorkarea().equals("0")){
                tfarmer.setWorkarea("");
            }
        }
        //连带更新诉求中农户的信息
        List<Tappeal> tappeals =  appealService.getAppealId(tfarmer.getId());
        farmerService.update(tfarmer);

        for (Tappeal tappeal : tappeals){
            tappeal.setAppealname(tfarmer.getHouseholdername());
            tappeal.setVillage(tfarmer.getVillage());
            tappeal.setAppealtel(tfarmer.getContactnumber());
            appealService.update(tappeal);
        }
        if (!mqCard.getGridcharger().equals(mqCard.getHiddengridcharger())){
            farmerService.updateGridCharger(tfarmer);
        }
        model.addAttribute("updateRes","1");
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerView");
        return "/jsp/farmer/list.jsp";
    }

    @RequestMapping(value = "/querymainprojects", method = RequestMethod.GET)
    public String queryMainProjects(Model model) {

        int farmerCount = farmerService.getCount().intValue();
        int farmerPlantCount = farmerService.getPlantCount();
        int farmerFarmingCount = farmerService.getFarmingCount();
        int farmerSnackCount = farmerService.getSnackCount();
        int farmerWorkCount = farmerService.getWorkCount();
        int farmerFoundedCount = farmerService.getFoundedCount();
        int plantRatio = 0;
        int farmingRatio = 0;
        int snackRatio = 0;
        int workRatio = 0;
        int foundedRatio = 0;
        if (farmerCount != 0){
             plantRatio = 100 * farmerPlantCount / farmerCount;
             farmingRatio = 100 * farmerFarmingCount / farmerCount;
             snackRatio = 100 * farmerSnackCount / farmerCount;
             workRatio = 100 * farmerWorkCount / farmerCount;
             foundedRatio = 100 * farmerFoundedCount / farmerCount;
        }
        model.addAttribute("farmerCount", farmerCount);
        model.addAttribute("farmerPlantCount", farmerPlantCount);
        model.addAttribute("plantRatio", plantRatio);
        model.addAttribute("farmerFarmingCount", farmerFarmingCount);
        model.addAttribute("farmingRatio", farmingRatio);
        model.addAttribute("farmerSnackCount", farmerSnackCount);
        model.addAttribute("snackRatio", snackRatio);
        model.addAttribute("farmerWorkCount", farmerWorkCount);
        model.addAttribute("workRatio", workRatio);
        model.addAttribute("farmerFoundedCount", farmerFoundedCount);
        model.addAttribute("foundedRatio", foundedRatio);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmerStatistics");
        model.addAttribute("subMenu", "mainProject");
        return "/jsp/farmer/querymainprojects.jsp";
    }

    @RequestMapping(value = "/queryspecialfamily", method = RequestMethod.GET)
    public String querySpecialFamily(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmerStatistics");
        model.addAttribute("subMenu", "specialFamily");
        return "/jsp/farmer/queryspecialfamily.jsp";
    }

    @RequestMapping(value = "/queryhousingsituation", method = RequestMethod.GET)
    public String queryHouseingSituation(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmerStatistics");
        model.addAttribute("subMenu", "houseSituation");
        return "/jsp/farmer/queryhousingsituation.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/queryspecialfamily/{query}", method = RequestMethod.GET)
    public JsonModel querySpecialFamily(@PathVariable(value = "query") String query, String page, String rows, String _search, String filters) {
        query = query.substring(0, query.length() - 1);
        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            List<Tfarmer> farmers;
            farmers = farmerService.querySpecialFamily(page, rows, query);

            Long count = farmerService.getSpecialFamilyCount(query);
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(farmers);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        } else {
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++) {
                //性别
                if (filtersClass.getRules().get(i).getField().equals("gender")) {
                    if (filtersClass.getRules().get(i).getData().equals("男")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //政治面貌
                if (filtersClass.getRules().get(i).getField().equals("politicalstatus")) {
                    if (filtersClass.getRules().get(i).getData().equals("群众")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (filtersClass.getRules().get(i).getData().equals("共青团员")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (filtersClass.getRules().get(i).getData().equals("中共党员")) {
                        filtersClass.getRules().get(i).setData("2");
                    }
                }
            }

            List<Tfarmer> farmers = new ArrayList<Tfarmer>();
            farmers = farmerService.querySpecialFamily(page, rows, query, filtersClass);

            Long count = farmerService.getSpecialFamilyCount(query, filtersClass);
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(farmers);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/queryhousingsituation/{query}", method = RequestMethod.GET)
    public JsonModel queryHousingSituation(@PathVariable(value = "query") String query, String page, String rows, String _search, String filters) {
        query = query.substring(0, query.length() - 1);
        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            List<Tfarmer> farmers;
            farmers = farmerService.queryHousingSituation(page, rows, query);

            Long count = farmerService.getHousingSituationCount(query);
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(farmers);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        } else {
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++) {
                //性别
                if (filtersClass.getRules().get(i).getField().equals("gender")) {
                    if (filtersClass.getRules().get(i).getData().equals("男")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //政治面貌
                if (filtersClass.getRules().get(i).getField().equals("politicalstatus")) {
                    if (filtersClass.getRules().get(i).getData().equals("群众")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (filtersClass.getRules().get(i).getData().equals("共青团员")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (filtersClass.getRules().get(i).getData().equals("中共党员")) {
                        filtersClass.getRules().get(i).setData("2");
                    }
                }
            }

            List<Tfarmer> farmers = new ArrayList<Tfarmer>();
            farmers = farmerService.queryHousingSituation(page, rows, query, filtersClass);

            Long count = farmerService.getHousingSituationCount(query, filtersClass);
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(farmers);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }

    }
    //陈鑫start
    @ResponseBody
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public JsonModel exportExcel(Model model,HttpSession session, HttpServletRequest request) throws IOException {
        //按条件导出
        if (session.getAttribute("farmerFilters") != null)
        {
            String filters = session.getAttribute("farmerFilters").toString();
            //清除session
            session.setAttribute("farmerFilters",null);
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++)
            {
                //性别
                String curFilterRuleField = filtersClass.getRules().get(i).getField();
                String curFilterRuleData = filtersClass.getRules().get(i).getData();
                    if (curFilterRuleField.equals("gender")) {
                    if (curFilterRuleData.equals("男")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        //group
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //政治面貌
                if (curFilterRuleField.equals("politicalstatus"))
                {
                    if (curFilterRuleData.equals("群众")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (curFilterRuleData.equals("共青团员")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (curFilterRuleData.equals("中共党员")) {
                        filtersClass.getRules().get(i).setData("2");
                    }
                }



            }
            famerDataExportService.exportExcel2007(contextRootPath, filtersClass, (User) request.getAttribute(Constants.CURRENT_USER));
        }else {
            //无条件导出
            famerDataExportService.exportExcel2007(contextRootPath, (User) request.getAttribute(Constants.CURRENT_USER));
        }

        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerExportList");
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    //陈鑫end
}


