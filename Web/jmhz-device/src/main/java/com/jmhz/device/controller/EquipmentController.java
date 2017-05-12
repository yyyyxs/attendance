package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.model.*;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.pageModel.MqCard;
import com.jmhz.device.service.*;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.util.DownErweimaByUrlUtil;
import org.apache.log4j.Logger;
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
import java.util.UUID;

/**
 * Created by 锋情 on 2014/4/18.
 */
@Controller
@RequestMapping("/equipment")
public class EquipmentController extends BaseController {
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
    private deviceServiceI deviceService;
    @Autowired
    private upgradeServiceI upgradeService;
    @Autowired
    private AppealServiceI appealService;

    @Autowired
    private FarmerVisitServiceI farmerVisitService;
    @Autowired
    private TappealmarkServiceI tappealmarkService;

    Logger logger = Logger.getLogger(EquipmentController.class);

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String mqCardInput(Model model, HttpServletRequest request) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "equipmentmanage");
        model.addAttribute("subMenu", "equipmentadd");
        return "/jsp/equipment/addequipment.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/addequipment", method = RequestMethod.POST)
    public String deviceAdd(device devices, Model model, HttpServletRequest request) {
        ZXingUtil zxingutil = new ZXingUtil();
        deviceService.creatUUID(devices);
        String uuid = devices.getUUID();
        String path1 = servletContext.getRealPath("/");



        //start   by 彭巍 2016-11
        String tmppath = request.getSession().getServletContext().getRealPath("/assets/two-dimensioncode/");
        String paths[]=tmppath.split("\\\\");
        int j=-1;
        for(int i=0;i<paths.length;i++)
        {
            //System.out.println("split tmppath"+i+":"+paths[i]);
            //if(paths[i].equals("jmhz-device"))
            if(paths[i].equals("device"))//部署到服务器时用这个，device作为项目名
                j=i;
        }
        // System.out.println("j="+j);
        //
        String qstr="";
        for(int k=0;k<j;k++)
        {
            qstr=qstr+paths[k]+"\\";
        }
        //String path=qstr+"jmhz-device\\src\\main\\webapp\\assets\\erweima\\";
        String path=qstr+"device\\assets\\erweima\\";//部署到服务器时用这个，device作为项目名

        //end   by 彭巍 2016-11


        String localUrl = request.getRequestURL().toString();
        logger.debug("localUrl:" + localUrl);
        logger.debug("path:" + path);
        String codeUrl = localUrl + "two-dimensioncode/" + uuid + ".png";


        zxingutil.encodeQRCodeImage(uuid, null, path + uuid + ".png", 300, 300);
        devices.setCodeUrl(path + uuid + ".png");

        int said = deviceService.add(devices);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "equipmentmanage");
        model.addAttribute("subMenu", "equipmentadd");
        if (said >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String farmerShow(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "equipmentmanage");
        model.addAttribute("subMenu", "equipmentlist");

        return "/jsp/equipment/list.jsp";
    }


    @ResponseBody
    @RequestMapping(value = "/getAlldevice", method = RequestMethod.GET)
    public JsonModel getAlldevice(String page, String rows, String _search, String filters, HttpSession session, HttpServletRequest request) {
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        int userId = user.getId().intValue();
        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            List<device> devices;
            devices = deviceService.getAlldevice(page, rows);
            Long count = deviceService.getCount();
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(devices);
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

            }
            List<device> devices = new ArrayList<device>();
            devices = deviceService.getAlldevice(page, rows, filtersClass);
            Long count = deviceService.getCount(filtersClass);
           int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(devices);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(Integer.parseInt(page));
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(Integer.parseInt(rows));
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }

    }

    @RequestMapping(value = "/upgrade", method = RequestMethod.GET)
    public String upgradeapply(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerCreate");
        return "/jsp/upgrade/upgradeApply.jsp";

    }

    @ResponseBody
    @RequestMapping(value = "/addupgrade", method = RequestMethod.POST)
    public String upgradeAdd(upgradeApply apply, Model model, HttpServletRequest request) {
        System.out.println("hanjaimin");
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
        model.addAttribute("navMenu", "equipmentmanage");
        model.addAttribute("subMenu", "equipmentlist");
        return "/jsp/equipment/equipmentshow.jsp";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateDevice(device devices, Model model, HttpServletRequest request) {

        boolean fid = deviceService.update(devices);
//        device devicess=new device();

        model.addAttribute("navMenu", "equipmentmanage");
        model.addAttribute("subMenu", "equipmentlist");
        if (fid) {
            model.addAttribute("addres", "1");
        } else {
            model.addAttribute("addres", "0");
        }
        return "/jsp/equipment/equipmentshow.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public Boolean falsedelete(@RequestParam(value="id") int id, Model model) {
        device devices = deviceService.getdeviceById(id);
        devices.setDeleteFlag(1);
        boolean fid = deviceService.update(devices);
        return fid;
    }

    @ResponseBody
    @RequestMapping(value = "getEquipmentById", method = RequestMethod.POST)
    public JsonModel getequipmentById(int id, Model model) {
        JsonModel jsonModel = new JsonModel();
        device device = deviceService.getdeviceById(id);
        jsonModel.setDataObj(device);

        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "downEeweima", method = RequestMethod.POST)
    public JsonModel downEeweima(int id, Model model) {
        JsonModel jsonModel = new JsonModel();
        device device = deviceService.getdeviceById(id);

        jsonModel.setDataObj(device);
//        String url="http://localhost:8080/assets/erweima/"+device.getUUID()+".png";
        String url = "http://59.77.236.53/:8090/device/assets/erweima/" + device.getUUID() + ".png";// 服务器上用这个,需要修改url
//        http://218.193.126.85:8090

        DownErweimaByUrlUtil.downloadImgByUrl(url);

        return jsonModel;
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


