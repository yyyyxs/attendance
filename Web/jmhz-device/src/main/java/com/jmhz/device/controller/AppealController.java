package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.model.*;
import com.jmhz.device.pageModel.AppealStatus;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.*;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.util.SMSUtils;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 锋情 on 2014/4/19.
 */
@Controller
@RequestMapping("/appeal")
public class AppealController extends BaseController {

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
    private AppealDataExportServiceI appealDataExportService;

    @Autowired
    private AppealServiceI appealService;

    @Autowired
    private FarmerServiceI farmerService;

    @Autowired
    private GroupServiceI groupService;

    @Autowired
    private TappealmarkServiceI tappealmarkService;

    @Autowired
    private AppealRateServiceI appealRateService;

    @Autowired
    private SMSSentServiceI smsSentService;

    @Autowired
    private SMSTemplateServiceI smsTemplateService;

    @RequestMapping(value = "/show/{source}", method = RequestMethod.GET)
    public String mqGroupCardInput(@PathVariable("source") String source,Model model) {

        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        if ("all".equals(source)){
            model.addAttribute("navMenu", "appeal");
            model.addAttribute("subMenu", "appealView");
        }else {
            model.addAttribute("navMenu", "appeal");
            model.addAttribute("subMenu", "appealTownView");
            model.addAttribute("subMenuSubMenu", "appealTownView"+source);
        }

        model.addAttribute("source", source);

        //设置一些下拉菜单数据
        setCommandData(model);
        return "/jsp/appeal/list.jsp";
    }

