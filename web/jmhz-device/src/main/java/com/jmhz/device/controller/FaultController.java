package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.model.FaultApply;
import com.jmhz.device.model.Faultrepair;

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
    private deviceServiceI deviceService;
    @Autowired
    private FaultServiceI faultService;





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

}


