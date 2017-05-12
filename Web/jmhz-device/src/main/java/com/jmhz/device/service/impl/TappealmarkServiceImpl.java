package com.jmhz.device.service.impl;

import com.jmhz.device.dao.TappealmarkDaoI;
import com.jmhz.device.model.Tappealmark;
import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.TappealmarkServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by 陈鑫on 2014/4/22.
 */
@Service
public class TappealmarkServiceImpl implements TappealmarkServiceI {
    @Autowired
    private TappealmarkDaoI tappealmarkDao;
    @Override
    public int add(Tappealmark tappealmark) {
        Serializable id = tappealmarkDao.save(tappealmark);
        if (id == null) {
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public List<Tappealmark> getAllAppealmark(int page, int rows) {
        String hql = "from Tappealmark t  order by t.id ";
        List<Tappealmark> tappealmarks = tappealmarkDao.find(hql, page, rows);
        return tappealmarks;
    }
    @Override
    public List<Tappealmark> getAllAppealmark(int  appealid, int page, int rows) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", appealid);
        String hql = "from Tappealmark t where t.appealid=:appealid order by t.id ";
        List<Tappealmark> tappealmarks = tappealmarkDao.find(hql, params, page, rows);
        return tappealmarks;
    }

    @Override
    public List<Tappealmark> getAllAppealmark(int appealid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", appealid);
        String hql = "from Tappealmark t where t.appealid=:appealid order by t.id desc";
        List<Tappealmark> tappealmarks = tappealmarkDao.find(hql, params);
        return tappealmarks;
    }

    @Override
    public List<Tappealmark> getAllAppealmark(int page, int rows, Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tappealmark t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){
            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);
        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
        List<Tappealmark> tappealmarks = tappealmarkDao.find(hql, params, page, rows);
        return tappealmarks;
    }

    @Override
    public Long getCount() {
        Long count = tappealmarkDao.count("select count(*) from Tappealmark");
        return count;
    }

    @Override
    public Long getCount(int appealid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("appealid", appealid);
        String hql = "select count(*) from Tappealmark t where t.appealid=:appealid";
        return tappealmarkDao.count(hql, params);
    }

    @Override
    public Boolean delAppealmark(Tappealmark tappealmark) {
        tappealmarkDao.delete(tappealmark);
        return true;
    }

    @Override
    public Boolean update(Tappealmark tappealmark) {
        tappealmarkDao.update(tappealmark);
        return true;
    }

    @Override
    public void updateRemark(int id, String remark, String newDate) {
        String hqlToUpdate = "update Tappealmark t set t.processupdateremark=:remark,t.processupdatetime=:newDate where t.id=:id";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("remark", remark);
        params.put("newDate", newDate);
        tappealmarkDao.executeHql(hqlToUpdate, params);
    }

    @Override
    public Tappealmark getTappealmarkByHouseholdername(String name){
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("householdername", name);
        String hql = "from Tappealmark t where t.householdername = :householdername";
        return  tappealmarkDao.get(hql, params);
    }

    //诉求状态跟踪 显示调用方法（二）
    @Override
    public Tappealmark getAppealmarkByappealid(int appealid) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("appealid", appealid);
        String hql = "from Tappealmerk t where t.appealid = :appealid";
        return  tappealmarkDao.get(hql, params);
    }

    @Override
    public  List<Tappealmark> getAppealmarkByUuid(String uuid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", uuid);
        String hql = "from Tappealmark t where t.uuid=:uuid order by t.id desc";
        List<Tappealmark> tappealmarks = tappealmarkDao.find(hql, params);
        return tappealmarks;
    }

    @Override
    public List<Tappealmark> getAppealmarkByUuid(String uuid, int page, int rows) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", uuid);
        String hql = "from Tappealmark t where t.uuid=:uuid order by t.id ";
        List<Tappealmark> tappealmarks = tappealmarkDao.find(hql, params, page, rows);
        return tappealmarks;
    }

    @Override
    public Long getCountByUuid(String uuid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", uuid);
        String hql = "select count(*) from Tappealmark t where t.uuid=:uuid";
        return tappealmarkDao.count(hql, params);
    }

    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tappealmark t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){
            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);
        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

        return tappealmarkDao.count(hql, params);
    }

    @Override
    public List<Tappealmark> getAllAppealmark(int page, int rows, Filters filtersClass, String uuid) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tappealmark t where t.uuid=:uuid and ");
        params.put("uuid", uuid);
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){
            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);
        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
        List<Tappealmark> tappealmarks = tappealmarkDao.find(hql, params, page, rows);
        return tappealmarks;
    }

    @Override
    public Long getCount(Filters filtersClass, String uuid) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tappealmark t where t.uuid=:uuid and ");
        params.put("uuid", uuid);
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){
            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);
        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

        return tappealmarkDao.count(hql, params);
    }

    @Override
    public void delAppealmarkByUuid(String uuid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", uuid);
        String hql = " delete from Tappealmark t where t.uuid=:uuid";
        tappealmarkDao.executeHql(hql, params);
    }
}
