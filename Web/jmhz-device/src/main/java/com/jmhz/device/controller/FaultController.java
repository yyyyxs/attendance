package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.model.FaultApply;
import com.jmhz.device.model.Faultrepair;
import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.model.device;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.*;
import com.jmhz.device.sys.entity.User;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 锋情 on 2014/4/18.
 */
@Controller
@RequestMapping("/fault")
public class FaultController extends BaseController {
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
    private deviceServiceI deviceService;
    @Autowired
    private FaultServiceI faultService;
    @Autowired
    private FarmerServiceI farmerService;



/*    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String faultapply(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单

        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerCreate");
        return "/jsp/devicemg/faultApply.jsp";

    }*/

    @RequestMapping(value = "/add{id}", method = RequestMethod.GET)
    public String faultapply(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        String username = user.getUsername();
        device devices = deviceService.getdeviceById(id);
        model.addAttribute("username", username);
        model.addAttribute("device", devices);
        model.addAttribute("navMenu", "fault");
        model.addAttribute("subMenu", "approve");
        return "/jsp/devicemg/faultApply.jsp";

    }

    @ResponseBody
    @RequestMapping(value = "/addfault", method = RequestMethod.POST)
    public String faultAdd(FaultApply appplys, Model model, HttpServletRequest request) {
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        int userid = user.getId().intValue();
        appplys.setUserId(userid);
        int said = faultService.add(appplys);
//        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
//        model.addAttribute("navMenu", "fault");
//        model.addAttribute("subMenu", "approve");
        if (said >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updatefault", method = RequestMethod.POST)
    public String updatefault(FaultApply appplys, Model model, HttpServletRequest request) {
        // System.out.println(id);
        //appplys=faultService.getapplyById(id);
        boolean said = faultService.update(appplys);
//        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
//        model.addAttribute("navMenu", "fault");
//        model.addAttribute("subMenu", "approve");
        model.addAttribute("apply", appplys);
        if (said) {
            return "1";//1 成功
        } else {
            return "0";
        }
    }

    @RequestMapping(value = "/showapply", method = RequestMethod.GET)
    public String Showapply(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "fault");
        model.addAttribute("subMenu", "approve");
        return "/jsp/devicemg/faultlist.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllapply", method = RequestMethod.GET)
    public JsonModel getAllapply(String page, String rows, String _search, String filters, HttpSession session, HttpServletRequest request) {
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        int userId = user.getId().intValue();
        int roleId = Integer.parseInt(user.getRoleIds());
        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            if (roleId == 1 || roleId == 2)//根据权限判断是否显示所有记录，2，3表示管理员和超管
            {

                List<FaultApply> applys;
                applys = faultService.getAllapply(page, rows);
                Long count = faultService.getCount();
                int pages = count.intValue() / Integer.parseInt(rows) + 1;
                jsonModel.setSuccess(true);
                jsonModel.setMsg("success");
                jsonModel.setDataObj(applys);
                jsonModel.setTotalpages(pages);
                jsonModel.setCurrentpage(Integer.parseInt(page));
                jsonModel.setTotalrecords(count.intValue());
                jsonModel.setPagerows(Integer.parseInt(rows));
                jsonModel.setSort("");
                jsonModel.setOrder("");
            } else //1表示普通用户，根据userId显示记录，下面条件查询同理
            {
                List<FaultApply> applys;
                applys = faultService.getAllapply(page, rows, userId);
                Long count = faultService.getCount(userId);
                int pages = count.intValue() / Integer.parseInt(rows) + 1;
                jsonModel.setSuccess(true);
                jsonModel.setMsg("success");
                jsonModel.setDataObj(applys);
                jsonModel.setTotalpages(pages);
                jsonModel.setCurrentpage(Integer.parseInt(page));
                jsonModel.setTotalrecords(count.intValue());
                jsonModel.setPagerows(Integer.parseInt(rows));
                jsonModel.setSort("");
                jsonModel.setOrder("");
            }
            return jsonModel;
        } else {
            //查询
            session.setAttribute("farmerFilters", filters);
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);

            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++) {
                //状态
                if (filtersClass.getRules().get(i).getField().equals("statues")) {
                    if (filtersClass.getRules().get(i).getData().equals("使用中")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (filtersClass.getRules().get(i).getData().equals("废弃")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (filtersClass.getRules().get(i).getData().equals("维修中")) {
                        filtersClass.getRules().get(i).setData("2");
                    } else {
                        filtersClass.getRules().get(i).setData("3");
                    }
                }
                //审核进度
                if (filtersClass.getRules().get(i).getField().equals("approve")) {
                    if (filtersClass.getRules().get(i).getData().equals("审核中")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (filtersClass.getRules().get(i).getData().equals("同意维修")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (filtersClass.getRules().get(i).getData().equals("拒绝维修")) {
                        filtersClass.getRules().get(i).setData("2");
                    }
                }
                if (filtersClass.getRules().get(i).getField().equals("deviceType")) {
                    if (filtersClass.getRules().get(i).getData().equals("公有")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (filtersClass.getRules().get(i).getData().equals("私有")) {
                        filtersClass.getRules().get(i).setData("1");
                    }
                }

            }
            if (roleId == 1 || roleId == 2) {
                List<FaultApply> applys;
                applys = faultService.getAllapply(page, rows, filtersClass);
                Long count = faultService.getCount(filtersClass);
                int pages = count.intValue() / Integer.parseInt(rows) + 1;
                jsonModel.setSuccess(true);
                jsonModel.setMsg("success");
                jsonModel.setDataObj(applys);
                jsonModel.setTotalpages(pages);
                jsonModel.setCurrentpage(Integer.parseInt(page));
                jsonModel.setTotalrecords(count.intValue());
                jsonModel.setPagerows(Integer.parseInt(rows));
                jsonModel.setSort("");
                jsonModel.setOrder("");
            } else {
                List<FaultApply> applys;
                applys = faultService.getAllapply(page, rows, filtersClass, userId);
                Long count = faultService.getCount(filtersClass, userId);
                int pages = count.intValue() / Integer.parseInt(rows) + 1;
                jsonModel.setSuccess(true);
                jsonModel.setMsg("success");
                jsonModel.setDataObj(applys);
                jsonModel.setTotalpages(pages);
                jsonModel.setCurrentpage(Integer.parseInt(page));
                jsonModel.setTotalrecords(count.intValue());
                jsonModel.setPagerows(Integer.parseInt(rows));
                jsonModel.setSort("");
                jsonModel.setOrder("");
            }
            return jsonModel;
        }

    }

    @RequestMapping(value = "/approve", method = RequestMethod.GET)
    public String Approve(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appply");
        model.addAttribute("subMenu", "faultppply");
        return "/jsp/devicemg/applylist.jsp";
    }

    @RequestMapping(value = "/faultLog/{id}", method = RequestMethod.GET)
    public String faultLog(@PathVariable("id") int id, Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        FaultApply apply = faultService.getapplyById(id);
        model.addAttribute("apply", apply);
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerCreate");
        return "/jsp/devicemg/faultLog.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/addFaultLog", method = RequestMethod.POST)
    public String addFaultLog(Faultrepair logs, int applyId, Model model, HttpServletRequest request) {
        int said = faultService.addlog(logs);
        FaultApply apply = faultService.getapplyById(applyId);
        // System.out.println("asf" + apply);
        apply.setLogmark(1);
        faultService.update(apply);
        // System.out.println(apply.getLogmark());
        if (said >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }
    }

    @RequestMapping(value = "/showlog/{id}", method = RequestMethod.GET)
    public String showlog(@PathVariable("id") int applyId, Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        Faultrepair log = faultService.getlogByapplyID(applyId);
        model.addAttribute("log", log);
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerCreate");
        return "/jsp/devicemg/showlog.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/getfaultById", method = RequestMethod.POST)
    public JsonModel getfaultById(int id) {
        JsonModel jsonModel = new JsonModel();
        FaultApply apply = faultService.getapplyById(id);
        jsonModel.setDataObj(apply);
        return jsonModel;
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
    @RequestMapping("/deletes")
    public String modify(@RequestParam("id") String ids, @RequestParam("oper") String oper) {
        if (oper.equals("edit")) {
            //查看全部信息
            return "/jsp/device/add.jsp";
        } else if (oper.equals("del")) {
            String idlist[] = ids.split(",");
            int id;
            for (int i = 0; i < idlist.length; i++) {
                id = Integer.parseInt(idlist[i]);
                device devices = new device();
                devices.setId(id);
                deviceService.deldevice(devices);
            }
            return "/jsp/device/list.jsp";
        } else if (oper.equals("add")) {
            return "/jsp/device/add.jsp";
        }
        return "/jsp/device/list.jsp";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public JsonModel delete(FaultApply applys) {

        faultService.delfaultapply(applys);

        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    @RequestMapping("/toviewpage/{id}")
    public String toviewpage(@PathVariable("id") int id, Model model) {
        FaultApply applys = faultService.getapplyById(id);
        // device devices=deviceService.getdeviceById(id);
        model.addAttribute("apply", applys);
        //model.addAttribute("device", devices);
//        if (tfarmer.getRepresentation() != null) {
//            model.addAttribute("representationlist", tfarmer.getRepresentation().split(","));
//        }
//        if (tfarmer.getSpecialfamily() != null) {
//            model.addAttribute("specialfamilylist", tfarmer.getSpecialfamily().split(","));
//        }
//        if (tfarmer.getHousingsituation() != null) {
//            model.addAttribute("housingsituationlist", tfarmer.getHousingsituation().split(","));
//        }
//        List<Tfarmervisit> tfarmervisitList = farmerVisitService.getAllVisitList(id);
//        model.addAttribute("farmerVisitList", tfarmervisitList);
//        model.addAttribute("farmerVisitSize", tfarmervisitList.size());
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerView");
        return "/jsp/devicemg/applyShow.jsp";
    }


    @RequestMapping(value = "/modifyapply/{id}", method = RequestMethod.GET)
    public String modefyapply(@PathVariable("id") int id, Model model) {
        FaultApply applys = faultService.getapplyById(id);
        model.addAttribute("apply", applys);
        return "/jsp/devicemg/modifyApply.jsp";

    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateDevice(FaultApply applys, Model model, HttpServletRequest request) {

//        BeanUtils.copyProperties(mqCard, tfarmer);
//        //将账号的县、镇信息获取
//        User user = (User) request.getAttribute(Constants.CURRENT_USER);
//        tfarmer.setCity(user.getCity());
//        tfarmer.setTown(user.getTown());

        boolean fid = faultService.update(applys);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerView");
        model.addAttribute("apply", applys);
        if (fid) {
            model.addAttribute("addres", "1");
        } else {
            model.addAttribute("addres", "0");
        }
        return "/jsp/devicemg/applyShow.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/approveopinion", method = RequestMethod.POST)
    public JsonModel addVisit(@RequestParam("id") int id,
                              @RequestParam("status") String status,
                              @RequestParam("approve") String approve,
                              @RequestParam("approveRemark") String approveRemark,
                              Model model) {
        FaultApply applys = faultService.getapplyById(id);
        applys.setStatus(status);
        applys.setApprove(approve);
        applys.setApproveRemark(approveRemark);
        boolean fvid = faultService.update(applys);
        JsonModel jsonModel = new JsonModel();
        if (!fvid) {
            jsonModel.setSuccess(false);
        } else {
            jsonModel.setSuccess(true);
        }
        return jsonModel;
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
        if (farmerCount != 0) {
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
    public JsonModel exportExcel(Model model, HttpSession session, HttpServletRequest request) throws IOException {
        //按条件导出
        if (session.getAttribute("farmerFilters") != null) {
            String filters = session.getAttribute("farmerFilters").toString();
            //清除session
            session.setAttribute("farmerFilters", null);
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++) {
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
                if (curFilterRuleField.equals("politicalstatus")) {
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
        } else {
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


