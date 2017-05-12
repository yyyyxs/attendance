package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
import com.jmhz.device.Constants;
import com.jmhz.device.model.Tsmhlappeal;
import com.jmhz.device.model.Tsmssent;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.pageModel.SmhlAppealStatus;
import com.jmhz.device.service.*;
import com.jmhz.device.util.SMSUtils;
import com.jmhz.device.model.Tsmhlappealmark;
import com.jmhz.device.model.Tsmoriginalappeal;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.sys.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 锋情 on 2014/7/9.
 */
@Controller
@RequestMapping("/smhlappeal")
public class SmhlAppealController {

    @Autowired
    private SmhlAppealServiceI smhlAppealService;
    @Autowired
    private SmOriginalAppealServiceI smOriginalAppealService;
    @Autowired
    private TsmhlappealmarkServiceI tsmhlappealmarkService;
    @Autowired
    private SMSSentServiceI smsSentService;
    @Autowired
    private SMSTemplateServiceI smsTemplateService;

    @RequestMapping(value = "/addInput/{source}", method = RequestMethod.GET)
    public String addInput(@PathVariable("source") String source, Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "hlAppeal");
        model.addAttribute("subMenu", "hlAppealCreate");
        model.addAttribute("source",source);
        return "/jsp/smhlappeal/addinput.jsp";
    }
    @ResponseBody
    @RequestMapping(value = "/addAppeal/{source}", method = RequestMethod.POST)
    public String addAppeal(@PathVariable("source") String source,Tsmhlappeal tsmhlappeal, Model model,HttpServletRequest request) {

        if ("".equals(tsmhlappeal.getCreatedate())||tsmhlappeal.getCreatedate()==null){
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
            tsmhlappeal.setCreatedate(format.format(new Date()));
        }
        //将账号的县、镇信息获取
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        tsmhlappeal.setCity(user.getCity());
        tsmhlappeal.setTown(user.getTown());
        //tsmhlappeal.setSource(source);
        tsmhlappeal.setStatus("0");
        int said = smhlAppealService.add(tsmhlappeal);



        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "hlAppeal");
        model.addAttribute("subMenu", "hlAppealCreate");
        if (said >= 0) {
            return "1";//1 成功
        } else {
            return "0";
        }
    }
    @RequestMapping(value = "/show/{source}", method = RequestMethod.GET)
    public String mqGroupCardInput(@PathVariable("source") String source, Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        if ("0".equals(source)){
            model.addAttribute("navMenu", "smAppeal");
            model.addAttribute("subMenu", "smAppealView");
        }else {
            model.addAttribute("navMenu", "hlAppeal");
            model.addAttribute("subMenu", "hlAppealView");
        }

        model.addAttribute("source", source);

        return "/jsp/smhlappeal/list.jsp";
    }
    @ResponseBody
    @RequestMapping(value = "/getAllAppeal/{source}", method = RequestMethod.GET)
    public JsonModel getAllBehalfDeal(@PathVariable("source") int source,int page, int rows, boolean _search, String filters, Model model, HttpSession session) {
        JsonModel jsonModel = new JsonModel();
        session.setAttribute("source",source);
        if (!_search) {

            //设置搜索属性到model
            model.addAttribute("curPage", page);
            model.addAttribute("curRows", rows);
            model.addAttribute("curIsSearch", _search);
            model.addAttribute("curFilters", filters);
            session.setAttribute("curFilters", filters);
            List<Tsmhlappeal> tsmhlappeals;
            tsmhlappeals = smhlAppealService.getAllAppeal(page, rows, source);//获取列表

            Long count = smhlAppealService.getCount(source);
            int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tsmhlappeals);
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
            int source_session = Integer.parseInt(session.getAttribute("source")+"");
            //查询
            filters = filters.replace("&quot;", "\"");
            Filters filtersClass = JSON.parseObject(filters, Filters.class);

            List<Tsmhlappeal> tsmhlappeals;
            tsmhlappeals = smhlAppealService.getAllAppeal(page, rows, filtersClass, source_session);//获取列表
            Long count = smhlAppealService.getCount(filtersClass, source_session);
            int pages = count.intValue() / rows + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tsmhlappeals);
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
        Tsmhlappeal tsmhlappeal = smhlAppealService.getAppealById(id);
        model.addAttribute("tsmhlappeal", tsmhlappeal);
        if ("0".equals(tsmhlappeal.getSource())){
            //根据uuid获取诉求备注
            model.addAttribute("tsmhlappealmarks", tsmhlappealmarkService.getSmhlappealmarkByUuid(tsmhlappeal.getUuid()));
            model.addAttribute("size", tsmhlappealmarkService.getSmhlappealmarkByUuid(tsmhlappeal.getUuid()).size());

            //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
            model.addAttribute("navMenu", "smAppeal");
            model.addAttribute("subMenu", "smAppealView");

        }else {
            //根据uuid获取诉求备注
           model.addAttribute("tsmhlappealmarks", tsmhlappealmarkService.getSmhlappealmarkByUuid(tsmhlappeal.getUuid()));
           model.addAttribute("size", tsmhlappealmarkService.getSmhlappealmarkByUuid(tsmhlappeal.getUuid()).size());
            //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
            model.addAttribute("navMenu", "hlAppeal");
            model.addAttribute("subMenu", "hlAppealView");
        }
        return "/jsp/smhlappeal/appealshow.jsp";
    }
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateSpecialGroup(SmhlAppealStatus smhlAppealStatus, Model model,HttpServletRequest request) {
        Tsmhlappeal tsmhlappeal = new Tsmhlappeal();
        BeanUtils.copyProperties(smhlAppealStatus,tsmhlappeal);
        //将账号的县、镇信息获取
        User user = (User) request.getAttribute(Constants.CURRENT_USER);
        tsmhlappeal.setCity(user.getCity());
        tsmhlappeal.setTown(user.getTown());

        smhlAppealService.update(tsmhlappeal);
        smhlAppealService.synchronizationUpdate(tsmhlappeal);
        //by add remark begin
        if (!smhlAppealStatus.getProcessupdateremark().equals("") && !smhlAppealStatus.getProcessupdatetime().equals("")) {

            Tsmhlappealmark tsmhlappealmark = new Tsmhlappealmark();
            tsmhlappealmark.setId(smhlAppealStatus.getId());
            tsmhlappealmark.setUuid(smhlAppealStatus.getUuid());
            tsmhlappealmark.setAppealname(smhlAppealStatus.getAppealname());
            tsmhlappealmark.setProcessupdatetime(smhlAppealStatus.getProcessupdatetime());
            tsmhlappealmark.setProcessupdateremark(smhlAppealStatus.getProcessupdateremark());
            tsmhlappealmarkService.add(tsmhlappealmark);
           // system.out.printl


        }
        //by add remark end
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        if ("0".equals(tsmhlappeal.getSource())){
            model.addAttribute("navMenu", "smAppeal");
            model.addAttribute("subMenu", "smAppealView");
        }else {
            model.addAttribute("navMenu", "hlAppeal");
            model.addAttribute("subMenu", "hlAppealView");
        }
        return "1";
    }
    @RequestMapping(value = "/showOriginal/{source}", method = RequestMethod.GET)
    public String showOriginal(@PathVariable("source") String source, Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
//        if ("0".equals(source)){
            model.addAttribute("navMenu", "smAppeal");
            model.addAttribute("subMenu", "smOriginalAppealView");
//        }else {
//            model.addAttribute("navMenu", "hlAppeal");
//            model.addAttribute("subMenu", "hlAppealView");
//        }

//        model.addAttribute("source", source);

        return "/jsp/smhlappeal/originalList.jsp";
    }
    @ResponseBody
    @RequestMapping(value = "/getAllOriginalAppeal", method = RequestMethod.GET)
    public JsonModel getAllOriginalAppeal(int page, int rows, boolean _search, String filters, Model model, HttpSession session) {
        JsonModel jsonModel = new JsonModel();

        if (!_search) {

            //设置搜索属性到model
            model.addAttribute("curPage", page);
            model.addAttribute("curRows", rows);
            model.addAttribute("curIsSearch", _search);
            model.addAttribute("curFilters", filters);
            session.setAttribute("curFilters", filters);
            List<Tsmoriginalappeal> tsmoriginalappeals;
            tsmoriginalappeals = smOriginalAppealService.getAllAppeal(page, rows);//获取列表

            Long count = smOriginalAppealService.getCount();
            int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tsmoriginalappeals);
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

                //状态
                if (curFilterRuleField.equals("status")) {
                    if (curFilterRuleData.equals("新增")) {
                        filtersClass.getRules().get(i).setData("1");
                    } else if (curFilterRuleData.equals("正在处理")) {
                        filtersClass.getRules().get(i).setData("2");
                    } else if (curFilterRuleData.equals("已回复")) {
                        filtersClass.getRules().get(i).setData("3");
                    }
                }


            }
            List<Tsmoriginalappeal> tsmoriginalappeals;
            tsmoriginalappeals = smOriginalAppealService.getAllAppeal(page, rows, filtersClass);//获取列表
            Long count = smOriginalAppealService.getCount(filtersClass);
            int pages = count.intValue() / rows + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tsmoriginalappeals);
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
    @RequestMapping("/delete")
    public JsonModel delete(Tsmhlappeal tsmhlappeal) {

        smhlAppealService.delAppeal(tsmhlappeal);

        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }
    @ResponseBody
    @RequestMapping("/deleteOriginal")
    public JsonModel deleteOriginal(Tsmoriginalappeal tsmoriginalappeal) {

        smOriginalAppealService.delAppeal(tsmoriginalappeal);

        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }
    @RequestMapping(value = "/originalTransformAppeal/{id}", method = RequestMethod.GET)
    public String originalTransformAppeal(@PathVariable("id") String id, Model model) {
        Tsmoriginalappeal tsmoriginalappeal = smOriginalAppealService.getAppealById(id);
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "smAppeal");
        model.addAttribute("subMenu", "smOriginalAppealView");
        model.addAttribute("source",0);//0短信；1热线
        model.addAttribute("tsmoriginalappeal",tsmoriginalappeal);

        return "/jsp/smhlappeal/addinput.jsp";
    }
    @RequestMapping("/tovieworiginal/{id}")
    public String tovieworiginal(@PathVariable("id") String id, Model model) {
        Tsmoriginalappeal tsmoriginalappeal = smOriginalAppealService.getAppealById(id);
        model.addAttribute("tsmoriginalappeal", tsmoriginalappeal);

        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
//        if ("0".equals(tsmhlappeal.getSource())){
            model.addAttribute("navMenu", "smAppeal");
            model.addAttribute("subMenu", "smOriginalAppealView");
//        }else {
//            model.addAttribute("navMenu", "hlAppeal");
//            model.addAttribute("subMenu", "hlAppealView");
//        }
        return "/jsp/smhlappeal/originalAppealshow.jsp";
    }
    @ResponseBody
    @RequestMapping(value = "/updateOriginal", method = RequestMethod.POST)
    public String updateOriginal(Tsmoriginalappeal tsmoriginalappeal, Model model) {
        smOriginalAppealService.update(tsmoriginalappeal);

        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        //        if ("0".equals(tsmhlappeal.getSource())){
        model.addAttribute("navMenu", "smAppeal");
        model.addAttribute("subMenu", "smOriginalAppealView");
//        }else {
//            model.addAttribute("navMenu", "hlAppeal");
//            model.addAttribute("subMenu", "hlAppealView");
//        }
        return "1";
    }

    @ResponseBody
    @RequestMapping(value = "/getSMSForSmhlAppeal/{id}", method = RequestMethod.POST)
    public Map<String, String> getSmhlAppealDetail(@PathVariable("id") int id) {
        // rar 收到后回复
        // rad 处理后回复
        String SMSTemplate = smsTemplateService.findSMSTplByTplnameAndValid(Constants.SMS_TEMPLATE_TYPES.RAD, 1);
        Tsmhlappeal tsmhlappeal = smhlAppealService.getAppealById(id);
        Map<String, String> frontDataMap = new HashMap<String, String>();
        frontDataMap.put("uuid", tsmhlappeal.getUuid());
        frontDataMap.put("appealtel", tsmhlappeal.getAppealtel());
        frontDataMap.put("appealContent", tsmhlappeal.getHardshipappeal());

        Map<String, String> contextDataMap = new HashMap<String, String>();
        String[] appealDate = tsmhlappeal.getCreatedate().split("-");
        contextDataMap.put("year", appealDate[0]); // 年份
        contextDataMap.put("month", appealDate[1]); // 月份
        contextDataMap.put("day", appealDate[2]); // 日
        contextDataMap.put("appealContent", tsmhlappeal.getHardshipappeal()); // 诉求信息
        contextDataMap.put("appealResponse", tsmhlappeal.getSolution() == null ? "" : tsmhlappeal.getSolution()); // 解决措施（默认为诉求答复）
        for (String key : contextDataMap.keySet()) {
            SMSTemplate = SMSTemplate.replace("{" + key + "}", contextDataMap.get(key));
        }
        frontDataMap.put("smsContent", SMSTemplate);
        return frontDataMap;
    }

    @ResponseBody
    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public JsonModel sendSMS(@RequestParam(value = "appealtel") String appealtel,
                             @RequestParam(value = "smsContent") String smsContent) {
        JsonModel jsonModel = new JsonModel();
        smsContent = smsContent.replace("&quot;", "\"");
//        appealtel = "15880050010";
//        SMSUtils smsUtils = new SMSUtils();
//        int connectRe = smsUtils.init();
//        if (connectRe == smsUtils.apiClient.IMAPI_SUCC) {
//            System.out.println("connectRe true");
           /* int result = SMSUtils.sendSM(appealtel, smsContent, new Long(22)); //初始化成功

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
            }
*/

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


        System.out.println("connectRe false");
//        smsUtils.close();
        return jsonModel;
    }
}
