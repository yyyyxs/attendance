package com.jmhz.device.service.impl;

import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.dao.FarmerVisitDaoI;
import com.jmhz.device.model.Tfarmervisit;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.FarmerVisitServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FarmetVisitServiceImpl implements FarmerVisitServiceI{

    @Autowired
    private FarmerVisitDaoI farmerVisitDao;

    @Override
    public int add(Tfarmervisit tfarmervisit) {
        
        Serializable id = farmerVisitDao.save(tfarmervisit);
        if (id == null){
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public Long getCountById(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return farmerVisitDao.count("select count(*) from Tfarmervisit t where t.farmerid=:id", params);
    }

    @Override
    public Boolean update(Tfarmervisit tfarmervisit) {
        farmerVisitDao.update(tfarmervisit);
        return true;
    }

    @Override
    public int getCount(String condition) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = farmerVisitDao.count("select count(*) from Tfarmervisit t where t.status = :condition", params);
        return count.intValue();
    }

    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tfarmervisit t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        Long count = farmerVisitDao.count(hql, params);
        return count;
    }

    @Override
    public List<Tfarmervisit> getAllVisitList(int farmerid, int page, int rows) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("farmerid", farmerid);
        String hql = "from Tfarmervisit t where t.farmerid=:farmerid order by t.id desc";
        return farmerVisitDao.find(hql, params, page, rows);
    }

    @Override
    public List<Tfarmervisit> getAllVisitList(int farmerid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("farmerid", farmerid);
        String hql = "from Tfarmervisit t where t.farmerid=:farmerid order by t.id desc";
        return farmerVisitDao.find(hql, params);
    }

    @Override
    public void updateFarmerVisit(int id, String visittime, String visitremark) {
        String hqlToRate = "update Tfarmervisit t set t.visittime=:visittime,t.visitremark=:visitremark,t.inserttime=:newdate where t.id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("visittime", visittime);
        params.put("visitremark", visitremark);
        params.put("newdate", new Date());
        farmerVisitDao.executeHql(hqlToRate, params);
    }

    @Override
    public void deleteFarmerVisit(int farmerid) {
        Tfarmervisit tfarmervisit = new Tfarmervisit();
        tfarmervisit.setId(farmerid);
        farmerVisitDao.delete(tfarmervisit);
    }

}
