package com.jmhz.device.service.impl;

import com.jmhz.device.dao.AppealRateDaoI;
import com.jmhz.device.service.AppealRateServiceI;
import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.model.Tappealrate;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppealRateServiceImpl implements AppealRateServiceI {

    @Autowired
    private AppealRateDaoI appealRateDao;

    @Override
    public int add(Tappealrate tappealrate) {
        Serializable id = appealRateDao.save(tappealrate);
        if (id == null){
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public Long getCountById(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return appealRateDao.count("select count(*) from Tappealrate t where t.tappealid=:id", params);
    }

    @Override
    public Boolean update(Tappealrate tappealrate) {
        appealRateDao.update(tappealrate);
        return true;
    }

    @Override
    public int getCount(String condition) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = appealRateDao.count("select count(*) from Tappealrate t where t.status = :condition", params);
        return count.intValue();
    }

    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tappealrate t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        Long count = appealRateDao.count(hql, params);
        return count;
    }

    @Override
    public List<Tappealrate> getAllAppealRate(int appealid, int page, int rows) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", appealid);
        String hql = "from Tappealrate t where t.tappealid=:appealid order by t.id desc";
        return appealRateDao.find(hql, params, page, rows);
    }

    @Override
    public List<Tappealrate> getAllAppealRate(int appealid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", appealid);
        String hql = "from Tappealrate t where t.tappealid=:appealid order by t.id desc";
        return appealRateDao.find(hql, params);
    }

    @Override
    public void updateAppealRate(int appealId, int rateLevel, String rateContent, String rateDate) {
        String hqlToRate = "update Tappealrate t set t.ratelevel=:rateLevel,t.ratecontent=:rateContent,t.ratedate=:curDate where t.id=:appealId";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealId", appealId);
        params.put("rateLevel", rateLevel);
        params.put("rateContent", rateContent);
        Date curDate = null;
        try {
            curDate = new SimpleDateFormat("yyyy-MM-dd").parse(rateDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        params.put("curDate", curDate);
        appealRateDao.executeHql(hqlToRate, params);
    }

    @Override
    public void deleteAppealRate(int rateId) {
        Tappealrate tappealrate = new Tappealrate();
        tappealrate.setId(rateId);
        appealRateDao.delete(tappealrate);
    }

}
