package com.jmhz.device.service;

import com.jmhz.device.backModel.UpgradeAddBack;
import com.jmhz.device.model.FaultApply;
import com.jmhz.device.model.device;
import com.jmhz.device.model.upgradeApply;
import com.jmhz.device.model.upgraderepair;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

/**
 * Created by 韩嘉民 on 2015/10/18.
 */

public interface upgradeServiceI {


    int add(device devices);

    int addlog(upgraderepair logs);

    public int addupgrade(upgradeApply apply);

    Long getCount(Filters filtersClass, int userId);

    List<upgradeApply> getAllapply(String page, String rows);

    List<upgradeApply> getAllapply(String page, String rows, int userId);

    List<upgradeApply> getAllapply(String page, String rows, Filters filtersClass, int userId);

    Long getCount(int userId);

    List<upgradeApply> getAllapply(String page, String rows, Filters filtersClass);

    List<upgradeApply> getAllapplyByapprove(String page, String rows, String approve);

    List<upgradeApply> getAllapplyByapprove(String page, String rows, int userId, String approve);

    Long getCountByapprove(int userId, String approve);

    Long getCountByapprove(String approve);

    Long getCount();
    int getCount(String condition);

    Boolean deldevice(device devices);


    upgraderepair getlogByapplyID(int applyId);

    upgradeApply getapplyById(int id);

    Boolean update(upgradeApply applys);

    device getdeviceByName(String name);

    List<device> getAlldevice(String page, String rows, Filters filtersClass);

    int getUsingCount(String condition);

    int getDiscardCount(String condition);

    int getUnuseCount(String condition);

    int getPublicCount(String condition);

    int getPrivateCount(String condition);

    Long getCount(Filters filtersClass);

/*
    List<device> getAlldeviceByPlant(String page, String rows);

    Long getCountByPlant();
*/

//    List<device> getAlldevicerByPlant(String page, String rows, Filters filtersClass);

   /* Long getCountByPlant(Filters filtersClass);

    int getPlantCount();

    int getFarmingCount();

    int getSnackCount();

    int getWorkCount();

    int getFoundedCount();
*/
   /* Long getCount(Filters filtersClass);

    List<Tfarmer> querySpecialFamily(String page, String rows, String query);
    List<Tfarmer> queryHousingSituation(String page, String rows, String query);

    Long getSpecialFamilyCount(String query);
    Long getHousingSituationCount(String query);

    List<Tfarmer> querySpecialFamily(String page, String rows, String query, Filters filtersClass);
    List<Tfarmer> queryHousingSituation(String page, String rows, String query, Filters filtersClass);

    Long getSpecialFamilyCount(String query, Filters filtersClass);
    Long getHousingSituationCount(String query, Filters filtersClass);
*/

    /*device getdevice(String householdername, String contactnumber);

    Tfarmer getFarmer(String name);

    void updateGridCharger(Tfarmer tfarmer);

    Tfarmer getFarmerMassesById(int appealid);

    Tfarmer getFarmerByNameAndTel(String name, String telephone);

    List<Tfarmer> getFarmerByTownName(String townName);

    List<Tfarmer> getFarmerByVillageName(String villageName);*/
}
