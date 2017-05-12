package com.jmhz.device.controller;

import com.alibaba.fastjson.JSON;
//import com.jasson.im.api.APIClient;
import com.jmhz.device.Constants;
import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.model.Tsmssent;
import com.jmhz.device.pageModel.JsonModel;
import com.jmhz.device.service.FarmerServiceI;
import com.jmhz.device.service.SMSSentServiceI;
import com.jmhz.device.sys.service.ISysConfigService;
import com.jmhz.device.util.SMSUtils;
import com.jmhz.device.util.SmsQueryTypesConverter;
import com.jmhz.device.pageModel.Filters;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*
@Controller
@RequestMapping("/smssent")
public class SMSSentController extends BaseController {

    @Autowired
    private SMSSentServiceI smsSentService;
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private FarmerServiceI farmerService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String mqGroupCardInput(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "smAppeal");
        model.addAttribute("subMenu", "smssent");
        return "/jsp/smstpl/smssent.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/getAllSMSSent", method = RequestMethod.GET)
    public JsonModel getAllAppeal(int page, int rows, boolean _search, String filters, Model model, HttpSession session, int qrytype) {
        JsonModel jsonModel = new JsonModel();
        if (!_search) {

            //设置搜索属性到model
            model.addAttribute("curPage", page);
            model.addAttribute("curRows", rows);
            model.addAttribute("curIsSearch", _search);
            model.addAttribute("curFilters", filters);
            session.setAttribute("curFilters", filters);
            List<Tsmssent> tsmssentList;
            tsmssentList = smsSentService.getAllSMSSent(page, rows, qrytype);//获取列表

            Long count = smsSentService.getCount(qrytype);
            int pages = count.intValue() % rows == 0 ? (count.intValue() / rows) : (count.intValue() / rows + 1);
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tsmssentList);
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
            List<Tsmssent> tsmssentList;
            tsmssentList = smsSentService.getAllSMSSent(page, rows, filtersClass);//获取列表
            Long count = smsSentService.getCount(filtersClass);
            int pages = count.intValue() / rows + 1;
            jsonModel.setSuccess(true);
            jsonModel.setMsg("success");
            jsonModel.setDataObj(tsmssentList);
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
    public JsonModel deleteSMSSent(Tsmssent tsmssent) {
        smsSentService.delSMSSent(tsmssent);
        JsonModel jsonModel = new JsonModel();
        jsonModel.setSuccess(true);
        return jsonModel;
    }

    @RequestMapping(value = "/sendSMSView", method = RequestMethod.GET)
    public String sendSMS(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "smAppeal");
        model.addAttribute("subMenu", "sendSMS");
        //List<String> configList = sysConfigService.getConfigValuesByType(Constants.SYS_CONFIG_TYPE.SMS_MESSAGE_TYPES);
        return "/jsp/smhlappeal/sendsms.jsp";
    }

    @RequestMapping(value = "/sendSMSBatchView", method = RequestMethod.GET)
    public String sendSMSBatch(Model model) {
        //设置菜单栏样式所需，navMenu：父级菜单；subMenu：子菜单
        model.addAttribute("navMenu", "smAppeal");
        model.addAttribute("subMenu", "sendSMSBatch");
        //List<String> configList = sysConfigService.getConfigValuesByType(Constants.SYS_CONFIG_TYPE.SMS_MESSAGE_TYPES);
        return "/jsp/smhlappeal/sendsmsbatch.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/qryTelByName", method = RequestMethod.POST)
    public JsonModel qryTelByName(String qryname) {
        Tfarmer tfarmer = farmerService.getFarmer(qryname.trim());
        JsonModel jsonModel = new JsonModel();
        if (tfarmer != null) {
            jsonModel.setSuccess(true);
            jsonModel.setDataObj(tfarmer.getContactnumber());
        } else {
            jsonModel.setSuccess(false);
            jsonModel.setDataObj("");
        }
        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "/qryTelsByTownName", method = RequestMethod.POST)
    public JsonModel qryTelsByTownName(String qrytownname) {
        List<Tfarmer> tfarmerList = farmerService.getFarmerByTownName(qrytownname.trim());
        JsonModel jsonModel = new JsonModel();
        StringBuilder numberString = new StringBuilder();
        if (tfarmerList != null && tfarmerList.size() > 0) {
            for (Tfarmer tfarmer1 : tfarmerList) {
                String number = tfarmer1.getContactnumber();
                if (!StringUtils.isEmpty(number) && number.length() == 11) {
                    numberString.append(number).append(",");
                }
            }
            jsonModel.setSuccess(true);
            jsonModel.setDataObj(numberString.substring(0, numberString.toString().length() - 1));
        } else {
            jsonModel.setSuccess(false);
            jsonModel.setDataObj("");
        }
        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "/qryTelsByVillageName", method = RequestMethod.POST)
    public JsonModel qryTelsByVillageName(String qryvillagename) {
        List<Tfarmer> tfarmerList = farmerService.getFarmerByVillageName(qryvillagename.trim());
        JsonModel jsonModel = new JsonModel();
        StringBuilder numberString = new StringBuilder();
        if (tfarmerList != null && tfarmerList.size() > 0) {
            for (Tfarmer tfarmer1 : tfarmerList) {
                String number = tfarmer1.getContactnumber();
                if (!StringUtils.isEmpty(number) && number.length() == 11) {
                    numberString.append(number).append(",");
                }
            }
            jsonModel.setSuccess(true);
            jsonModel.setDataObj(numberString.substring(0, numberString.toString().length() - 1));
        } else {
            jsonModel.setSuccess(false);
            jsonModel.setDataObj("");
        }
        return jsonModel;
    }

    @ResponseBody
    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public JsonModel sendSMS(@RequestParam(value = "telnumbers") String telnumbers,
                             @RequestParam(value = "smscontent") String smscontent,
                             @RequestParam(value = "smsparenttype", required = false) String smsparenttype,
                             @RequestParam(value = "smschildtype", required = false) String smschildtype,
                             @RequestParam(value = "ispublic", required = false) boolean ispublic) {
        JsonModel jsonModel = new JsonModel();
        smscontent = smscontent.replace("&quot;", "\"");
        telnumbers = telnumbers.replace("，", ",");
        String[] numbers = telnumbers.split(",");
        List<String> numberList = new ArrayList<String>();
        String[] strings = new String[]{};

        for (String number : numbers) {
            // 处理掉因为两个连续逗号产生的空字符串 及 不是手机号码的无效号码(包括加上区号的11位固话05985504552)
            if (!StringUtils.isEmpty(number) && number.length() == 11 && !numberList.contains(number) && !number.startsWith("0")) {
                numberList.add(number);
            }
        }

        // 有效号码数
       */
/* int numberSize = numberList.size();
        // 单批次发送号码数量
        int batchSize = 500;
        if (numberSize <= batchSize) {
            // 少于 等于 500 条，直接发送
            return sendMessageAndReturnJsonModel(jsonModel, numberList.toArray(strings), smscontent, smsparenttype, smschildtype, ispublic);
        } else {
            int cycleCount = numberSize / batchSize;
            for (int i = 0; i < cycleCount; i++) {
                sendMessageAndReturnJsonModel(jsonModel, numberList.subList((i * batchSize), ((i + 1) * batchSize)).toArray(strings), smscontent, smsparenttype, smschildtype, ispublic);
            }
            sendMessageAndReturnJsonModel(jsonModel, numberList.subList(cycleCount * batchSize, numberSize).toArray(strings), smscontent, smsparenttype, smschildtype, ispublic);
        }
        return jsonModel;*//*

//*/
/*    }*//*


   */
/* private JsonModel sendMessageAndReturnJsonModel(JsonModel jsonModel, String[] numberToSend, String smscontent, String parentType, String childType, boolean ispublic) {
        int result = SMSUtils.sendSM(numberToSend, smscontent, (long) 22); //初始化成功
        if (result == APIClient.IMAPI_SUCC) {
            //发送成功
            jsonModel.setSuccess(true);
            jsonModel.setMsg("发送成功");
        } else if (result == APIClient.IMAPI_INIT_ERR) {
            //未初始化
            jsonModel.setSuccess(false);
            jsonModel.setMsg("未初始化");
        } else if (result == APIClient.IMAPI_CONN_ERR) {
            //数据库连接失败
            jsonModel.setSuccess(false);
            jsonModel.setMsg("数据库连接失败");
        } else if (result == APIClient.IMAPI_DATA_ERR) {
            //参数错误
            jsonModel.setSuccess(false);
            jsonModel.setMsg("参数错误");
        } else if (result == APIClient.IMAPI_DATA_TOOLONG) {
            //消息内容太长
            jsonModel.setSuccess(false);
            jsonModel.setMsg("消息内容太长");
        } else if (result == APIClient.IMAPI_INS_ERR) {
            //数据库插入错误
            jsonModel.setSuccess(false);
            jsonModel.setMsg("数据库插入错误");
        } else if (result == APIClient.IMAPI_IFSTATUS_INVALID) {
            //接口处于暂停或失效状态
            jsonModel.setSuccess(false);
            jsonModel.setMsg("接口处于暂停或失效状态");
        } else if (result == APIClient.IMAPI_GATEWAY_CONN_ERR) {
            //短信网关未连接
            jsonModel.setSuccess(false);
            jsonModel.setMsg("短信网关未连接");
        } else {
            //出现其他错误
            jsonModel.setSuccess(false);
            jsonModel.setMsg("出现其他错误");
        }
        if (result == APIClient.IMAPI_SUCC) {
            //添加发送记录 rad当诉求处理后回复本短信
            int qryType = SmsQueryTypesConverter.getQueryTypes(parentType, childType);
            Tsmssent tsmssent = new Tsmssent(Constants.SMS_TEMPLATE_TYPES.SINGLE, parentType, childType, qryType, Arrays.toString(numberToSend), smscontent, ispublic, new Date());
            int smsId = smsSentService.add(tsmssent);
        } else {
            System.out.println("Something error, fixme.");
        }
        return jsonModel;
    }*//*

}*/
