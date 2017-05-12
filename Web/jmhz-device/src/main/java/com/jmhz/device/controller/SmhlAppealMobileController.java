package com.jmhz.device.controller;

import com.jmhz.device.model.Tsmhlappeal;
import com.jmhz.device.service.SmhlAppealServiceI;
import com.jmhz.device.backModel.SmhlAppealBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 锋情 on 2015-04-11.
 */
@Controller
@RequestMapping("/mobilesmhlappeal")
public class SmhlAppealMobileController extends BaseController{
    /**
     * 分页配置
     */
    public final static String curPageDefaultValue = "1";
    public final static String curRowsDefaultValue = "5";

    @Autowired
    SmhlAppealServiceI smhlAppealService;

    @ResponseBody
    @RequestMapping(value = "/getMobileAppealByCondition")
    public SmhlAppealBack getAppealByCondition(String uuid, String name, String tel,int source,
                                           @RequestParam(value="page", defaultValue = curPageDefaultValue) int page,
                                           @RequestParam(value="rows", defaultValue = curRowsDefaultValue) int rows){

        SmhlAppealBack smhlAppealBack = new SmhlAppealBack();
        if (uuid == null || uuid == ""){
            uuid = "0";
        }if (name == null || name == ""){
            name = "0";
        }if (tel == null || tel == ""){
            tel = "0";
        }
        String[] queryList = {uuid,name,tel};

        List<Tsmhlappeal> tsmhlappeals;
        List<Tsmhlappeal> tsmhlappealsNew = new ArrayList<Tsmhlappeal>();
        tsmhlappeals = smhlAppealService.getMassesSmhlAppeal(page+"", rows+"", queryList, source);
        if (!queryList[2].equals("0")) {
            for (int i = 0; i < tsmhlappeals.size(); i++) {
                    if (tsmhlappeals.get(i).getAppealtel().equals(queryList[2])) {
                        tsmhlappealsNew.add(tsmhlappeals.get(i));
                    }
            }
            smhlAppealBack.setTotal(tsmhlappealsNew.size());
            smhlAppealBack.setCurPage(page);
            smhlAppealBack.setCurRows(rows);
            smhlAppealBack.setAppeals(tsmhlappealsNew);
            return smhlAppealBack;
        }
        Long count = smhlAppealService.getMassesSmhlAppealCount(queryList, source);
        smhlAppealBack.setTotal(count.intValue());
        smhlAppealBack.setCurPage(page);
        smhlAppealBack.setCurRows(rows);
        smhlAppealBack.setAppeals(tsmhlappeals);
        return smhlAppealBack;
    }
}
