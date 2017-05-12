package com.jmhz.device.service.impl;

import com.jmhz.device.backModel.UpgradeAddBack;
import com.jmhz.device.dao.*;
import com.jmhz.device.model.FaultApply;
import com.jmhz.device.model.device;
import com.jmhz.device.model.upgradeApply;
import com.jmhz.device.model.upgraderepair;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.deviceServiceI;
import com.jmhz.device.service.upgradeServiceI;
import com.jmhz.device.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by han on 2015/10/18.
 */
@Service
public class upgradeServiceImpl implements upgradeServiceI {

    @Autowired
    private deviceDaoI deviceDao;
    @Autowired
    private FaultDaoI FaultDao;
    @Autowired
    private upgradeDaoI upgradeDao;
    @Autowired
    private upgradeLogDaoI upgradeLogdao;

    @Autowired
    private FarmerMassesDaoI farmerMassesDao;

    @Override
    public int add(device devices) {
        Serializable id = deviceDao.save(devices);
        if (id == null) {
            return -1;
        }
        return Integer.parseInt(id.toString());
    }

    @Override
    public int addlog(upgraderepair logs) {
        Serializable id = upgradeLogdao.save(logs);
        if (id == null) {
            return -1;
        }
        return Integer.parseInt(id.toString());
    }
    @Override
    public int addupgrade(upgradeApply apply) {
        Serializable id = upgradeDao.save(apply);
        System.out.print("id+++"+id);
        if (id == null) {
            return -1;
        }
        return Integer.parseInt(id.toString());
    }


    @Override
    public List<upgradeApply> getAllapply(String page, String rows) {
        String hql = "from upgradeApply t order by t.id desc";
        List<upgradeApply> applys = upgradeDao.find(hql, Integer.parseInt(page), Integer.parseInt(rows));
        return applys;
    }

    @Override
    public List<upgradeApply> getAllapplyByapprove(String page, String rows, String approve) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("approve", approve);
        String hql = "from upgradeApply t where  t.approve=:approve order by t.id desc";
        List<upgradeApply> applys = upgradeDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return applys;
    }

    @Override
    public List<upgradeApply> getAllapplyByapprove(String page, String rows, int userId, String approve) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("approve", approve);
        String hql = "from upgradeApply t where t.userId=:userId and t.approve=:approve order by t.id desc";
        List<upgradeApply> applys = upgradeDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return applys;
    }

    @Override
    public Long getCountByapprove(int userId, String approve) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("approve", approve);
        String hql = "select count(*) from upgradeApply t where t.userId=:userId and t.approve=:approve ";
        return upgradeDao.count(hql, params);
    }

    @Override
    public Long getCountByapprove(String approve) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("approve", approve);
        String hql = "select count(*) from upgradeApply t where  t.approve=:approve ";
        return upgradeDao.count(hql, params);
    }

    @Override
    public Long getCount() {
        return upgradeDao.count("select count(*) from upgradeApply t");
    }

    @Override
    public int getCount(String condition) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = upgradeLogdao.count("select count(*) from upgraderepair t where t.dealStatus = :condition", params);
        return count.intValue();
    }

    @Override
    public Boolean deldevice(device devices) {
        deviceDao.delete(devices);
        return true;
    }


    @Override
    public upgraderepair getlogByapplyID(int applyId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("applyId", applyId);
        String hql = "from upgraderepair t where t.applyId = :applyId";
        return upgradeLogdao.get(hql, params);
    }
    @Override
    public upgradeApply getapplyById(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String hql = "from upgradeApply t where t.id = :id";
        return upgradeDao.get(hql, params);
    }

    @Override
    public Boolean update(upgradeApply applys) {
        upgradeDao.update(applys);
        return true;
    }

    @Override
    public device getdeviceByName(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("deviceName", name);
        String hql = "from device t where t.deviceName = :deviceName";
        return deviceDao.get(hql, params);
    }
    @Override
    public Long getCount(int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "select count(*) from upgradeApply t where t.userId=:userId";
        return deviceDao.count(hql, params);
    }

    @Override
    public List<device> getAlldevice(String page, String rows, Filters filtersClass) {
        return null;
    }
    @Override
    public List<upgradeApply> getAllapply(String page, String rows, int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "from upgradeApply t where t.userId=:userId order by t.id desc";
        List<upgradeApply> applys = upgradeDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return applys;
    }

    @Override
    public List<upgradeApply> getAllapply(String page, String rows, Filters filtersClass) {

        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from upgradeApply t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<upgradeApply> applys = upgradeDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return applys;
    }

    @Override
    public List<upgradeApply> getAllapply(String page, String rows, Filters filtersClass, int userId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from upgradeApply t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " and t.userId= :userId order by t.id desc";
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<upgradeApply> applys = upgradeDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return applys;
    }
    @Override
    public Long getCount(Filters filtersClass, int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from device t where t.userId= :userId and  ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = deviceDao.count(hql, params);
        return count;
    }
    @Override
    public int getUsingCount(String condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = deviceDao.count("select count(*) from device t where t.status = :condition", params);
        return count.intValue();
    }

    @Override
    public int getDiscardCount(String condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = deviceDao.count("select count(*) from device t where t.status = :condition", params);
        return count.intValue();
    }

    @Override
    public int getUnuseCount(String condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = deviceDao.count("select count(*) from device t where t.status = :condition", params);
        return count.intValue();
    }

    @Override
    public int getPublicCount(String condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = deviceDao.count("select count(*) from device t where t.deviceType = :condition", params);
        return count.intValue();
    }

    @Override
    public int getPrivateCount(String condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = deviceDao.count("select count(*) from device t where t.deviceType = :condition", params);
        return count.intValue();
    }

    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from upgradeApply t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

        //List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = upgradeDao.count(hql, params);
        return count;
    }

    //    @Override
//    public List<device> getAlldeviceByPlant(String page, String rows) {
//        String hql = "from device_info t where t.plantingproject != ''order by t.id desc";
//        List<device> devices = deviceDao.find(hql, Integer.parseInt(page), Integer.parseInt(rows));
//        return devices;
//    }

   /* @Override
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
    }*/

   /* @Override
    public device getdevice(String householdername, String contactnumber) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("householdername", householdername);
        params.put("contactnumber", contactnumber);
        String hql = "from Tfarmer t where t.householdername = :householdername and t.contactnumber = :contactnumber";
        return  farmerDao.get(hql, params);
    }
*/
    /*@Override
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
    }*/
}
