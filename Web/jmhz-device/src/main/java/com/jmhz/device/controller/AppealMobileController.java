package com.jmhz.device.controller;

import com.jmhz.device.backModel.AppealAddBack;
import com.jmhz.device.backModel.AppealBack;
import com.jmhz.device.backModel.AppealRateBack;
import com.jmhz.device.model.Tappeal;
import com.jmhz.device.model.Tappealrate;
import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.service.AppealRateServiceI;
import com.jmhz.device.service.AppealServiceI;
import com.jmhz.device.service.FarmerServiceI;
import com.jmhz.device.service.GroupServiceI;
import com.jmhz.device.util.ErrorCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 锋情 on 2015-04-10.
 */
@Controller
@RequestMapping("/mobileappeal")
public class AppealMobileController extends BaseController{

    /**
     * 分页配置
     */
    public final static String curPageDefaultValue = "1";
    public final static String curRowsDefaultValue = "5";

    @Autowired
    private AppealServiceI appealService;
    @Autowired
    private FarmerServiceI farmerService;
    @Autowired
    private GroupServiceI groupService;
    @Autowired
    private AppealRateServiceI appealRateService;

    @ResponseBody
    @RequestMapping(value = "/getPublicAppeal")
    public AppealBack getPublicAppeal(@RequestParam(value="page", defaultValue = curPageDefaultValue) int page
            , @RequestParam(value="rows", defaultValue = curRowsDefaultValue) int rows){

        AppealBack appealBack = new AppealBack();
        List<Tappeal> tappeals = appealService.getPublicAppeal(page,rows);
        int total = appealService.getPublicAppealCount();
        appealBack.setTotal(total);
        appealBack.setCurPage(page);
        appealBack.setCurRows(rows);
        appealBack.setAppeals(tappeals);
        return appealBack;
    }

    @ResponseBody
    @RequestMapping(value = "/getAppealByCondition")
    public AppealBack getAppealByCondition(String uuid, String name, String tel,
              @RequestParam(value="page", defaultValue = curPageDefaultValue) int page,
               @RequestParam(value="rows", defaultValue = curRowsDefaultValue) int rows){

        AppealBack appealBack = new AppealBack();
        if (uuid == null || uuid == ""){
            uuid = "0";
        }if (name == null || name == ""){
            name = "0";
        }if (tel == null || tel == ""){
            tel = "0";
        }
        String[] queryList = {uuid,name,tel};

        List<Tappeal> tappeals;
        List<Tappeal> tappealsNew = new ArrayList<Tappeal>();
        tappeals = appealService.getMassesAppeal(page+"", rows+"", queryList);
        if (!queryList[2].equals("0")) {
            System.out.println("name" + name);
            for (int i = 0; i < tappeals.size(); i++) {
                //个人
                if (tappeals.get(i).getProposer().equals("0")) {
                    if (farmerService.getFarmerMassesById(tappeals.get(i).getAppealid()).getContactnumber().equals(queryList[2])) {
                        tappealsNew.add(tappeals.get(i));
                    }
                } else {
                    //集体
                    if (groupService.getGroupMassesById(tappeals.get(i).getAppealid()).getGroupchargertel().equals(queryList[2])) {
                        tappealsNew.add(tappeals.get(i));
                    }
                }

            }
            appealBack.setTotal(tappealsNew.size());
            appealBack.setCurPage(page);
            appealBack.setCurRows(rows);
            appealBack.setAppeals(tappealsNew);
            return appealBack;
        }
        Long count = appealService.getMassesAppealCount(queryList);
        appealBack.setTotal(count.intValue());
        appealBack.setCurPage(page);
        appealBack.setCurRows(rows);
        appealBack.setAppeals(tappeals);
        return appealBack;
    }

    @ResponseBody
    @RequestMapping(value = "/appealRate", method = RequestMethod.POST)
    public AppealRateBack appealRate(@RequestParam("appealId") int appealId,
                          @RequestParam("rateLevel") int rateLevel,
                          @RequestParam("rateContent") String rateContent) {
        AppealRateBack appealRateBack = new AppealRateBack();
        if (rateLevel < 1 || rateLevel > 4){
            appealRateBack.setSuccess(false);
            appealRateBack.setErrcode(ErrorCodeUtils.EC_PARAMETERS_OUTOFBOUND);
            return appealRateBack;
        }

        if (appealService.getAppealById(appealId) == null){
            appealRateBack.setSuccess(false);
            appealRateBack.setErrcode(ErrorCodeUtils.EC_APPEAL_INEXISTENCE);
            return appealRateBack;
        }

        Tappealrate newAppealRate = new Tappealrate(appealId, rateLevel, rateContent, new Date());
        int rsid = appealRateService.add(newAppealRate);

        if (rsid == -1) {
            appealRateBack.setSuccess(false);
            appealRateBack.setErrcode(ErrorCodeUtils.EC_DATABASE);
        } else {
            appealRateBack.setSuccess(true);
        }

        return appealRateBack;
    }

    @ResponseBody
    @RequestMapping(value = "/addAppeal", method = RequestMethod.POST)
    public AppealAddBack addAppeal(@RequestParam(value = "name") String name,
                            @RequestParam(value = "town") String town,
                            @RequestParam(value = "village") String village,
                            @RequestParam(value = "telephone") String telephone,
                            @RequestParam(value = "appealtype") String appealtype,
                            @RequestParam(value = "affairtype") String affairtype,
                            @RequestParam(value = "hardshipappeal") String hardshipappeal) {
        AppealAddBack appealAddBack = new AppealAddBack();
        Tappeal tappeal = new Tappeal();
        Tfarmer tfarmer = farmerService.getFarmerByNameAndTel(name,telephone);
        if (tfarmer != null){
            tappeal.setAppealid(tfarmer.getId());
        }else {
            Tfarmer newTfarmer = new Tfarmer();
            newTfarmer.setHouseholdername(name);
            newTfarmer.setTown(town);
            newTfarmer.setVillage(village);
            newTfarmer.setContactnumber(telephone);
            newTfarmer.setCity("沙县");
            tappeal.setAppealid(farmerService.add(newTfarmer));
        }


        tappeal.setAppealname(name);
        tappeal.setCity("沙县");
        tappeal.setTown(town);
        tappeal.setVillage(village);
        tappeal.setProposer("0");
        tappeal.setAppealtype(appealtype);
        tappeal.setAffairtype(affairtype);
        tappeal.setStatus("0");
        tappeal.setHardshipappeal(hardshipappeal);
        tappeal.setAppealtel(telephone);
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd" );
        tappeal.setCreatedate(format.format(new Date()));
        int aid = appealService.add(tappeal);
        if (aid < 0) {
            appealAddBack.setSuccess(false);
            appealAddBack.setErrcode(ErrorCodeUtils.EC_DATABASE);
        }
        return appealAddBack;
    }
}
