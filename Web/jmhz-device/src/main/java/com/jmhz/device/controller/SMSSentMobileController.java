package com.jmhz.device.controller;

import com.jmhz.device.Constants;
import com.jmhz.device.backModel.SMSSentBack;
import com.jmhz.device.model.Tsmssent;
import com.jmhz.device.service.SMSSentServiceI;
import com.jmhz.device.util.ErrorCodeUtils;
import com.jmhz.device.util.SmsQueryTypesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Copyright: Copyright (c) 2013-2015 SimaStudio
 * Usage:
 *
 * @author Charkey
 * @date 2015/4/21 10:47
 */
@Controller
@RequestMapping("/mobilesmssent")
public class SMSSentMobileController extends BaseController {

    /**
     * 分页配置
     */
    public final static String curPageDefaultValue = "1";
    public final static String curRowsDefaultValue = "5";

    @Autowired
    private SMSSentServiceI smsSentService;

    /**
     * 根据分页及qrytype查询短信发送记录
     * @param page 当前页
     * @param rows 一页多少条记录
     * @param qrytype 查询类型type
     * @see Constants.SMS_QUERY_TYPES
     * @see SmsQueryTypesConverter#getQueryTypes(java.lang.String, java.lang.String)
     * @return SMSSentBack
     */
    @ResponseBody
    @RequestMapping(value = "/getSMSSent", method = RequestMethod.POST)
    public SMSSentBack getSMSSent(@RequestParam(value = "page", defaultValue = curPageDefaultValue) int page,
                                  @RequestParam(value = "rows", defaultValue = curRowsDefaultValue) int rows,
                                  @RequestParam(value = "qrytype", defaultValue = Constants.SMS_QUERY_TYPES.DWGK_0+"") int qrytype) {

        List<Tsmssent> tsmssentList = smsSentService.getSMSSent(page, rows, qrytype);
        Long count = smsSentService.getSMSSentCount(qrytype);

        SMSSentBack smsSentBack = new SMSSentBack();
        smsSentBack.setCurPage(page);
        smsSentBack.setCurRows(rows);
        smsSentBack.setTotal(count.intValue());
        smsSentBack.setTsmssentList(tsmssentList);
        smsSentBack.setSuccess(true);
        smsSentBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        return smsSentBack;
    }

    /**
     * 根据分页查询公开的短信发送记录
     * @param page 当前页
     * @param rows 一页多少条记录
     * @see Constants.SMS_QUERY_TYPES
     * @see SmsQueryTypesConverter#getQueryTypes(java.lang.String, java.lang.String)
     * @return SMSSentBack
     */
    @ResponseBody
    @RequestMapping(value = "/getPublicSMSSent", method = RequestMethod.POST)
    public SMSSentBack getSMSSent(@RequestParam(value = "page", defaultValue = curPageDefaultValue) int page,
                                  @RequestParam(value = "rows", defaultValue = curRowsDefaultValue) int rows) {
        List<Tsmssent> tsmssentList = smsSentService.getPublicSMSSent(page, rows);
        Long count = smsSentService.getPublicSMSSentCount();

        SMSSentBack smsSentBack = new SMSSentBack();
        smsSentBack.setCurPage(page);
        smsSentBack.setCurRows(rows);
        smsSentBack.setTotal(count.intValue());
        smsSentBack.setTsmssentList(tsmssentList);
        smsSentBack.setSuccess(true);
        smsSentBack.setErrcode(ErrorCodeUtils.EC_SUCCESS);
        return smsSentBack;
    }
}
