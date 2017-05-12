package com.jmhz.device.service.impl;

import com.jmhz.device.dao.AppealMassesDaoI;
import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.dao.AppealDaoI;
import com.jmhz.device.model.Tappeal;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.AppealServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.*;

/**
 * Created by 锋情 on 2014/4/18.
 */
@Service
public class AppealServiceImpl implements AppealServiceI{
    @Autowired
    private AppealDaoI appealDao;
    @Autowired
    private AppealMassesDaoI appealMassesDao;
    @Autowired
    HttpSession session;
    @Override
    public int add(Tappeal tappeal) {
        Serializable id = appealDao.save(tappeal);
        if (id == null){
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public List<Tappeal> getAllAppeal(int page, int rows) {
        String town = session.getAttribute("townSource")+"";
        if (town.equals("all")){
            String hql = "from Tappeal t order by t.id desc";
            List<Tappeal> tappeals = appealDao.find(hql ,page, rows);
            return tappeals;
        }else {
            Map<String, Object> params =new HashMap<String, Object>();
            params.put("townSource", town);
            String hql = "from Tappeal t where t.town = :townSource order by t.id desc";
            List<Tappeal> tappeals = appealDao.find(hql , params, page, rows);
            return tappeals;
        }
    }

    @Override
    public Long getCount() {
        String town = session.getAttribute("townSource")+"";
        if (town.equals("all")){
            Long count = appealDao.count("select count(*) from Tappeal t");
            return count;
        }else {
            Map<String, Object> params =new HashMap<String, Object>();
            params.put("townSource", town);
            Long count = appealDao.count("select count(*) from Tappeal t where t.town = :townSource",params);
            return count;
        }
    }

    @Override
    public Boolean delAppeal(Tappeal tappeal) {
        appealDao.delete(tappeal);
        return true;
    }

    @Override
    public Boolean update(Tappeal tappeal) {
        appealDao.update(tappeal);
        return true;
    }

    @Override
    public List<Tappeal> getAllAppeal(int page, int rows, Filters filtersClass) {

        Map<String, Object> params =new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tappeal t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
        List<Tappeal> tappeals = appealDao.find(hql, params, page, rows);
        return tappeals;
    }

    @Override
    public int getCount(String condition) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = appealDao.count("select count(*) from Tappeal t where t.status = :condition", params);
        return count.intValue();
    }

    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tappeal t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        Long count = appealDao.count(hql, params);
        return count;
    }

    @Override
    public List<Tappeal> getMassesAppeal(String page, String rows, String[] queryList) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "from Tappeal t";
        //诉求编号
        if (!queryList[0].equals("0")){
            params.put("uuid", queryList[0]);
            hql = hql + "  where t.uuid = :uuid ";
            //诉求者
            if (!queryList[1].equals("0")){
                params.put("appealname", queryList[1]);
                hql = hql + "and t.appealname = :appealname";
            }
        }else {
            if (!queryList[1].equals("0")){
                params.put("appealname", queryList[1]);
                hql = hql + "  where t.appealname = :appealname";
            }
        }

//        //诉求者联系号码
//        if (queryList[2].equals("")){
//            params.put("uuid", queryList[2]);
//            hql = hql + "t.uuid = :uuid";
//        }

       // hql = hql + " order by t.id desc";
        List<Tappeal> tappeals;
        if (hql.equals("from Tappeal t")){
            tappeals = appealMassesDao.find(hql , Integer.parseInt(page), Integer.parseInt(rows));
        }else {
            tappeals = appealMassesDao.find(hql , params , Integer.parseInt(page), Integer.parseInt(rows));
        }