    @RequestMapping(value = "/verifyinput", method = RequestMethod.GET)
    public String verifyInput(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appeal");
        model.addAttribute("subMenu", "appealCreate");
        return "/jsp/appeal/verifyinput.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllAppeal/{townSource}", method = RequestMethod.GET)
    public JsonModel getAllAppeal(@PathVariable("townSource") String townSource,int page, int rows, boolean _search, String filters, Model model, HttpSession session) {

        session.setAttribute("townSource", townSource);
        JsonModel jsonModel = new JsonModel();
        if (!_search) {

            //设置搜索属性到model
            model.addAttribute("curPage", page);
            model.addAttribute("curRows", rows);
            model.addAttribute("curIsSearch", _search);
            model.addAttribute("curFilters", filters);
            session.setAttribute("curFilters", filters);
            List<Tappeal> appeals;
            appeals = appealService.getAllAppeal(page, rows);//获取列表

            Long count = appealService.getCount();
            int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(appeals);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(page);
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(rows);
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        } else {
            //设置搜索属性到model
            model.addAttribute("curPage", page);
            model.addAttribute("curRows", rows);
            model.addAttribute("curIsSearch", _search);
            model.addAttribute("curFilters", filters);
            session.setAttribute("curFilters", filters);
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++) {
                //诉求来源
                String curFilterRuleField = filtersClass.getRules().get(i).getField();
                String curFilterRuleData = filtersClass.getRules().get(i).getData();
                if (curFilterRuleField.equals("proposer")) {
                    if (curFilterRuleData.equals("个人提出")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        //group
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //诉求类别
                if (curFilterRuleField.equals("appealtype")) {
                    if (curFilterRuleData.equals("发展生产")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (curFilterRuleData.equals("基础设施")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (curFilterRuleData.equals("矛盾纠纷")) {
                        filtersClass.getRules().get(i).setData("2");
                    } else if (curFilterRuleData.equals("社会治安")) {
                        filtersClass.getRules().get(i).setData("3");
                    } else if (curFilterRuleData.equals("生活救助")) {
                        filtersClass.getRules().get(i).setData("4");
                    } else if (curFilterRuleData.equals("征地拆迁")) {
                        filtersClass.getRules().get(i).setData("5");
                    } else if (curFilterRuleData.equals("政策咨询")) {
                        filtersClass.getRules().get(i).setData("6");
                    }else if (curFilterRuleData.equals("证照办理")) {
                        filtersClass.getRules().get(i).setData("7");
                    }else if (curFilterRuleData.equals("群众投诉")) {
                        filtersClass.getRules().get(i).setData("8");
                    }else if (curFilterRuleData.equals("其他")) {
                        filtersClass.getRules().get(i).setData("9");
                    }
                }
                //事务类别
                if (curFilterRuleField.equals("affairtype")) {
                    if (curFilterRuleData.equals("个人事务")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        //group
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //状态
                if (curFilterRuleField.equals("status")) {
                    if (curFilterRuleData.equals("未解决")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (curFilterRuleData.equals("以上报上级协调解决")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (curFilterRuleData.equals("延时解决")) {
                        filtersClass.getRules().get(i).setData("2");
                    } else if (curFilterRuleData.equals("正在解决")) {
                        filtersClass.getRules().get(i).setData("3");
                    } else if (curFilterRuleData.equals("已做好解释说明工作")) {
                        filtersClass.getRules().get(i).setData("4");
                    } else if (curFilterRuleData.equals("已解决")) {
                        filtersClass.getRules().get(i).setData("5");
                    }
                }


            }
            List<Tappeal> appeals;
            appeals = appealService.getAllAppeal(page, rows, filtersClass);//获取列表

            Long count = appealService.getCount(filtersClass);
            int pages = count.intValue() / rows + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(appeals);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(page);
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(rows);
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }
    }

    @RequestMapping("/toviewpage/{id}")
    public String toviewpage(@PathVariable("id") int id, Model model) {
        Tappeal tappeal = appealService.getAppealById(id);
        Tfarmer tfarmer;
        Tgroup tgroup;
        if (tappeal.getProposer().equals("0")) {
            //农户数据
            tfarmer = farmerService.getFarmerById(tappeal.getAppealid());
            model.addAttribute("appeal", tappeal);
            model.addAttribute("farmer", tfarmer);
            //model.addAttribute("tappealmarks", tappealmarkService.getAllAppealmark(id));
            //根据uuid获取诉求备注
            model.addAttribute("tappealmarks", tappealmarkService.getAppealmarkByUuid(tappeal.getUuid()));
            model.addAttribute("size", tappealmarkService.getAppealmarkByUuid(tappeal.getUuid()).size());
            //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
            model.addAttribute("navMenu", "appeal");
            model.addAttribute("subMenu", "appealView");
            return "/jsp/appeal/appealshow.jsp";// 跳转到个人诉求状态界面
        } else {
            //集体数据
            tgroup = groupService.getGroupById(tappeal.getAppealid());
            model.addAttribute("appeal", tappeal);
            model.addAttribute("group", tgroup);
            model.addAttribute("tappealmarks", tappealmarkService.getAppealmarkByUuid(tappeal.getUuid()));
            model.addAttribute("size", tappealmarkService.getAppealmarkByUuid(tappeal.getUuid()).size());
            //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
            model.addAttribute("navMenu", "appeal");
            model.addAttribute("subMenu", "appealView");
            return "/jsp/group/mqgroupstatus.jsp";// 跳转到个人诉求状态界面
        }

    }

    @ResponseBody
    @RequestMapping("/modify")
    public String modify(Tappeal tappeal, @RequestParam("oper") String oper) {
        if (oper.equals("edit")) {
            //查看全部信息
            return "/jsp/appeal/list.jsp";
        } else if (oper.equals("del")) {
            //删除

            appealService.delAppeal(tappeal);
            return "/jsp/appeal/list.jsp";
        } else if (oper.equals("add")) {
            return "/jsp/appeal/list.jsp";
        }
        return "/jsp/appeal/list.jsp";
    }

    @ResponseBody
    @RequestMapping("/delete")
    public JsonModel deleteAppeal(Tappeal tappeal) {

        int count = appealService.getCountByUuid(tappeal.getUuid());
        if (count <= 1) {
            //删除诉求的备注
            tappealmarkService.delAppealmarkByUuid(tappeal.getUuid());
        }

        appealService.delAppeal(tappeal);

        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateAppeal(AppealStatus appealStatus, Model model, HttpServletRequest request) {
        Tappeal tappeal = new Tappeal();
        if (appealStatus.getHouseholdername().equals(appealStatus.getHouseholdernamehidden())) {
            tappeal.setId(appealStatus.getId());
            tappeal.setUuid(appealStatus.getUuid());
            tappeal.setAppealid(Integer.parseInt(appealStatus.getHouseholderidhidden()));
            tappeal.setAppealname(appealStatus.getHouseholdername());
            //
            User user = (User) request.getAttribute(Constants.CURRENT_USER);
            tappeal.setCity(user.getCity());
            tappeal.setTown(user.getTown());
            tappeal.setVillage(appealStatus.getAppealvillage());
            tappeal.setProposer("0");
            tappeal.setAppealtype(appealStatus.getAppealtype());
            tappeal.setAffairtype(appealStatus.getAffairtype());
            tappeal.setStatus(appealStatus.getStatus());
            tappeal.setDoingstatus(appealStatus.getDoingstatus());
            tappeal.setHardshipappeal(appealStatus.getHardshipappeal());
            tappeal.setDutyleader(appealStatus.getDutyleader());
            tappeal.setDutydept(appealStatus.getDutydept());
            tappeal.setDutymenber(appealStatus.getDutymenber());
            tappeal.setSolution(appealStatus.getSolution());
            tappeal.setTimelimit(appealStatus.getTimelimit());
            tappeal.setCreatedate(appealStatus.getCreatedate());
            tappeal.setAppealtel(appealStatus.getContactnumber());
            tappeal.setPublish(appealStatus.getPublish());
        } else {
            Tfarmer tfarmer = farmerService.getFarmerByName(appealStatus.getHouseholdername());
            if (tfarmer == null) {
                return "0";//0 该农户不存在
            }
            tappeal.setId(appealStatus.getId());//TODO: 后台报错，需要处理,NumberFormatException: null
            tappeal.setUuid(appealStatus.getUuid());
            tappeal.setAppealid(tfarmer.getId());
            tappeal.setAppealname(tfarmer.getHouseholdername());
            //
            User user = (User) request.getAttribute(Constants.CURRENT_USER);
            tappeal.setCity(user.getCity());
            tappeal.setTown(user.getTown());
            tappeal.setVillage(appealStatus.getAppealvillage());
            tappeal.setProposer("0");
            tappeal.setAppealtype(appealStatus.getAppealtype());
            tappeal.setAffairtype(appealStatus.getAffairtype());
            tappeal.setStatus(appealStatus.getStatus());
            tappeal.setDoingstatus(appealStatus.getDoingstatus());
            tappeal.setHardshipappeal(appealStatus.getHardshipappeal());
            tappeal.setDutyleader(appealStatus.getDutyleader());
            tappeal.setDutydept(appealStatus.getDutydept());
            tappeal.setDutymenber(appealStatus.getDutymenber());
            tappeal.setSolution(appealStatus.getSolution());
            tappeal.setTimelimit(appealStatus.getTimelimit());
            tappeal.setCreatedate(appealStatus.getCreatedate());
            tappeal.setAppealtel(appealStatus.getContactnumber());
            tappeal.setPublish(appealStatus.getPublish());
        }

        appealService.update(tappeal);
        appealService.synchronizationUpdate(tappeal);//同步多个诉求
        if (!appealStatus.getProcessupdateremark().equals("") && !appealStatus.getProcessupdatetime().equals("")) {
            //陈鑫 begin  添加记录到tapplealmark 表里面
            Tappealmark tappealmark = new Tappealmark();
            tappealmark.setAppealid(appealStatus.getId());
            tappealmark.setUuid(appealStatus.getUuid());
            tappealmark.setHouseholdername(appealStatus.getHouseholdername());
            tappealmark.setProcessupdatetime(appealStatus.getProcessupdatetime());
            tappealmark.setProcessupdateremark(appealStatus.getProcessupdateremark());
            tappealmarkService.add(tappealmark);
            //boolean markres = tappealmarkService.update(tappealmark);
            //陈鑫 end
        }
        //model.addAttribute("updateRes","1");
        return "1";
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public String verifyAddAppeal(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "phone") String phone,
                                  @RequestParam(value = "proposer") String proposer,
                                  Model model) {
        if (proposer.equals("0")) {
            //添加个人诉求
            //判断该农户是否已经存在
            Tfarmer tfarmer = new Tfarmer();
            if (phone.equals("")) {
                //没有输入手机号
                tfarmer = farmerService.getFarmer(name);
            } else {
                tfarmer = farmerService.getFarmer(name, phone);
            }

            if (tfarmer == null) {
                //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
                model.addAttribute("navMenu", "farmer");
                model.addAttribute("subMenu", "farmerCreate");
                //该用户不存在，跳转至农户添加界面
                model.addAttribute("verifyRes", "0");
                model.addAttribute("verifyName", name);
                model.addAttribute("verifyPhone", phone);
                return "/jsp/farmer/mqcard.jsp";
            } else {
                //用户存在，跳转至诉求添加界面
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
                //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
                model.addAttribute("navMenu", "appeal");
                model.addAttribute("subMenu", "appealCreate");
                return "/jsp/appeal/addinput.jsp";
            }
        } else {
            //添加集体诉求
            //判断该集体是否已经存在
            Tgroup tgroup = groupService.getGroupByName(name);
            if (tgroup == null) {
                //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
                model.addAttribute("navMenu", "group");
                model.addAttribute("subMenu", "groupCreate");
                //该用户不存在，跳转至添加集体
                model.addAttribute("verifyRes", "0");
                model.addAttribute("verifyName", name);
                return "/jsp/group/mqgroupcard.jsp";
            } else {
                //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
                model.addAttribute("navMenu", "appeal");
                model.addAttribute("subMenu", "appealView");
                //用户存在，跳转至诉求添加界面
                model.addAttribute("group", tgroup);
                return "/jsp/group/addgroupinput.jsp";
            }
        }
        // return "/jsp/appeal/verifyinput.jsp";
    }

    @RequestMapping(value = "/addAppeal", method = RequestMethod.POST)
    @ResponseBody
    public String addAppeal(@RequestParam(value = "id") String farmerid,
                            @RequestParam(value = "uuid") String uuid,
                            @RequestParam(value = "village") String village,
                            @RequestParam(value = "householdername") String householdername,
                            @RequestParam(value = "appealtype") String appealtype,
                            @RequestParam(value = "affairtype") String affairtype,
                            @RequestParam(value = "contactnumber") String contactnumber,
                            @RequestParam(value = "hardshipappeal") String hardshipappeal,
                            HttpServletRequest request) {
        Tappeal tappeal = new Tappeal();
        tappeal.setUuid(uuid);
        tappeal.setAppealid(Integer.parseInt(farmerid));
        tappeal.setAppealname(householdername);
        //
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        tappeal.setCity(user.getCity());
        tappeal.setTown(user.getTown());
        tappeal.setVillage(village);
        tappeal.setProposer("0");
        tappeal.setAppealtype(appealtype);
        tappeal.setAffairtype(affairtype);
        tappeal.setStatus("0");
        tappeal.setHardshipappeal(hardshipappeal);
        tappeal.setAppealtel(contactnumber);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        tappeal.setCreatedate(format.format(new Date()));
        int aid = appealService.add(tappeal);
        if (aid >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }

    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(Model model) {
        String condition = "";
        int unsolvedCount = appealService.getCount("0");
        int reportedCount = appealService.getCount("1");
        int delayedCount = appealService.getCount("2");
        int doingCount = appealService.getCount("3");
        int explainCount = appealService.getCount("4");
        int finishCount = appealService.getCount("5");
        int total = unsolvedCount + reportedCount + delayedCount +
                doingCount + explainCount + finishCount;
        int[] countlist = new int[7];
        if (total != 0) {
            countlist[0] = total;
            countlist[1] = 100 * unsolvedCount / total;
            countlist[2] = 100 * reportedCount / total;
            countlist[3] = 100 * delayedCount / total;
            countlist[4] = 100 * doingCount / total;
            countlist[5] = 100 * explainCount / total;
            countlist[6] = 100 * finishCount / total;

        } else {
            countlist[0] = 0;
            countlist[1] = 0;
            countlist[2] = 0;
            countlist[3] = 0;
            countlist[4] = 0;
            countlist[5] = 0;
            countlist[6] = 0;
        }

        model.addAttribute("countlist", countlist);
        model.addAttribute("unsolvedCount", unsolvedCount);
        model.addAttribute("reportedCount", reportedCount);
        model.addAttribute("delayedCount", delayedCount);
        model.addAttribute("doingCount", doingCount);
        model.addAttribute("explainCount", explainCount);
        model.addAttribute("finishCount", finishCount);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appeal");
        model.addAttribute("subMenu", "appealStatistics");
        return "/jsp/appeal/statistics.jsp";
    }

    private void setCommandData(Model model) {
        Map<String, String> affairTypeRefer = new HashMap<String, String>();
        affairTypeRefer.put("0", "个人事务");
        affairTypeRefer.put("1", "公共事务");
        model.addAttribute("affairTypeRefer", affairTypeRefer);

        Map<String, String> proposerTypeRefer = new HashMap<String, String>();
        proposerTypeRefer.put("0", "个人提出");
        proposerTypeRefer.put("1", "集体提出");
        model.addAttribute("proposerTypeRefer", proposerTypeRefer);

        Map<String, String> appealTypeRefer = new HashMap<String, String>();
        appealTypeRefer.put("0", "发展生产");
        appealTypeRefer.put("1", "基础设施");
        appealTypeRefer.put("2", "矛盾纠纷");
        appealTypeRefer.put("3", "社会治安");
        appealTypeRefer.put("4", "生活救助");
        appealTypeRefer.put("5", "征地拆迁");
        appealTypeRefer.put("6", "政策咨询");
        appealTypeRefer.put("7", "证照办理");
        appealTypeRefer.put("8", "群众投诉");
        appealTypeRefer.put("9", "其他");
        model.addAttribute("appealTypeRefer", appealTypeRefer);

        Map<String, String> statusTypeRefer = new HashMap<String, String>();
        statusTypeRefer.put("0", "未解决");
        statusTypeRefer.put("1", "已上报上级协调解决");
        statusTypeRefer.put("2", "延时解决");
        statusTypeRefer.put("3", "正在解决");
        statusTypeRefer.put("4", "已做好解释说明工作");
        statusTypeRefer.put("5", "已解决");
        model.addAttribute("statusTypeRefer", statusTypeRefer);

    }

    @ResponseBody
    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public JsonModel exportExcel(Model model, HttpSession session, HttpServletRequest request) throws IOException {
        //按条件导出
        if (session.getAttribute("curFilters") != null) {
            String filters = session.getAttribute("curFilters").toString();
            //清除session
            session.setAttribute("curFilters",null);
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++) {
                //诉求来源
                String curFilterRuleField = filtersClass.getRules().get(i).getField();
                String curFilterRuleData = filtersClass.getRules().get(i).getData();
                if (curFilterRuleField.equals("proposer")) {
                    if (curFilterRuleData.equals("个人提出")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        //group
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //诉求类别
                if (curFilterRuleField.equals("appealtype")) {
                    if (curFilterRuleData.equals("发展生产")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (curFilterRuleData.equals("基础设施")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (curFilterRuleData.equals("矛盾纠纷")) {
                        filtersClass.getRules().get(i).setData("2");
                    } else if (curFilterRuleData.equals("社会治安")) {
                        filtersClass.getRules().get(i).setData("3");
                    } else if (curFilterRuleData.equals("生活救助")) {
                        filtersClass.getRules().get(i).setData("4");
                    } else if (curFilterRuleData.equals("征地拆迁")) {
                        filtersClass.getRules().get(i).setData("5");
                    } else if (curFilterRuleData.equals("政策咨询")) {
                        filtersClass.getRules().get(i).setData("6");
                    }else if (curFilterRuleData.equals("证照办理")) {
                        filtersClass.getRules().get(i).setData("7");
                    }else if (curFilterRuleData.equals("群众投诉")) {
                        filtersClass.getRules().get(i).setData("8");
                    }else if (curFilterRuleData.equals("其他")) {
                        filtersClass.getRules().get(i).setData("9");
                    }
                }
                //事务类别
                if (("affairtype").equals(curFilterRuleField)) {
                    if (("个人事务").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        //group
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //状态
                if (("status").equals(curFilterRuleField)) {
                    if (("未解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (("已上报上级协调解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (("延时解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("2");
                    } else if (("正在解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("3");
                    } else if (("已做好解释说明工作").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("4");
                    } else if (("已解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("5");
                    }
                }
            }
            appealDataExportService.exportExcel2007(contextRootPath, filtersClass, (User) request.getAttribute(Constants.CURRENT_USER));
        }else {
            //无条件导出
            appealDataExportService.exportExcel2007(contextRootPath, (User) request.getAttribute(Constants.CURRENT_USER));
        }

        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appeal");
        model.addAttribute("subMenu", "appealExport");
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "/exportData", method = RequestMethod.POST)
    public JsonModel exportDataExcel(Model model,
                                     @RequestParam(value = "starttime") String starttime,
                                     @RequestParam(value = "endtime") String endtime,
                                     HttpServletRequest request) throws IOException {
        appealDataExportService.exportExcel2007(contextRootPath, starttime, endtime, (User) request.getAttribute(Constants.CURRENT_USER));
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appeal");
        model.addAttribute("subMenu", "appealExport");
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    @RequestMapping(value = "/searchinput", method = RequestMethod.GET)
    public String searchInput(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "appeal");
        model.addAttribute("subMenu", "appealSearch");
        return "/jsp/appeal/queryAppeal.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/searchAppealDoingstatus/{doingstatus}", method = RequestMethod.GET)
    public JsonModel querySpecialFamily(@PathVariable(value = "doingstatus") String doingstatus, int page, int rows, boolean _search, String filters) {
        JsonModel jsonModel = new JsonModel();
        if (!_search) {

            List<Tappeal> appeals;
            appeals = appealService.getAppealByDoingstatus(page, rows, doingstatus);//获取列表

            Long count = appealService.getCountByDoingstatus(doingstatus);
            int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(appeals);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(page);
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(rows);
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        } else {
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);
            //清理诉求信息 与 数据库一致
            for (int i = 0; i < filtersClass.getRules().size(); i++) {
                //诉求来源
                String curFilterRuleField = filtersClass.getRules().get(i).getField();
                String curFilterRuleData = filtersClass.getRules().get(i).getData();
                if (("proposer").equals(curFilterRuleField)) {
                    if (("个人提出").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        //group
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //诉求类别
                if (("appealtype").equals(curFilterRuleField)) {
                    if (curFilterRuleData.equals("发展生产")) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (curFilterRuleData.equals("基础设施")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (curFilterRuleData.equals("矛盾纠纷")) {
                        filtersClass.getRules().get(i).setData("2");
                    } else if (curFilterRuleData.equals("社会治安")) {
                        filtersClass.getRules().get(i).setData("3");
                    } else if (curFilterRuleData.equals("生活救助")) {
                        filtersClass.getRules().get(i).setData("4");
                    } else if (curFilterRuleData.equals("征地拆迁")) {
                        filtersClass.getRules().get(i).setData("5");
                    } else if (curFilterRuleData.equals("政策咨询")) {
                        filtersClass.getRules().get(i).setData("6");
                    }else if (curFilterRuleData.equals("证照办理")) {
                        filtersClass.getRules().get(i).setData("7");
                    }else if (curFilterRuleData.equals("群众投诉")) {
                        filtersClass.getRules().get(i).setData("8");
                    }else if (curFilterRuleData.equals("其他")) {
                        filtersClass.getRules().get(i).setData("9");
                    }
                }
                //事务类别
                if (("affairtype").equals(curFilterRuleField)) {
                    if (("个人事务").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("0");
                    } else {
                        //group
                        filtersClass.getRules().get(i).setData("1");
                    }
                }
                //状态
                if (("status").equals(curFilterRuleField)) {
                    if (("未解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("0");
                    } else if (("已上报上级协调解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (("延时解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("2");
                    } else if (("正在解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("3");
                    } else if (("已做好解释说明工作").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("4");
                    } else if (("已解决").equals(curFilterRuleData)) {
                        filtersClass.getRules().get(i).setData("5");
                    }
                }


            }
            List<Tappeal> appeals;
            appeals = appealService.getAppealByDoingstatus(page, rows, doingstatus, filtersClass);//获取列表

            Long count = appealService.getCountByDoingstatus(doingstatus, filtersClass);
            int pages = count.intValue() / rows + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(appeals);
            jsonModel.setTotalpages(pages);
            jsonModel.setCurrentpage(page);
            jsonModel.setTotalrecords(count.intValue());
            jsonModel.setPagerows(rows);
            jsonModel.setSort("");
            jsonModel.setOrder("");
            return jsonModel;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getSMSForAppeal/{id}", method = RequestMethod.POST)
    public Map<String, String> getSmhlAppealDetail(@PathVariable("id") int id) {
        // rar 收到后回复
        // rad 处理后回复
        String SMSTemplate = smsTemplateService.findSMSTplByTplnameAndValid(Constants.SMS_TEMPLATE_TYPES.RAD, 1);
        Tappeal tappeal = appealService.getAppealById(id);
        Map<String, String> frontDataMap = new HashMap<String, String>();
        frontDataMap.put("uuid", tappeal.getUuid());
        frontDataMap.put("appealtel", tappeal.getAppealtel());
        frontDataMap.put("appealContent", tappeal.getHardshipappeal().trim());

        Map<String, String> contextDataMap = new HashMap<String, String>();
        String[] appealDate = tappeal.getCreatedate().split("-");
        contextDataMap.put("year", appealDate[0]); // 年份
        contextDataMap.put("month", appealDate[1]); // 月份
        contextDataMap.put("day", appealDate[2]); // 日
        contextDataMap.put("appealContent", tappeal.getHardshipappeal().trim()); // 诉求信息
        contextDataMap.put("appealResponse", tappeal.getSolution() == null ? "" : tappeal.getSolution().trim()); // 解决措施（默认为诉求答复）
        for (String key : contextDataMap.keySet()) {
            SMSTemplate = SMSTemplate.replace("{" + key + "}", contextDataMap.get(key));
        }
        frontDataMap.put("smsContent", SMSTemplate);
        return frontDataMap;
    }

  /*  @ResponseBody
    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public JsonModel sendSMS(@RequestParam(value = "appealtel") String appealtel,
                           @RequestParam(value = "smsContent") String smsContent) {
        JsonModel jsonModel = new JsonModel();
        smsContent = smsContent.replace("&quot;", "\"");
        //appealtel = "18259055729";
//        SMSUtils smsUtils = new SMSUtils();
//        int connectRe = smsUtils.init();
//        if (connectRe == smsUtils.apiClient.IMAPI_SUCC) {
//            System.out.println("connectRe true");
            int result = SMSUtils.sendSM(appealtel, smsContent, new Long(22)); //初始化成功

          if (result == SMSUtils.apiClient.IMAPI_SUCC) {
              System.out.println("IMAPI_SUCC");
              jsonModel.setSuccess(true);
              jsonModel.setMsg("发送成功");                                    //发送成功
              //添加发送记录 rad当诉求处理后回复本短信
              Tsmssent tsmssent = new Tsmssent("rad", appealtel, smsContent, new Date());
              int smsId = smsSentService.add(tsmssent);
            } else if (result == SMSUtils.apiClient.IMAPI_INIT_ERR){
              System.out.println("IMAPI_INIT_ERR");
              jsonModel.setSuccess(false);
              jsonModel.setMsg("未初始化");                                     //"未初始化
          }else if (result == SMSUtils.apiClient.IMAPI_CONN_ERR){
              System.out.println("IMAPI_CONN_ERR");
              jsonModel.setSuccess(false);
              jsonModel.setMsg("数据库连接失败");                                     //"数据库连接失败
          }else if (result == SMSUtils.apiClient.IMAPI_DATA_ERR){
              System.out.println("IMAPI_DATA_ERR");
              jsonModel.setSuccess(false);
              jsonModel.setMsg("参数错误");                                    //参数错误
          }

            else if (result == SMSUtils.apiClient.IMAPI_DATA_TOOLONG){
              System.out.println("IMAPI_DATA_TOOLONG");
              jsonModel.setSuccess(false);
              jsonModel.setMsg("消息内容太长");                                     //("消息内容太长
          }

            else if (result == SMSUtils.apiClient.IMAPI_INS_ERR){
              System.out.println("IMAPI_INS_ERR");
              jsonModel.setSuccess(false);
              jsonModel.setMsg("数据库插入错误");                                     //"数据库插入错误
          }

            else if (result == SMSUtils.apiClient.IMAPI_IFSTATUS_INVALID){
              System.out.println("IMAPI_IFSTATUS_INVALID");
              jsonModel.setSuccess(false);
              jsonModel.setMsg("接口处于暂停或失效状态");                                     //("接口处于暂停或失效状态
          }

            else if (result == SMSUtils.apiClient.IMAPI_GATEWAY_CONN_ERR){
              System.out.println("IMAPI_GATEWAY_CONN_ERR");
              jsonModel.setSuccess(false);
              jsonModel.setMsg("短信网关未连接");                                     //"短信网关未连接
          }

            else{
              System.out.println("other_err");
              jsonModel.setSuccess(false);
              jsonModel.setMsg("出现其他错误");                                    //出现其他错误
          }*/


//        }else if (connectRe == smsUtils.apiClient.IMAPI_CONN_ERR){
//            smsUtils.close();
//            System.out.println("IMAPI_CONN_ERR");
//            jsonModel.setSuccess(false);
//            jsonModel.setMsg("连接失败"); //连接失败
//        } else if (connectRe == smsUtils.apiClient.IMAPI_API_ERR){
//            smsUtils.close();
//            System.out.println("IMAPI_API_ERR");
//            jsonModel.setSuccess(false);
//            jsonModel.setMsg("apiID不存在");  //apiID不存在
//        }


//        System.out.println("connectRe false");
//        smsUtils.close();
//        return jsonModel;
//    }
}
