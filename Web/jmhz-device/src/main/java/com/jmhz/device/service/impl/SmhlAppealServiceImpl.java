package com.jmhz.device.service.impl;

import com.jmhz.device.dao.SmhlAppealDaoI;
import com.jmhz.device.dao.SmhlAppealMassesDaoI;
import com.jmhz.device.model.Tsmhlappeal;
import com.jmhz.device.service.SmhlAppealServiceI;
import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 锋情 on 2014/4/18.
 */
@Service
public class SmhlAppealServiceImpl implements SmhlAppealServiceI {

    @Autowired
    private SmhlAppealDaoI smhlAppealDao;
    @Autowired
    private SmhlAppealMassesDaoI smhlAppealMassesDao;
    @Override
    public int add(Tsmhlappeal tsmhlappeal) {
        Serializable id = smhlAppealDao.save(tsmhlappeal);
        if (id == null){
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public List<Tsmhlappeal> getAllAppeal(int page, int rows, int source) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("source",source+"");
        String hql = "from Tsmhlappeal t where t.source = :source order by t.id desc";
        List<Tsmhlappeal> tsmhlappealList = smhlAppealDao.find(hql ,params,page, rows);
        return tsmhlappealList;
    }

    @Override
    public Long getCount(int source) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("source",source+"");
        Long count = smhlAppealDao.count("select count(*) from Tsmhlappeal t where t.source = :source",params);
        return count;
    }

    @Override
    public List<Tsmhlappeal> getAllAppeal(int page, int rows, Filters filtersClass, int source_session) {
        Map<String, Object> params =new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tsmhlappeal t where t.source = :source and ");
        params.put("source",source_session+"");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
        List<Tsmhlappeal> tsmhlappealList = smhlAppealDao.find(hql, params, page, rows);
        return tsmhlappealList;
    }

    @Override
    public Long getCount(Filters filtersClass, int source_session) {

        Map<String, Object> params = new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tsmhlappeal t where t.source = :source and ");
        params.put("source",source_session+"");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        Long count = smhlAppealDao.count(hql, params);
        return count;
    }

    @Override
    public Tsmhlappeal getAppealById(int id) {
        String hql = "from Tsmhlappeal t where t.id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return smhlAppealDao.get(hql, params);
    }

    @Override
    public void update(Tsmhlappeal tsmhlappeal) {
        smhlAppealDao.update(tsmhlappeal);
    }

    @Override
    public void delAppeal(Tsmhlappeal tsmhlappeal) {
        smhlAppealDao.delete(tsmhlappeal);
    }

    @Override
    public void synchronizationUpdate(Tsmhlappeal tsmhlappeal) {
        String hql = "update Tsmhlappeal t set " ;
        Map<String, Object> params = new HashMap<String, Object>();


        if (tsmhlappeal.getAppealtype() != null){
            params.put("appealtype", tsmhlappeal.getAppealtype());
            hql = hql + "t.appealtype=:appealtype , ";
        }
        if (tsmhlappeal.getDutyleader() != null){
            params.put("dutyleader", tsmhlappeal.getDutyleader());
            hql = hql + "t.dutyleader=:dutyleader , ";
        }
        if (tsmhlappeal.getDutydept() != null){
            params.put("dutydept", tsmhlappeal.getDutydept());
            hql = hql + "t.dutydept=:dutydept , ";
        }
        if (tsmhlappeal.getDutymenber() != null){
            params.put("dutymenber", tsmhlappeal.getDutymenber());
            hql = hql + "t.dutymenber=:dutymenber , ";
        }
        if (tsmhlappeal.getSolution() != null){
            params.put("solution", tsmhlappeal.getSolution());
            hql = hql + "t.solution=:solution , " ;
        }
        if (tsmhlappeal.getTimelimit() != null){
            params.put("timelimit", tsmhlappeal.getTimelimit());
            hql = hql + "t.timelimit=:timelimit , ";
        }
        if (tsmhlappeal.getStatus() != null){
            params.put("status", tsmhlappeal.getStatus());
            hql = hql + "t.status=:status , ";
        }
        if (tsmhlappeal.getDoingstatus() != null){
            params.put("doingstatus", tsmhlappeal.getDoingstatus());
            hql = hql + "t.doingstatus=:doingstatus , ";
        }
        hql = hql.substring(0,hql.length() - 3);
        hql = hql + " where t.uuid=:uuid";
        params.put("uuid", tsmhlappeal.getUuid());
        smhlAppealDao.executeHql(hql, params);
    }

    @Override
    public List<Tsmhlappeal> getMassesSmhlAppeal(String page, String rows, String[] queryList, int source) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("source", source+"");
        String hql = "from Tsmhlappeal t where t.source = :source ";
        //诉求编号
        if (!queryList[0].equals("0")){
            params.put("uuid", queryList[0]);
            hql = hql + "and t.uuid = :uuid ";
            //诉求者
            if (!queryList[1].equals("0")){
                params.put("appealname", queryList[1]);
                hql = hql + "and t.appealname = :appealname";
            }
        }else {
            if (!queryList[1].equals("0")){
                params.put("appealname", queryList[1]);
                hql = hql + "and t.appealname = :appealname";
            }
        }

//        //诉求者联系号码
//        if (queryList[2].equals("")){
//            params.put("uuid", queryList[2]);
//            hql = hql + "t.uuid = :uuid";
//        }

        // hql = hql + " order by t.id desc";
        List<Tsmhlappeal> tsmhlappeals;
        tsmhlappeals = smhlAppealMassesDao.find(hql , params , Integer.parseInt(page), Integer.parseInt(rows));
        return tsmhlappeals;
    }

    @Override
    public Long getMassesSmhlAppealCount(String[] queryList, int source) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("source", source+"");
        String hql = "select count(*) from Tsmhlappeal t where t.source = :source ";
        //诉求编号
        if (!queryList[0].equals("0")){
            params.put("uuid", queryList[0]);
            hql = hql + "and t.uuid = :uuid ";
            //诉求者
            if (!queryList[1].equals("0")){
                params.put("appealname", queryList[1]);
                hql = hql + "and t.appealname = :appealname";
            }
        }else {
            if (!queryList[1].equals("0")){
                params.put("appealname", queryList[1]);
                hql = hql + "and t.appealname = :appealname";
            }
        }

        Long count = smhlAppealMassesDao.count(hql , params);
        return count;
    }
}
