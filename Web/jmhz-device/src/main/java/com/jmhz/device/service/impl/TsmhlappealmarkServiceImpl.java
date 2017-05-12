package com.jmhz.device.service.impl;

import com.jmhz.device.dao.TsmhlappealmarkDaoI;
import com.jmhz.device.model.Tsmhlappealmark;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.TsmhlappealmarkServiceI;
import com.jmhz.device.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wby on 2014/7/11.
 */
@Service
public class TsmhlappealmarkServiceImpl implements TsmhlappealmarkServiceI {
    @Autowired
    private TsmhlappealmarkDaoI tsmhlappealmarkDao;
    @Override
    public int add(Tsmhlappealmark tsmhlappealmark) {
        Serializable id = tsmhlappealmarkDao.save(tsmhlappealmark);
        if (id == null) {
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public List<Tsmhlappealmark> getSmhlappealmarkByUuid(String uuid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", uuid);
        String hql = "from Tsmhlappealmark t where t.uuid=:uuid order by t.id desc";
        List<Tsmhlappealmark> tsmhlappealmarks = tsmhlappealmarkDao.find(hql, params);
        return tsmhlappealmarks;
    }

    @Override
    public List<Tsmhlappealmark> getSmhlappealmarkByUuid(String uuid, int page, int rows) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", uuid);
        String hql = "from Tsmhlappealmark t where t.uuid=:uuid order by t.id ";
        List<Tsmhlappealmark> tsmhlappealmarks = tsmhlappealmarkDao.find(hql, params, page, rows);
        return tsmhlappealmarks;
    }

    @Override
    public Long getCountByUuid(String uuid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("uuid", uuid);
        String hql = "select count(*) from Tsmhlappealmark t where t.uuid=:uuid";
        return tsmhlappealmarkDao.count(hql, params);
    }



    @Override
    public List<Tsmhlappealmark> getAllSmhlappealmark(int page, int rows, Filters filtersClass, String uuid) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tsmhlappealmark t where t.uuid=:uuid and ");
        params.put("uuid", uuid);
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){
            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);
        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
        List<Tsmhlappealmark> tsmhlappealmarks = tsmhlappealmarkDao.find(hql, params, page, rows);
        return tsmhlappealmarks;
    }

    @Override
    public Long getCount(Filters filtersClass, String uuid) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tsmhlappealmark t where t.uuid=:uuid and ");
        params.put("uuid", uuid);
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){
            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);
        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

        return tsmhlappealmarkDao.count(hql, params);
    }

    @Override
    public Boolean delAppealmark(Tsmhlappealmark tsmhlappealmark) {
        tsmhlappealmarkDao.delete(tsmhlappealmark);
        return true;
    }

    @Override
    public void updateRemark(int id, String remark, String newDate) {
        String hqlToUpdate = "update Tsmhlappealmark t set t.processupdateremark=:remark,t.processupdatetime=:newDate where t.id=:id";
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("remark", remark);
        params.put("newDate", newDate);
        tsmhlappealmarkDao.executeHql(hqlToUpdate, params);
    }




}
