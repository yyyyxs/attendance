package com.jmhz.device.service.impl;

import com.jmhz.device.dao.SmOriginalAppealDaoI;
import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.model.Tsmoriginalappeal;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.SmOriginalAppealServiceI;
import com.jmhz.device.util.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 锋情 on 2014/4/18.
 */
@Service
public class SmOriginalAppealServiceImpl implements SmOriginalAppealServiceI{

    @Autowired
    private SmOriginalAppealDaoI smOriginalAppealDao;


    @Override
    public List<Tsmoriginalappeal> getAllAppeal(int page, int rows) {
//        String hql = "from Tsmoriginalappeal t order by t.status ASC , t.id DESC";
        String hql = "from Tsmoriginalappeal t order by t.id DESC";
        List<Tsmoriginalappeal> tsmoriginalappeals = smOriginalAppealDao.find(hql ,page, rows);
        return tsmoriginalappeals;
    }

    @Override
    public Long getCount() {
        Long count = smOriginalAppealDao.count("select count(*) from Tsmoriginalappeal");
        return count;
    }

    @Override
    public List<Tsmoriginalappeal> getAllAppeal(int page, int rows, Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tsmoriginalappeal t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id DESC";
        List<Tsmoriginalappeal> tsmoriginalappeals = smOriginalAppealDao.find(hql, params, page, rows);
        return tsmoriginalappeals;
    }

    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tsmoriginalappeal t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        Long count = smOriginalAppealDao.count(hql, params);
        return count;
    }

    @Override
    public void delAppeal(Tsmoriginalappeal tsmoriginalappeal) {
        smOriginalAppealDao.delete(tsmoriginalappeal);
    }

    @Override
    public Tsmoriginalappeal getAppealById(String id) {
        String hql = "from Tsmoriginalappeal t where t.id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", Integer.parseInt(id));
        return smOriginalAppealDao.get(hql, params);
    }

    @Override
    public void update(Tsmoriginalappeal tsmoriginalappeal) {
        smOriginalAppealDao.update(tsmoriginalappeal);
    }

    @Override
    public Boolean add(Tsmoriginalappeal tsmoriginalappeal) {
        String sql = "INSERT INTO tsmoriginalappeal(tel, content, createtime) VALUES (?, ?, ?)";

        int res = JDBCUtils.inserOriginalappeal(sql, tsmoriginalappeal);
        if (res > 0){
            return true;
        }
        return false;
    }
}
