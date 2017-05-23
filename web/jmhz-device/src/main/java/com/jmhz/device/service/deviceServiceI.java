package com.jmhz.device.service;


import com.jmhz.device.model.device;
import com.jmhz.device.pageModel.Filters;

import java.util.List;



public interface deviceServiceI {


    int add(device devices);

    List<device> getAlldevice(String page, String rows, int userId);

    List<device> getAlldevice(String page, String rows);

    List<device> getdeviceByCondition(String condition1, String condition2, String page, String rows);

    Long getCountByCondition(String condition1, String condition2);

    Long getCount();

    Long getCount(int userId);

    int getCountBybuyYear(String buyYear);

    int getCountByposition(String position);

    int getYeartoCount(String year, String condition);

    int getPositiontoCount(String position, String condition);

    device creatUUID(device devices);

    Boolean deldevice(device devices);

    device getdeviceById(int id);

    device getdeviceByuuid(String uuid);

    // device getdeviceBycondition(String )

    Boolean update(device devices);

    device getdeviceByName(String name);

    List<device> getAlldevice(String page, String rows, int userId, Filters filtersClass);

    List<device> getAlldevice(String page, String rows, Filters filtersClass);

    int getStatusCount(String condition);

    Long getCount(Filters filtersClass);

    Long getCount(Filters filtersClass, int userId);

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
