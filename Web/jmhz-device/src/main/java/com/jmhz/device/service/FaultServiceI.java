package com.jmhz.device.service;

import com.jmhz.device.model.device;
import com.jmhz.device.model.FaultApply;
import com.jmhz.device.model.Faultrepair;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

/**
 * Created by han on 2015/10/18.
 */

public interface FaultServiceI {


    int add(FaultApply applys);

    int addlog(Faultrepair logs);

    List<FaultApply> getAllapply(String page, String rows, int userId);

    List<FaultApply> getAllapply(String page, String rows);

    List<FaultApply> getAllapplyByapprove(String page, String rows, String approve);

    List<FaultApply> getAllapplyByapprove(String page, String rows, int userId, String approve);

    Long getCountByapprove(int userId, String approve);

    Long getCountByapprove(String approve);

    Long getCount();

    Long getCount(int userId);

    Boolean delfaultapply(FaultApply applys);

    FaultApply getapplyById(int id);

    Faultrepair getlogByapplyID(int applyId);

    Boolean update(FaultApply applys);

    FaultApply getapplyByName(String name);

    List<FaultApply> getAllapply(String page, String rows, Filters filtersClass);

    List<FaultApply> getAllapply(String page, String rows, Filters filtersClass, int userId);

    Long getCount(Filters filtersClass);

    Long getCount(Filters filtersClass, int userId);


    int getUsingCount(String condition);

    int getDiscardCount(String condition);

    int getUnuseCount(String condition);

    int getPublicCount(String condition);

    int getPrivateCount(String condition);
    Long getRepairCount();
    int  getRepairCount(String condition);

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

//
//    List<Tfarmer> querySpecialFamily(String page, String rows, String query);
//    List<Tfarmer> queryHousingSituation(String page, String rows, String query);
//
//    Long getSpecialFamilyCount(String query);
//    Long getHousingSituationCount(String query);
//
//    List<Tfarmer> querySpecialFamily(String page, String rows, String query, Filters filtersClass);
//    List<Tfarmer> queryHousingSituation(String page, String rows, String query, Filters filtersClass);
//
//    Long getSpecialFamilyCount(String query, Filters filtersClass);
//    Long getHousingSituationCount(String query, Filters filtersClass);

    /*device getdevice(String householdername, String contactnumber);

    Tfarmer getFarmer(String name);

    void updateGridCharger(Tfarmer tfarmer);

    Tfarmer getFarmerMassesById(int appealid);

    Tfarmer getFarmerByNameAndTel(String name, String telephone);

    List<Tfarmer> getFarmerByTownName(String townName);

    List<Tfarmer> getFarmerByVillageName(String villageName);*/
}
