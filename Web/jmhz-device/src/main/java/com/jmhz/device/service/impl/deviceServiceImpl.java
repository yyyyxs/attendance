package com.jmhz.device.service.impl;

import com.jmhz.device.dao.FaultDaoI;
import com.jmhz.device.dao.deviceDaoI;
import com.jmhz.device.model.device;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.deviceServiceI;
import com.jmhz.device.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by 林炜 on 2015/10/18.
 */
@Service
public class deviceServiceImpl implements deviceServiceI {

    @Autowired
    private deviceDaoI deviceDao;
    @Autowired
    private FaultDaoI FaultDao;


    @Override
    public int add(device devices) {
        Serializable id = deviceDao.save(devices);
        if (id == null) {
            return -1;
        }

        return Integer.parseInt(id.toString());
    }



    @Override
    public List<device> getAlldevice(String page, String rows, int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "from device t where t.userId= :userId and t.deleteFlag=0 order by t.id desc";

        List<device> devices = deviceDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return devices;
    }

    @Override
    public List<device> getAlldevice(String page, String rows) {
        String hql = "from device t where t.deleteFlag=0 order by t.id desc";
        List<device> devices = deviceDao.find(hql, Integer.parseInt(page), Integer.parseInt(rows));
        return devices;
    }

    @Override
    public List<device> getdeviceByCondition(String condition1, String condition2, String page, String rows) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition2", condition2);
//        String hql="from device t where :condition1=:condition2";
        String hql = "from device t where t." + condition1 + "=:condition2";

        List<device> devices = deviceDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return devices;
    }

    @Override
    public Long getCountByCondition(String condition1, String condition2) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("condition2", condition2);
        String hql = "select count(*) from device t where t." + condition1 + "=:condition2";
        return deviceDao.count(hql, params);
    }


    @Override
    public Long getCount() {
        //

        return deviceDao.count("select count(*) from device t where t.deleteFlag=0");
    }

    @Override
    public Long getCount(int userId) {
        //
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "select count(*) from device t where t.userId= :userId and t.deleteFlag=0";
        Long count = deviceDao.count(hql, params);
        return count;
    }

    @Override //获取某一年份的设备数量 2015-10-22 by 脐橙
    public int getCountBybuyYear(String buyYear) {
        String hql="select count(*) from device t where t.buyYear="+buyYear;
        Long count = deviceDao.count(hql);
        return count.intValue();
    }

    @Override //获取某一间实验室的设备数量 2015-10-22 by 脐橙
    public int getCountByposition(String position) {
        String hql="select count(*) from device t where t.position="+position;
        Long count = deviceDao.count(hql);
        return count.intValue();

    }

    @Override//某一年份，各个状态设备的数量(年份，状态) 2015-10-22 by 脐橙
    public int getYeartoCount(String year,String condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("year", year);
        params.put("condition",condition);
        String hql="select count(*) from device t where t.status= :condition and t.buyYear = :year";
        Long count = deviceDao.count(hql, params);
        return count.intValue();
    }

    @Override//某一间实验室，各个状态设备的数量(实验室，状态) 2015-10-22 by 脐橙
    public int getPositiontoCount(String position, String condition) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("position", position);
        params.put("condition",condition);
        String hql="select count(*) from device t where t.status= :condition and t.position= :position";
        Long count = deviceDao.count(hql, params);
        return count.intValue();
    }

    @Override
    public device creatUUID(device devices) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        devices.setUUID(uuid);
        return devices;

    }

    @Override
    public Boolean deldevice(device devices) {
        deviceDao.delete(devices);
        return true;
    }

    @Override
    public device getdeviceById(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        String hql = "from device t where t.id = :id";
        return deviceDao.get(hql, params);
    }

    @Override
    public device getdeviceByuuid(String uuid) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("UUID", uuid);
        String hql = "from device t where t.UUID = :UUID and t.deleteFlag=0";
        return deviceDao.get(hql, params);
    }


    @Override
    public Boolean update(device devices) {
        deviceDao.update(devices);
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
    public List<device> getAlldevice(String page, String rows, int userId, Filters filtersClass) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from device t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " and t.userId= :userId order by t.id desc";
        // System.out.print("deviimpl0000"+hql);
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<device> managers = deviceDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return managers;
    }

    @Override
    public List<device> getAlldevice(String page, String rows, Filters filtersClass) {

        Map<String, Object> params = new HashMap<String, Object>();
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("from device t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }

        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
        hql = hql + " order by t.id desc";
        // System.out.print("deviimpl0000"+hql);
//        List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        List<device> managers = deviceDao.find(hql, params, Integer.parseInt(page), Integer.parseInt(rows));
        return managers;
    }


    @Override //获取设备某一状态数量 0-使用中，1-废弃，2-是维修，3-升级，4-闲置
    public int getStatusCount(String condition) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = deviceDao.count("select count(*) from device t where t.status = :condition", params);
        return count.intValue();
    }


    @Override
    public Long getCount(Filters filtersClass) {
        Map<String, Object> params = new HashMap<String, Object>();

        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from device t where ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

        //List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = deviceDao.count(hql, params);
        return count;
    }


    @Override
    public Long getCount(Filters filtersClass, int userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        String hql = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from device t where t.userId= :userId and ");
        //拼接HQL
        for (FilterRule filterRule : filtersClass.getRules()) {

            //判断rules 中的操作 op
            stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

        }
        hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);

        //List<Tmanager> managers = managerDao.find(hql ,Integer.parseInt(page), Integer.parseInt(rows));
        Long count = deviceDao.count(hql, params);
        return count;
    }

    //    @Override
//    public List<device> getAlldeviceByPlant(String page, String rows) {
//        String hql = "from device_info t where t.plantingproject != ''order by t.id desc";
//        List<device> devices = deviceDao.find(hql, Integer.parseInt(page), Integer.parseInt(rows));
//        return devices;
//    }

    /*@Override
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
    }*/

    /* @Override
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
 */


    /*@Override
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
    }*/

    /*@Override
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
    }*/

  /*  @Override
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
