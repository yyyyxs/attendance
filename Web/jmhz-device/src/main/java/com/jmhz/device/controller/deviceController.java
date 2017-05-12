package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.model.Tappeal;
import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.model.device;
import com.jmhz.device.model.ZXingUtil;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.*;
import com.jmhz.device.model.FaultApply;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.util.DownErweimaByUrlUtil;
import org.apache.log4j.Logger;
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
import java.util.UUID;

/**
 * Created by  林炜 on 2014/4/18.
 */

@Controller
@RequestMapping("/devicemg")
public class deviceController extends BaseController {
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
    private FarmerServiceI farmerService;

    @Autowired
    private AppealServiceI appealService;

    @Autowired
    private FarmerVisitServiceI farmerVisitService;
    @Autowired
    private TappealmarkServiceI tappealmarkService;

    Logger logger = Logger.getLogger(deviceController.class);

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addjsp(Model model, HttpServletRequest request) {
        //获取用户id
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        String username = user.getUsername();
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("username", username);
        model.addAttribute("navMenu", "device");
        model.addAttribute("subMenu", "deviceadd");
        return "/jsp/devicemg/add.jsp";
    }
    @ResponseBody
    @RequestMapping(value = "/adddevice", method = RequestMethod.POST)
    public String deviceAdd(device devices, Model model, HttpServletRequest request) {
        ZXingUtil zxingutil = new ZXingUtil();
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        devices.setUserId(user.getId().intValue());
        deviceService.creatUUID(devices);
        String uuid = devices.getUUID();
        //String path = servletContext.getRealPath("/");


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


        String localUrl = request.getRequestURL().toString().split("d")[0];

        String codeUrl = localUrl + "two-dimensioncode/" + uuid + ".png";
        zxingutil.encodeQRCodeImage(uuid, null, path + "two-dimensioncode/" + uuid + ".png", 300, 300);
        devices.setCodeUrl(codeUrl);

        int said = deviceService.add(devices);
//        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "device");
        model.addAttribute("subMenu", "deviceadd");
        if (said >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String farmerShow(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "device");
        model.addAttribute("subMenu", "showdevice");
        return "/jsp/devicemg/list.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/getAlldevice", method = RequestMethod.GET)
    public JsonModel getAlldevice(String page, String rows, String _search, String filters, HttpSession session, HttpServletRequest request) {
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        int userId = user.getId().intValue();
        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            List<device> devices;
            devices = deviceService.getAlldevice(page, rows, userId);

            Long count = deviceService.getCount(userId);
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
            devices = deviceService.getAlldevice(page, rows, userId, filtersClass);
            Long count = deviceService.getCount(filtersClass, userId);
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

    @ResponseBody
    @RequestMapping(value = "downEeweima", method = RequestMethod.POST)
    public JsonModel downEeweima(int id, Model model) {
        JsonModel jsonModel = new JsonModel();
        device device = deviceService.getdeviceById(id);

        jsonModel.setDataObj(device);
//        String url="http://localhost:8080/assets/erweima/"+device.getUUID()+".png";
        String url = "http://59.77.236.53:8090/0/device/assets/erweima/" + device.getUUID() + ".png";// 服务器上用这个,需要修改url
//        http://218.193.126.85:8090

        DownErweimaByUrlUtil.downloadImgByUrl(url);

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
    public JsonModel delete(device devices) {

        deviceService.deldevice(devices);

        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }
    @RequestMapping("/toviewpage/{id}")
    public String toviewpage(@PathVariable("id") int id, Model model) {
        device devices = deviceService.getdeviceById(id);
        model.addAttribute("device", devices);
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
        model.addAttribute("navMenu", "device");
        model.addAttribute("subMenu", "deviceshow");
        return "/jsp/devicemg/deviceshow.jsp";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateDevice(device devices, Model model, HttpServletRequest request) {

//        BeanUtils.copyProperties(mqCard, tfarmer);
//        //将账号的县、镇信息获取
//        User user = (User) request.getAttribute(Constants.CURRENT_USER);
//        tfarmer.setCity(user.getCity());
//        tfarmer.setTown(user.getTown());

        //连带更新诉求中农户的信息
//        List<Tappeal> tappeals = appealService.getAppealId(tfarmer.getId());
        boolean fid = deviceService.update(devices);

//        for (Tappeal tappeal : tappeals) {
//            tappeal.setAppealname(tfarmer.getHouseholdername());
//            tappeal.setVillage(tfarmer.getVillage());
//            tappeal.setAppealtel(tfarmer.getContactnumber());
//            appealService.update(tappeal);
//        }
//        if (!mqCard.getGridcharger().equals(mqCard.getHiddengridcharger())) {
//            farmerService.updateGridCharger(tfarmer);
//        }

        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "farmer");
        model.addAttribute("subMenu", "farmerView");
        if (fid) {
            model.addAttribute("addres", "1");
        } else {
            model.addAttribute("addres", "0");
        }
        return "/jsp/devicemg/deviceshow.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "getdeviceById", method = RequestMethod.POST)
    public JsonModel getdeviceById(int id, Model model) {
        JsonModel jsonModel = new JsonModel();
        device device = deviceService.getdeviceById(id);
        jsonModel.setDataObj(device);
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


