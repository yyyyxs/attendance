package com.jmhz.device.service.impl;

import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.dao.GroupDaoI;
import com.jmhz.device.dao.GroupMassesDaoI;
import com.jmhz.device.dao.VillageDaoI;
import com.jmhz.device.model.Tgroup;
import com.jmhz.device.model.Tvillage;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.GroupServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 锋情 on 2014/4/19.
 */
@Service
public class GroupServiceImpl implements GroupServiceI{

    @Autowired
    private GroupDaoI groupDao;
    @Autowired
    private GroupMassesDaoI groupMassesDao;
    @Autowired
    private VillageDaoI villageDao;

    @Override
    public int add(Tgroup tgroup) {

        Serializable id = groupDao.save(tgroup);
        if (id == null){
            return -1;
        }
        return Integer.parseInt(id.toString());
    }
    @Override
    public List<Tgroup> getAllGroup(String page, String rows) {
        String hql = "from Tgroup t order by t.id desc";
        List<Tgroup> tgroups = groupDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        return tgroups;
    }
    @Override
    public Long getCount() {
        Long count = groupDao.count("select count(*) from Tgroup t");
        return count;
    }
    @Override
    public Boolean updateTgroup(Tgroup tgroup){
        groupDao.update(tgroup);
        return true;
    }
    @Override
    public Boolean delTgroup(Tgroup tgroup){
        groupDao.delete(tgroup);
        return  true;
    }
    @Override
    public List<Tgroup> getAllGroup(String page, String rows, ArrayList<FilterRule> rules) {
        //String hql = "from Tgroup t where t.name = :name order by t.id desc";

        Map<String, Object> params =new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tgroup t where ");
        for (FilterRule filterRule : rules){
            params.put(filterRule.getField(), filterRule.getData());
            stringBuilder.append("t."+filterRule.getField()+" = "+":"+filterRule.getField()+" and ");
        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
        //List<Tgroup> managers = tgroupDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
       List<Tgroup> tgroups = groupDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return tgroups;
    }

    @Override
    public List<Tgroup> getAllGroup(String page, String rows, Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();


        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tgroup t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
//        List<Tgroup> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<Tgroup> tgroups = groupDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return tgroups;
    }
    @Override
    public Tgroup getGroupByName(String name){
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("groupname", name);
        String hql = "from Tgroup t where t.groupname = :groupname";
        return  groupDao.get(hql, params);
    }
    @Override
    public Tgroup getGroupById(int id) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("id", id);
        String hql = "from Tgroup t where t.id = :id";
        return  groupDao.get(hql, params);
    }

//    村情
    @Override
    public int addv(Tvillage tvillage){
        Serializable id = villageDao.save(tvillage);
        if(id==null){
            return -1;
        }
        return Integer.parseInt(id.toString());
    }
    @Override
    public Tvillage getVillage(String village){
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("village", village);
        String hql = "from Tvillage t where t.village = :village";
        return villageDao.get(hql, params);
    }


    @Override
    public List<Tvillage> getAllVillage(String page, String rows) {
        String hql = "from Tvillage t order by t.id desc";
        List<Tvillage> tvillages = villageDao.find(hql, Integer.parseInt(page), Integer.parseInt(rows));
        return tvillages;
    }


    @Override
    public List<Tvillage> getAllVillage(String page, String rows, Filters filtersClass) {

        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tvillage t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<Tvillage> managers = villageDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return managers;
    }

    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tvillage t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = villageDao.count(hql, params);
        return count;
    }

    @Override
    public Long getvillageCount() {
        Long count = villageDao.count("select count(*) from Tvillage t");
        return count;
    }

    @Override
    public Boolean delVillage(Tvillage tvillage) {
        villageDao.delete(tvillage);
        return true;
    }

    @Override
    public Tvillage getVillageById(int id) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("id", id);
        String hql = "from Tvillage t where t.id = :id";
        return  villageDao.get(hql, params);
    }
    @Override
    public Boolean update(Tvillage tvillage) {
        villageDao.update(tvillage);
        return true;
    }

    @Override
    public Tgroup getGroupMassesById(int id) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("id", id);
        String hql = "from Tgroup t where t.id = :id";
        return  groupMassesDao.get(hql, params);
    }


}
