package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.model.Tappeal;
import com.jmhz.device.model.Tappealmark;
import com.jmhz.device.model.Tgroup;
import com.jmhz.device.model.Tvillage;
import com.jmhz.device.pageModel.*;
import com.jmhz.device.service.AppealServiceI;
import com.jmhz.device.service.TappealmarkServiceI;
import com.jmhz.device.service.VillageDataExportServiceI;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.service.GroupServiceI;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupServiceI groupService;
    @Autowired
    private AppealServiceI appealService;
    @Autowired
    private TappealmarkServiceI tappealmarkService;
    @Autowired
    private VillageDataExportServiceI villageDataExportService;

    @Autowired
    private ServletContext servletContext;

    private String contextRootPath;

    @PostConstruct
    public void afterPropertiesSet() {
        contextRootPath = servletContext.getRealPath("/");
    }

    @RequestMapping(value = "/mqgroupcard", method = RequestMethod.GET)
    public String mqGroupCardInput(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "group");
        model.addAttribute("subMenu", "groupCreate");
        return "/jsp/group/mqgroupcard.jsp";
    }

    @RequestMapping(value = "/showw", method = RequestMethod.GET)
    public String mqGroupAppeal(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "group");
        model.addAttribute("subMenu", "groupView");
        return "/jsp/appeal/list.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/addgroupcard", method = RequestMethod.POST)
    public String addGroupCard(MqGroupCard mqGroupCard, Model model,HttpServletRequest request) {
        //int aid = -1;
        String res = "0";
        if (groupService.getGroupByName(mqGroupCard.getGroupname()) == null) {
            Tgroup tgroup = new Tgroup();
            Tappeal tappeal = new Tappeal();
//            BeanUtils.copyProperties(mqGroupCard, tgroup);
//            BeanUtils.copyProperties(mqGroupCard, tappeal);
            tgroup.setGroupname(mqGroupCard.getGroupname());
            tgroup.setGroupchargername(mqGroupCard.getGroupchargername());
            tgroup.setGroupchargertel(mqGroupCard.getGroupchargertel());
            //将账号的县、镇信息获取
            User user = (User) request.getAttribute(Constants.CURRENT_USER);
            tgroup.setCity(user.getCity());
            tgroup.setTown(user.getTown());

            int gid = -1;
            gid = groupService.add(tgroup);
            if (!mqGroupCard.getHardshipappeal().equals("")) {
                tappeal.setCity(user.getCity());
                tappeal.setTown(user.getTown());
                tappeal.setAppealid(gid);
                tappeal.setUuid(mqGroupCard.getUuid());
                tappeal.setProposer("1");
                tappeal.setAffairtype(mqGroupCard.getAffairtype());
                tappeal.setAppealtype(mqGroupCard.getAppealtype());
                tappeal.setAppealname(tgroup.getGroupname());
                tappeal.setStatus("0");
                tappeal.setHardshipappeal(mqGroupCard.getHardshipappeal());
                SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
                tappeal.setCreatedate(format.format(new Date()));
                tappeal.setAppealtel(mqGroupCard.getGroupchargertel());
                appealService.add(tappeal);
            }

            if (gid >= 0) {
                res = "1";
            } else {
                res = "2";
            }

            return res;
        } else {
            return res;
        }

        //return "/jsp/group/mqgroupcard.jsp";
    }

    @RequestMapping(value = "/showww", method = RequestMethod.GET)
    public String mqGroupCardShowww(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "group");
        model.addAttribute("subMenu", "groupView");
        return "/jsp/group/list.jsp";
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String mqGroupCardShow(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "group");
        model.addAttribute("subMenu", "groupView");
        return "/jsp/group/list.jsp";
    }

    @RequestMapping(value = "/getAllGroup", method = RequestMethod.GET)
    @ResponseBody
    public JsonModel getAllGroups(String page, String rows, String _search, String filters) {
        JsonModel jsonModel = new JsonModel();
        if (_search.equals("false")) {
            List<Tgroup> tgroups;
            tgroups = groupService.getAllGroup(page, rows);
            Long count = groupService.getCount();
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tgroups);
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
            List<Tgroup> tgroups;
            // tgroups = groupService.getAllGroup(page, rows);
            tgroups = groupService.getAllGroup(page, rows, filtersClass);
            Long count = groupService.getCount();
            int pages = count.intValue() / Integer.parseInt(rows) + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tgroups);
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
    public String modify(MqGroupCard mqGroupCard, @RequestParam("oper") String oper) {
        Tgroup tgroup = new Tgroup();
        BeanUtils.copyProperties(mqGroupCard, tgroup);

        if (oper.equals("edit")) {
            //更新
            //连带更新诉求中农户的信息
            List<Tappeal> tappeals =  appealService.getAppealIdByGroup(tgroup.getId());
            groupService.updateTgroup(tgroup);
            for (Tappeal appeal : tappeals){
                appeal.setAppealname(tgroup.getGroupname());
                //appeal.setVillage(appealGroupStatus.get);
                appeal.setAppealtel(tgroup.getGroupchargertel());
                appealService.update(appeal);
            }
            return "/jsp/group/list.jsp";
        } else if (oper.equals("del")) {
            //删除
            groupService.delTgroup(tgroup);
            List<Tappeal> Tappeals = appealService.getAppealIdByGroup(tgroup.getId());
            appealService.delAppealByGroupId(tgroup.getId());
            for (Tappeal t : Tappeals){
                int count = appealService.getCountByUuid(t.getUuid());
                if (count <= 1){
                    //删除诉求的备注
                    tappealmarkService.delAppealmarkByUuid(t.getUuid());
                }

            }
            return "/jsp/group/list.jsp";
        } else if (oper.equals("add")) {
            groupService.add(tgroup);
            return "/jsp/group/list.jsp";
        }
        return "/jsp/group/list.jsp";

    }

    @RequestMapping(value = "/addAppeal", method = RequestMethod.POST)
    public String addAppeal(@RequestParam(value = "id") String groupid,
                            @RequestParam(value = "uuid") String uuid,
                            @RequestParam(value = "groupname") String groupname,
                            @RequestParam(value = "appealtype") String appealtype,
                            @RequestParam(value = "affairtype") String affairtype,
                            @RequestParam(value = "groupchargertel") String groupchargertel,
                            @RequestParam(value = "hardshipappeal") String hardshipappeal,
                            Model model,HttpServletRequest request) {
        Tappeal tappeal = new Tappeal();
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        tappeal.setCity(user.getCity());
        tappeal.setTown(user.getTown());
        tappeal.setAppealid(Integer.parseInt(groupid));
        tappeal.setUuid(uuid);
        tappeal.setAppealname(groupname);
        tappeal.setProposer("1");
        tappeal.setAppealtype(appealtype);
        tappeal.setAffairtype(affairtype);
        tappeal.setStatus("0");
        tappeal.setHardshipappeal(hardshipappeal);
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        tappeal.setCreatedate(format.format(new Date()));
        tappeal.setAppealtel(groupchargertel);
        appealService.add(tappeal);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appeal");
        model.addAttribute("subMenu", "appealView");
        return "/jsp/appeal/list.jsp";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateAppeal(AppealGroupStatus appealGroupStatus) {
        Tappeal tappeal = new Tappeal();
        if (appealGroupStatus.getGroupname().equals(appealGroupStatus.getGroupnamehidden())) {
            tappeal.setId(Integer.parseInt(appealGroupStatus.getId()));
            tappeal.setUuid(appealGroupStatus.getUuid());
            tappeal.setAppealid(Integer.parseInt(appealGroupStatus.getGroupidhidden()));
            tappeal.setAppealname(appealGroupStatus.getGroupname());
            tappeal.setProposer("1");
            tappeal.setAppealtype(appealGroupStatus.getAppealtype());
            tappeal.setAffairtype(appealGroupStatus.getAffairtype());
            tappeal.setStatus(appealGroupStatus.getStatus());
            tappeal.setDoingstatus(appealGroupStatus.getDoingstatus());
            tappeal.setDoingstatus(appealGroupStatus.getDoingstatus());
            tappeal.setHardshipappeal(appealGroupStatus.getHardshipappeal());
            tappeal.setDutyleader(appealGroupStatus.getDutyleader());
            tappeal.setDutydept(appealGroupStatus.getDutydept());
            tappeal.setDutymenber(appealGroupStatus.getDutymenber());
            tappeal.setSolution(appealGroupStatus.getSolution());
            tappeal.setTimelimit(appealGroupStatus.getTimelimit());
            tappeal.setCreatedate(appealGroupStatus.getCreatedate());
            tappeal.setAppealtel(appealGroupStatus.getGroupchargertel());
        } else {
            Tgroup tgroup = groupService.getGroupByName(appealGroupStatus.getGroupname());
            if (tgroup == null) {
                return "0";//该用户不存在
            }
            tappeal.setId(Integer.parseInt(appealGroupStatus.getId()));
            tappeal.setUuid(appealGroupStatus.getUuid());
            tappeal.setAppealid(tgroup.getId());
            tappeal.setAppealname(tgroup.getGroupname());
            tappeal.setProposer("1");
            tappeal.setAppealtype(appealGroupStatus.getAppealtype());
            tappeal.setAffairtype(appealGroupStatus.getAffairtype());
            tappeal.setStatus(appealGroupStatus.getStatus());
            tappeal.setDoingstatus(appealGroupStatus.getDoingstatus());
            tappeal.setHardshipappeal(appealGroupStatus.getHardshipappeal());
            tappeal.setDutyleader(appealGroupStatus.getDutyleader());
            tappeal.setDutydept(appealGroupStatus.getDutydept());
            tappeal.setDutymenber(appealGroupStatus.getDutymenber());
            tappeal.setSolution(appealGroupStatus.getSolution());
            tappeal.setTimelimit(appealGroupStatus.getTimelimit());
            tappeal.setCreatedate(appealGroupStatus.getCreatedate());
            tappeal.setAppealtel(appealGroupStatus.getGroupchargertel());
        }

        appealService.update(tappeal);

        //添加记录到tapplealmark 表里面
        if (!appealGroupStatus.getProcessupdateremark().equals("") && !appealGroupStatus.getProcessupdatetime().equals("")) {
            Tappealmark tappealmark = new Tappealmark();
            tappealmark.setAppealid(Integer.parseInt(appealGroupStatus.getId()));
            tappealmark.setUuid(appealGroupStatus.getUuid());
            tappealmark.setHouseholdername(appealGroupStatus.getGroupname());
            tappealmark.setProcessupdatetime(appealGroupStatus.getProcessupdatetime());
            tappealmark.setProcessupdateremark(appealGroupStatus.getProcessupdateremark());
            tappealmarkService.add(tappealmark);
            tappealmarkService.update(tappealmark);
        }

        //陈鑫 end

        return "1";
    }



//    村情模块

     @RequestMapping(value = "/villageinfo", method = RequestMethod.GET)
      public String villageInfoInput(Model model) {
    //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
       model.addAttribute("navMenu", "group");
       model.addAttribute("subMenu", "villageInfoCreate");
       return "/jsp/group/villageInfo.jsp";
     }

    @RequestMapping(value = "/addVillageinfo",method = RequestMethod.POST)
    public String villageInfoAdd(VillageInfo villageInfo,Model model,HttpServletRequest request){
        //根据村名，判断用户是否存在
        if(groupService.getVillage(villageInfo.getVillage())==null)
        {
            Tvillage tvillage=new Tvillage();
            BeanUtils.copyProperties(villageInfo,tvillage);

            //将账号的县、镇信息获取
            User user = (User) request.getAttribute(Constants.CURRENT_USER);
            tvillage.setCity(user.getCity());
            tvillage.setTown(user.getTown());

            int fid=groupService.addv(tvillage);
            if(fid>=0){
                model.addAttribute("addres", "1");
            }else{
                model.addAttribute("addres", "0");
            }

        }else{
            model.addAttribute("addres", "2");
        }

//     设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "group");
        model.addAttribute("subMenu", "villageInfoCreate");
        return "/jsp/group/villageInfo.jsp";
    }

    @RequestMapping(value = "/villageshow",method = RequestMethod.GET)
    public String villageshow(Model model){
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu","group");
        model.addAttribute("subMenu", "villageInfoView");
         return "/jsp/group/villagelist.jsp";

    }

   @ResponseBody
   @RequestMapping(value="/getAllVillage",method = RequestMethod.GET)
   public JsonModel getAllVillage(String page,String rows,String _search,String filters,HttpSession session){
       JsonModel jsonModel=new JsonModel();
       if (_search.equals("false")) {
           List<Tvillage> villages;
           villages = groupService.getAllVillage(page, rows);
           Long count = groupService.getvillageCount();
           int pages = count.intValue() / Integer.parseInt(rows) + 1;
           jsonModel.setSuccess(true);
           jsonModel.setMsg("success");
           jsonModel.setDataObj(villages);
           jsonModel.setTotalpages(pages);
           jsonModel.setCurrentpage(Integer.parseInt(page));
           jsonModel.setTotalrecords(count.intValue());
           jsonModel.setPagerows(Integer.parseInt(rows));
           jsonModel.setSort("");
           jsonModel.setOrder("");
           return jsonModel;
       } else {
           //查询
           session.setAttribute("groupFilters", filters);
           filters = filters.replace("&quot;", "\"");
           Filters filtersClass = JSON.parseObject(filters, Filters.class);
           List<Tvillage> villages = new ArrayList<Tvillage>();
           villages = groupService.getAllVillage(page, rows, filtersClass);

           Long count = groupService.getCount(filtersClass);
           int pages = count.intValue() / Integer.parseInt(rows) + 1;
           jsonModel.setSuccess(true);
           jsonModel.setMsg("success");
           jsonModel.setDataObj(villages);
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
    @RequestMapping("/villagemodify")
    public String villagemodify(Tvillage tvillage, @RequestParam("oper") String oper) {
        if (oper.equals("edit")) {
            //查看全部信息
            return "/jsp/group/villageInfo.jsp";
        } else if (oper.equals("del")) {
            //删除
            groupService.delVillage(tvillage);
            return "/jsp/group/villagelist.jsp";
        } else if (oper.equals("add")) {
            return "/jsp/group/villageInfo.jsp";
        }
        return "/jsp/group/villagelist.jsp";
    }

    @RequestMapping("/villagetoviewpage/{id}")
    public String villagetoviewpage(@PathVariable("id") int id, Model model) {
        Tvillage tvillage = groupService.getVillageById(id);
        model.addAttribute("village", tvillage);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "group");
        model.addAttribute("subMenu", "villageInfoView");
        return "/jsp/group/villagesShow.jsp";
    }

    @RequestMapping(value = "/villageupdate", method = RequestMethod.POST)
    public String updateVillage(VillageInfo villageInfo, Model model) {
        Tvillage tvillage = new Tvillage();
        BeanUtils.copyProperties(villageInfo,tvillage);
       groupService.update(tvillage);
           model.addAttribute("updateRes","1");
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "group");
        model.addAttribute("subMenu", "villageInfoView");
        return "/jsp/group/villagelist.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public JsonModel exportExcel(Model model,HttpSession session) throws IOException {
        //按条件导出
        if (session.getAttribute("villageFilters") != null)
        {

            String filters = session.getAttribute("villageFilters").toString();
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++)
            {
                //性别
                String curFilterRuleField = filtersClass.getRules().get(i).getField();
                String curFilterRuleData = filtersClass.getRules().get(i).getData();
            }
            villageDataExportService.exportExcel2007(contextRootPath, filtersClass);
        }
        //无条件导出
        villageDataExportService.exportExcel2007(contextRootPath);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }


}

