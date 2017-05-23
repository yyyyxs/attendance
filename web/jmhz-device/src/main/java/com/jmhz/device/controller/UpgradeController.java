package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.model.*;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.*;
import com.jmhz.device.sys.entity.User;
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
@RequestMapping("/Upgrade")
public class UpgradeController extends BaseController {
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
    private upgradeServiceI upgradeService;



    @RequestMapping(value = "/upgrade", method = RequestMethod.GET)
    public String upgradeapply(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerCreate");
        return "/jsp/upgrade/upgradeApply.jsp";

    }
    @RequestMapping(value = "/modifyapply/{id}", method = RequestMethod.GET)
    public String modifyapply(@PathVariable("id") int id, Model model) {
        upgradeApply applys = upgradeService.getapplyById(id);
        System.out.print(id);
        System.out.print(applys);
        model.addAttribute("apply", applys);
        return "/jsp/upgrade/modifyApply.jsp";

    }
    @RequestMapping(value = "/upgrade/{id}", method = RequestMethod.GET)
    public String upgradeapply(@PathVariable("id") int id, Model model,HttpServletRequest request) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单

        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        String username = user.getUsername();
        device devices = deviceService.getdeviceById(id);
        model.addAttribute("username", username);
        model.addAttribute("device", devices);
        model.addAttribute("navMenu", "device");
        model.addAttribute("subMenu", "deviceshow");
        return "/jsp/upgrade/upgradeApply.jsp";

    }
    @ResponseBody
    @RequestMapping(value = "/addupgrade", method = RequestMethod.POST)
    public String upgradeAdd(upgradeApply apply, Model model, HttpServletRequest request) {
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        int userid = user.getId().intValue();
        apply.setUserId(userid);
        int said = upgradeService.addupgrade(apply);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "hlAppeal");
        model.addAttribute("subMenu", "hlAppealCreate");

        if (said >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }
    }
    @ResponseBody
    @RequestMapping(value = "/updateupgrade", method = RequestMethod.POST)
    public String updateupgrade(upgradeApply appplys, Model model, HttpServletRequest request) {
        // System.out.println(id);
        System.out.println(appplys);
        //appplys=faultService.getapplyById(id);
        boolean said = upgradeService.update(appplys);
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
        model.addAttribute("navMenu", "upgrade");
        model.addAttribute("subMenu", "upgradeView");
        return "/jsp/upgrade/upgradelist.jsp";
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String report(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerCreate");
        return "/jsp/upgrade/upgradeReort.jsp";

    }

    @ResponseBody
    @RequestMapping(value = "/addreport", method = RequestMethod.POST)
    public String reportAdd(upgradeApply apply, Model model, HttpServletRequest request) {

        int said = upgradeService.addupgrade(apply);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "hlAppeal");
        model.addAttribute("subMenu", "hlAppealCreate");
        if (said >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getAllapply", method = RequestMethod.GET)
    public JsonModel getAllapply(String page, String rows, String _search, String filters, HttpSession session, HttpServletRequest request) {
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        int userId = user.getId().intValue();
        int roleId = Integer.parseInt(user.getRoleIds());
        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            if (roleId == 1 || roleId == 2) {
                List<upgradeApply> applys;
                applys = upgradeService.getAllapply(page, rows);

                Long count = upgradeService.getCount();
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
                return jsonModel;
            } else {
                List<upgradeApply> applys;
                applys = upgradeService.getAllapply(page, rows, userId);
                Long count = upgradeService.getCount(userId);
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
            else {
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
            if (roleId == 2 || roleId == 3) {
                List<upgradeApply> applys;
                applys = upgradeService.getAllapply(page, rows, filtersClass);
                Long count = upgradeService.getCount(filtersClass);
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
                List<upgradeApply> applys;
                applys = upgradeService.getAllapply(page, rows, filtersClass, userId);
                Long count = upgradeService.getCount(filtersClass, userId);
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
        model.addAttribute("navMenu", "update");
        model.addAttribute("subMenu", "updatedeal");
        return "/jsp/upgrade/applylist.jsp";
    }

    @RequestMapping(value = "/upgradeLog/{id}", method = RequestMethod.GET)
    public String faultLog(@PathVariable("id") int id, Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        upgradeApply apply = upgradeService.getapplyById(id);
        model.addAttribute("apply", apply);
        model.addAttribute("navMenu", "upgrade");
        model.addAttribute("subMenu", "upgradeView");
        return "/jsp/upgrade/upgradeLog.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/addUpgradeLog", method = RequestMethod.POST)
    public String addUpgradeLog(upgraderepair logs, int applyId, Model model, HttpServletRequest request) {
        int said = upgradeService.addlog(logs);
        upgradeApply apply = upgradeService.getapplyById(applyId);
        /*System.out.println(applyId);
        System.out.println("asf" + apply);*/
        apply.setLogmark(1);
        upgradeService.update(apply);
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
        upgraderepair log = upgradeService.getlogByapplyID(applyId);
        System.out.println(log);
        model.addAttribute("log", log);
        model.addAttribute("navMenu", "update");
        model.addAttribute("subMenu", "updatedeal");
        return "/jsp/upgrade/showlog.jsp";
    }

    @ResponseBody
    @RequestMapping("/delete")
  /*  public String modify(device devices, @RequestParam("oper") String oper)*/
    public String delete(@RequestParam("id") String ids, @RequestParam("oper") String oper) {
        if (oper.equals("edit")) {
            //查看全部信息
            return "/jsp/equipment/addequipment.jsp";
        } else if (oper.equals("del")) {
            //删除
            System.out.println(ids);
            String[] idsList = ids.split(",");
            int deviceid;
            for (int i = 0; i < idsList.length; i++) {
                device devices = new device();
                deviceid = Integer.parseInt(idsList[i]);
                devices.setId(deviceid);
                //做关联删除
                deviceService.deldevice(devices);
            }

       /* } else if (oper.equals("del")) {
            //删除
            deviceService.deldevice(devices);
            List<Tappeal> Tappeals = appealService.getAppealId(devices.getId());
            appealService.delAppealByFarmerId(devices.getId());
            for (Tappeal t : Tappeals) {
                int count = appealService.getCountByUuid(t.getUuid());
                if (count <= 1) {
                    //删除诉求的备注
                    tappealmarkService.delAppealmarkByUuid(t.getUuid());
                }

            }*/

            return "/jsp/equipment/list.jsp";
        } else if (oper.equals("add")) {
            return "/jsp/equipment/addequipment.jsp";
        }
        return "/jsp/equipment/list.jsp";
    }

    @RequestMapping("/toviewpage/{id}")
    public String toviewpage(@PathVariable("id") int id, Model model) {
        device devices = deviceService.getdeviceById(id);
        model.addAttribute("device", devices);
//
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerView");
        return "/jsp/equipment/equipshow.jsp";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateDevice(device devices, Model model, HttpServletRequest request) {
        boolean fid = deviceService.update(devices);
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerView");
        if (fid) {
            model.addAttribute("addres", "1");
        } else {
            model.addAttribute("addres", "0");
        }
        return "/jsp/equipment/list.jsp";
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



}