        return tappeals;
    }

    @Override
    public Long getMassesAppealCount(String[] queryList) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "select count(*) from Tappeal t";
        //诉求编号
        if (!queryList[0].equals("0")){
            params.put("uuid", queryList[0]);
            hql = hql + "  where t.uuid = :uuid ";
            //诉求者
            if (!queryList[1].equals("0")){
                params.put("appealname", queryList[1]);
                hql = hql + "and t.appealname = :appealname";
            }
        }else {
            if (!queryList[1].equals("0")){
                params.put("appealname", queryList[1]);
                hql = hql + "  where t.appealname = :appealname";
            }
        }

        Long count = appealMassesDao.count(hql , params);
        return count;
    }

    @Override
    public Tappeal getAppealById(int id) {
        String hql = "from Tappeal t where t.id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return appealMassesDao.get(hql, params);
    }

    @Override
    public void synchronizationUpdate(Tappeal tappeal) {
        String hql = "update Tappeal t set " ;
        Map<String, Object> params = new HashMap<String, Object>();


        if (tappeal.getAppealtype() != null){
            params.put("appealtype", tappeal.getAppealtype());
            hql = hql + "t.appealtype=:appealtype , ";
        }
        if (tappeal.getDutyleader() != null){
            params.put("dutyleader", tappeal.getDutyleader());
            hql = hql + "t.dutyleader=:dutyleader , ";
        }
        if (tappeal.getDutydept() != null){
            params.put("dutydept", tappeal.getDutydept());
            hql = hql + "t.dutydept=:dutydept , ";
        }
        if (tappeal.getDutymenber() != null){
            params.put("dutymenber", tappeal.getDutymenber());
            hql = hql + "t.dutymenber=:dutymenber , ";
        }
        if (tappeal.getSolution() != null){
            params.put("solution", tappeal.getSolution());
            hql = hql + "t.solution=:solution , " ;
        }
        if (tappeal.getTimelimit() != null){
            params.put("timelimit", tappeal.getTimelimit());
            hql = hql + "t.timelimit=:timelimit , ";
        }
        if (tappeal.getStatus() != null){
            params.put("status", tappeal.getStatus());
            hql = hql + "t.status=:status , ";
        }
        if (tappeal.getDoingstatus() != null){
            params.put("doingstatus", tappeal.getDoingstatus());
            hql = hql + "t.doingstatus=:doingstatus , ";
        }
        hql = hql.substring(0,hql.length() - 3);
        hql = hql + " where t.uuid=:uuid";
        params.put("uuid", tappeal.getUuid());
        appealDao.executeHql(hql, params);
    }

    @Override
    public int getCountByUuid(String uuid) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("uuid", uuid);
        Long count = appealDao.count("select count(*) from Tappeal t where t.uuid = :uuid", params);
        return count.intValue();
    }

    @Override
    public void delAppealByFarmerId(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", id);
        params.put("proposer", "0");
        String hql = "delete from Tappeal t where t.appealid=:appealid and t.proposer=:proposer";
        appealDao.executeHql(hql, params);
    }

    @Override
    public List<Tappeal> getAppealId(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", id);
        params.put("proposer", "0");
        String hql = "from Tappeal t where t.appealid=:appealid and t.proposer=:proposer";
        List<Tappeal> tappeals = appealDao.find(hql ,params);
        return tappeals;
    }

    @Override
    public List<Tappeal> getAppealIdByGroup(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", id);
        params.put("proposer", "1");
        String hql = "from Tappeal t where t.appealid=:appealid and t.proposer=:proposer";
        List<Tappeal> tappeals = appealDao.find(hql ,params);
        return tappeals;
    }

    @Override
    public void delAppealByGroupId(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", id);
        params.put("proposer", "1");
        String hql = "delete from Tappeal t where t.appealid=:appealid and t.proposer=:proposer";
        appealDao.executeHql(hql, params);
    }

    @Override
    public List<Tappeal> getAppealByDoingstatus(int page, int rows, String doingstatus) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("doingstatus", doingstatus);
        params.put("status", "3");
        String hql = "from Tappeal t where t.status = :status and t.doingstatus = :doingstatus order by t.uuid desc";
        List<Tappeal> tappeals = appealDao.find(hql,params ,page, rows);
        return tappeals;
    }

    @Override
    public Long getCountByDoingstatus(String doingstatus) {

        Map<String, Object> params =new HashMap<String, Object>();
        params.put("doingstatus", doingstatus);
        params.put("status", "3");
        Long count = appealDao.count("select count(*) from Tappeal t where t.status = :status and t.doingstatus = :doingstatus ", params);
        return count;
    }

    @Override
    public List<Tappeal> getAppealByDoingstatus(int page, int rows, String doingstatus, Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("doingstatus", doingstatus);
        params.put("status", "3");

        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tappeal t where t.status = :status and t.doingstatus = :doingstatus and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
        List<Tappeal> tappeals = appealDao.find(hql, params, page, rows);
        return tappeals;
    }

    @Override
    public Long getCountByDoingstatus(String doingstatus, Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("doingstatus", doingstatus);
        params.put("status", "3");

        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tappeal t where t.status = :status and t.doingstatus = :doingstatus and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        Long count = appealDao.count(hql, params);
        return count;
    }

    @Override
    public List<Tappeal> getPublicAppeal(int page, int rows) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("publish", "true");
        String hql = "from Tappeal t where t.publish=:publish order by t.id desc";
        List<Tappeal> tappeals = appealMassesDao.find(hql ,params,page, rows);
        return tappeals;
    }

    @Override
    public List<Tappeal> getPublicAppeal() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("publish", "true");
        String hql = "from Tappeal t where t.publish=:publish order by t.id desc";
        List<Tappeal> tappeals = appealMassesDao.find(hql ,params);
        return tappeals;
    }

    @Override
    public int getPublicAppealCount() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("publish", "true");
        String hql = "select count(*) from Tappeal t where t.publish=:publish";
        return  appealMassesDao.count(hql ,params).intValue();
    }


}
