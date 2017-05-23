package com.jmhz.device.service.impl;


import com.jmhz.device.dao.UpdatesuccessDaoI;
import com.jmhz.device.dao.UpdatesuccessMassesDaoI;
import com.jmhz.device.model.Updatesuccess;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.UpdatesuccessServiceI;
import com.jmhz.device.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UpdatesuccessServiceImpl implements UpdatesuccessServiceI{
    @Autowired
    private UpdatesuccessDaoI UpdatesuccessDao;
    @Autowired
    private UpdatesuccessMassesDaoI UpdatesuccessMassesDao;
    @Autowired
    HttpSession session;
    @Override
    public int add(Updatesuccess updatesuccess) {
        Serializable id = UpdatesuccessDao.save(updatesuccess);
        if (id == null){
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public List<Updatesuccess> getAllAppeal(int page, int rows) {
        String town = session.getAttribute("townSource")+"";
        if (town.equals("all")){
            String hql = "from Tappeal t order by t.id desc";
            List<Updatesuccess> updatesuccess = UpdatesuccessDao.find(hql ,page, rows);
            return updatesuccess;
        }else {
            Map<String, Object> params =new HashMap<String, Object>();
            params.put("townSource", town);
            String hql = "from Tappeal t where t.town = :townSource order by t.id desc";
            List<Updatesuccess> updatesuccess = UpdatesuccessDao.find(hql , params, page, rows);
            return updatesuccess;
        }
    }

    @Override
    public Long getCount() {
        String town = session.getAttribute("townSource")+"";
        if (town.equals("all")){
            Long count = UpdatesuccessDao.count("select count(*) from Tappeal t");
            return count;
        }else {
            Map<String, Object> params =new HashMap<String, Object>();
            params.put("townSource", town);
            Long count = UpdatesuccessDao.count("select count(*) from Tappeal t where t.town = :townSource",params);
            return count;
        }
    }

    @Override
    public Boolean delAppeal(Updatesuccess updatesuccess) {
        UpdatesuccessDao.delete(updatesuccess);
        return true;
    }

    @Override
    public Boolean update(Updatesuccess updatesuccess) {
        UpdatesuccessDao.update(updatesuccess);
        return true;
    }

    @Override
    public List<Updatesuccess> getAllAppeal(int page, int rows, Filters filtersClass) {

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
        List<Updatesuccess> updatesuccess = UpdatesuccessDao.find(hql, params, page, rows);
        return updatesuccess;
    }

    @Override
    public int getCount(String condition) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("condition", condition);
        //无须用到权限，所以用UpdatesuccessMassesDao借口类
        Long count = UpdatesuccessMassesDao.count("select count(*) from Updatesuccess t where t.dealStatus = :condition", params);
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
        Long count = UpdatesuccessDao.count(hql, params);
        return count;
    }

    @Override
    public List<Updatesuccess> getMassesAppeal(String page, String rows, String[] queryList) {
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
        List<Updatesuccess> updatesuccess;
        if (hql.equals("from Tappeal t")){
            updatesuccess = UpdatesuccessMassesDao.find(hql , Integer.parseInt(page), Integer.parseInt(rows));
        }else {
            updatesuccess = UpdatesuccessMassesDao.find(hql , params , Integer.parseInt(page), Integer.parseInt(rows));
        }

        return updatesuccess;
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

        Long count = UpdatesuccessMassesDao.count(hql , params);
        return count;
    }

    @Override
    public Updatesuccess getAppealById(int id) {
        String hql = "from Updatesuccess t where t.id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return UpdatesuccessMassesDao.get(hql, params);
    }

    @Override
    public void synchronizationUpdate(Updatesuccess updatesuccess) {
        String hql = "update Updatesuccess t set " ;
        Map<String, Object> params = new HashMap<String, Object>();


        if (updatesuccess.getDeviceName() != null){
            params.put("appealtype", updatesuccess.getDeviceName());
            hql = hql + "t.appealtype=:appealtype , ";
        }
        if (updatesuccess.getDeviceType() != null){
            params.put("dutyleader", updatesuccess.getDeviceType());
            hql = hql + "t.dutyleader=:dutyleader , ";
        }
        if (updatesuccess.getChange() != null){
            params.put("dutydept", updatesuccess.getChange());
            hql = hql + "t.dutydept=:dutydept , ";
        }
        if (updatesuccess.getCost()!= null){
            params.put("dutymenber", updatesuccess.getCost());
            hql = hql + "t.dutymenber=:dutymenber , ";
        }
        if (updatesuccess.getUpdateResult()!= null){
            params.put("solution", updatesuccess.getUpdateResult());
            hql = hql + "t.solution=:solution , " ;
        }
        if (updatesuccess.getDealStatus() != null){
            params.put("doingstatus", updatesuccess.getDealStatus());
            hql = hql + "t.doingstatus=:doingstatus , ";
        }
        hql = hql.substring(0,hql.length() - 3);
        hql = hql + " where t.uuid=:uuid";
        params.put("uuid", updatesuccess.getDeviceId());
        UpdatesuccessDao.executeHql(hql, params);
    }

    @Override
    public int getCountByUuid(String uuid) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("uuid", uuid);
        Long count = UpdatesuccessDao.count("select count(*) from Updatesuccess t where t.deviceId = :uuid", params);
        return count.intValue();
    }

    @Override
    public void delAppealByFarmerId(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", id);
        params.put("proposer", "0");
        String hql = "delete from Tappeal t where t.appealid=:appealid and t.proposer=:proposer";
        UpdatesuccessDao.executeHql(hql, params);
    }

    @Override
    public List<Updatesuccess> getAppealId(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", id);
        params.put("proposer", "0");
        String hql = "from Tappeal t where t.appealid=:appealid and t.proposer=:proposer";
        List<Updatesuccess> updatesuccess = UpdatesuccessDao.find(hql ,params);
        return updatesuccess;
    }

    @Override
    public List<Updatesuccess> getAppealIdByGroup(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", id);
        params.put("proposer", "1");
        String hql = "from Tappeal t where t.appealid=:appealid and t.proposer=:proposer";
        List<Updatesuccess> updatesuccess = UpdatesuccessDao.find(hql ,params);
        return updatesuccess;
    }

    @Override
    public void delAppealByGroupId(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", id);
        params.put("proposer", "1");
        String hql = "delete from Tappeal t where t.appealid=:appealid and t.proposer=:proposer";
        UpdatesuccessDao.executeHql(hql, params);
    }

    @Override
    public List<Updatesuccess> getAppealByDoingstatus(int page, int rows, String doingstatus) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("doingstatus", doingstatus);
        params.put("status", "3");
        String hql = "from Tappeal t where t.status = :status and t.doingstatus = :doingstatus order by t.uuid desc";
        List<Updatesuccess> updatesuccess = UpdatesuccessDao.find(hql,params ,page, rows);
        return updatesuccess;
    }

    @Override
    public Long getCountByDoingstatus(String doingstatus) {

        Map<String, Object> params =new HashMap<String, Object>();
        params.put("doingstatus", doingstatus);
        params.put("status", "3");
        Long count = UpdatesuccessDao.count("select count(*) from Tappeal t where t.status = :status and t.doingstatus = :doingstatus ", params);
        return count;
    }

    @Override
    public List<Updatesuccess> getAppealByDoingstatus(int page, int rows, String doingstatus, Filters filtersClass) {
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
        List<Updatesuccess> updatesuccess = UpdatesuccessDao.find(hql, params, page, rows);
        return updatesuccess;
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
        Long count = UpdatesuccessDao.count(hql, params);
        return count;
    }

    @Override
    public List<Updatesuccess> getPublicAppeal(int page, int rows) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("publish", "true");
        String hql = "from Tappeal t where t.publish=:publish order by t.id desc";
        List<Updatesuccess> updatesuccess = UpdatesuccessMassesDao.find(hql ,params,page, rows);
        return updatesuccess;
    }

    @Override
    public List<Updatesuccess> getPublicAppeal() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("publish", "true");
        String hql = "from Tappeal t where t.publish=:publish order by t.id desc";
        List<Updatesuccess> updatesuccess = UpdatesuccessMassesDao.find(hql ,params);
        return updatesuccess;
    }

    @Override
    public int getPublicAppealCount() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("publish", "true");
        String hql = "select count(*) from Tappeal t where t.publish=:publish";
        return  UpdatesuccessMassesDao.count(hql ,params).intValue();
    }


}
