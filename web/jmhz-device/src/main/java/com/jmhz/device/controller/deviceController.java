package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;

import com.jmhz.device.model.device;
import com.jmhz.device.model.ZXingUtil;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.*;

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
    private deviceServiceI deviceService;


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



}


