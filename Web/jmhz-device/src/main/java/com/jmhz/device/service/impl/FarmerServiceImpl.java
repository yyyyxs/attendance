package com.jmhz.device.service.impl;

import com.jmhz.device.dao.FarmerDaoI;
import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.dao.FarmerMassesDaoI;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.FarmerServiceI;
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
public class FarmerServiceImpl implements FarmerServiceI{

    @Autowired
    private FarmerDaoI farmerDao;
    @Autowired
    private FarmerMassesDaoI farmerMassesDao;

    @Override
    public int add(Tfarmer tfarmer) {
        Serializable id = farmerDao.save(tfarmer);
        if (id == null){
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public List<Tfarmer> getAllFarmer(String page, String rows) {
        String hql = "from Tfarmer t order by t.id desc";
        List<Tfarmer> farmers = farmerDao.find(hql, Integer.parseInt(page), Integer.parseInt(rows));
        return farmers;
    }

    @Override
    public Long getCount() {
        return farmerDao.count("select count(*) from Tfarmer t");
    }

    @Override
    public Boolean delFarmer(Tfarmer tfarmer) {
        farmerDao.delete(tfarmer);
        return true;
    }

    @Override
    public Tfarmer getFarmerById(int id) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("id", id);
        String hql = "from Tfarmer t where t.id = :id";
        return  farmerDao.get(hql, params);
    }

    @Override
    public Boolean update(Tfarmer tfarmer) {
        farmerDao.update(tfarmer);
        return true;
    }

    @Override
    public Tfarmer getFarmerByName(String name) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("householdername", name);
        String hql = "from Tfarmer t where t.householdername = :householdername";
        return  farmerDao.get(hql, params);
    }

    @Override
    public List<Tfarmer> getAllFarmer(String page, String rows, Filters filtersClass) {

        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tfarmer t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<Tfarmer> managers = farmerDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return managers;
    }

    @Override
    public List<Tfarmer> getAllFarmerByPlant(String page, String rows) {
        String hql = "from Tfarmer t where t.plantingproject != ''order by t.id desc";
        List<Tfarmer> farmers = farmerDao.find(hql, Integer.parseInt(page), Integer.parseInt(rows));
        return farmers;
    }

    @Override
    public Long getCountByPlant() {
        return farmerDao.count("select count(*) from Tfarmer t where t.plantingproject != ''");
    }

    @Override
    public List<Tfarmer> getAllFarmerByPlant(String page, String rows, Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tfarmer t where t.plantingproject != '' and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<Tfarmer> managers = farmerDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return managers;
    }

    @Override
    public Long getCountByPlant(Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tfarmer t where t.plantingproject != '' and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = farmerDao.count(hql, params);
        return count;
    }

    @Override
    public int getPlantCount() {
        return farmerDao.count("select count(*) from Tfarmer t where t.plantingproject != ''").intValue();
    }

    @Override
    public int getFarmingCount() {
        return farmerDao.count("select count(*) from Tfarmer t where t.farmingproject != ''").intValue();
    }

    @Override
    public int getSnackCount() {
        return farmerDao.count("select count(*) from Tfarmer t where t.snackprovince != ''").intValue();
    }

    @Override
    public int getWorkCount() {
        return farmerDao.count("select count(*) from Tfarmer t where t.workprofession != ''").intValue();
    }

    @Override
    public int getFoundedCount() {
        return farmerDao.count("select count(*) from Tfarmer t where t.foundedname != ''").intValue();
    }

    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tfarmer t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = farmerDao.count(hql, params);
        return count;
    }

    @Override
    public List<Tfarmer> querySpecialFamily(String page, String rows, String query) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("query", "%"+query+"%");
        String hql = "from Tfarmer t where t.specialfamily like :query order by t.id desc";
        List<Tfarmer> farmers = farmerDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return farmers;
    }

    @Override
    public List<Tfarmer> queryHousingSituation(String page, String rows, String query) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("query", "%"+query+"%");
        String hql = "from Tfarmer t where t.housingsituation like :query order by t.id desc";
        List<Tfarmer> farmers = farmerDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return farmers;
    }

    @Override
    public Long getSpecialFamilyCount(String query) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("query", "%"+query+"%");
        String hql = "select count(*) from Tfarmer t where t.specialfamily like :query ";
        Long count = farmerDao.count(hql, params);
        return count;
    }

    @Override
    public Long getHousingSituationCount(String query) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("query", "%"+query+"%");
        String hql = "select count(*) from Tfarmer t where t.housingsituation like :query ";
        Long count = farmerDao.count(hql, params);
        return count;
    }

    @Override
    public List<Tfarmer> querySpecialFamily(String page, String rows, String query, Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("query", "%"+query+"%");
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tfarmer t where t.specialfamily like :query and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<Tfarmer> managers = farmerDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return managers;
    }

    @Override
    public List<Tfarmer> queryHousingSituation(String page, String rows, String query, Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("query", "%"+query+"%");
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from Tfarmer t where t.housingsituation like :query and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<Tfarmer> managers = farmerDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return managers;
    }

    @Override
    public Long getSpecialFamilyCount(String query, Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("query", "%"+query+"%");
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tfarmer t where t.specialfamily like :query and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = farmerDao.count(hql, params);
        return count;
    }

    @Override
    public Long getHousingSituationCount(String query, Filters filtersClass) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("query", "%"+query+"%");
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from Tfarmer t where t.housingsituation like :query and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()){

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = farmerDao.count(hql, params);
        return count;
    }

    @Override
    public Tfarmer getFarmer(String householdername, String contactnumber) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("householdername", householdername);
        params.put("contactnumber", contactnumber);
        String hql = "from Tfarmer t where t.householdername = :householdername and t.contactnumber = :contactnumber";
        return  farmerDao.get(hql, params);
    }

    @Override
    public Tfarmer getFarmer(String householdername) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("householdername", householdername);
        String hql = "from Tfarmer t where t.householdername = :householdername";
        return  farmerDao.get(hql, params);
    }

    @Override
    public void updateGridCharger(Tfarmer tfarmer) {
        String hql = "update Tfarmer t set t.gridcharger = :gridcharger where t.village = :village and" +
                " t.grid = :grid" ;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("gridcharger", tfarmer.getGridcharger());
        params.put("village", tfarmer.getVillage());
        params.put("grid", tfarmer.getGrid());
        farmerDao.executeHql(hql, params);
    }

    @Override
    public Tfarmer getFarmerMassesById(int id) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("id", id);
        String hql = "from Tfarmer t where t.id = :id";
        return  farmerMassesDao.get(hql, params);
    }

    @Override
    public Tfarmer getFarmerByNameAndTel(String name, String telephone) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("householdername", name);
        params.put("contactnumber", telephone);
        String hql = "from Tfarmer t where t.householdername = :householdername and t.contactnumber = :contactnumber";
        return  farmerDao.get(hql, params);
    }

    @Override
    public List<Tfarmer> getFarmerByTownName(String townName) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("town", townName);
        String hql = "from Tfarmer t where t.town = :town";
        return  farmerDao.find(hql, params);
    }

    @Override
    public List<Tfarmer> getFarmerByVillageName(String villageName) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("village", villageName);
        String hql = "from Tfarmer t where t.village = :village";
        return  farmerDao.find(hql, params);
    }
}
