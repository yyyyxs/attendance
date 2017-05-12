package com.jmhz.device.service;

import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

/**
 * Created by 锋情 on 2014/4/18.
 */

public interface FarmerServiceI {


    int add(Tfarmer tfarmer);

    List<Tfarmer> getAllFarmer(String page, String rows);

    Long getCount();

    Boolean delFarmer(Tfarmer tfarmer);

    Tfarmer getFarmerById(int id);

    Boolean update(Tfarmer tfarmer);

    Tfarmer getFarmerByName(String name);

    List<Tfarmer> getAllFarmer(String page, String rows, Filters filtersClass);

    List<Tfarmer> getAllFarmerByPlant(String page, String rows);

    Long getCountByPlant();

    List<Tfarmer> getAllFarmerByPlant(String page, String rows, Filters filtersClass);

    Long getCountByPlant(Filters filtersClass);

    int getPlantCount();

    int getFarmingCount();

    int getSnackCount();

    int getWorkCount();

    int getFoundedCount();

    Long getCount(Filters filtersClass);

    List<Tfarmer> querySpecialFamily(String page, String rows, String query);
    List<Tfarmer> queryHousingSituation(String page, String rows, String query);

    Long getSpecialFamilyCount(String query);
    Long getHousingSituationCount(String query);

    List<Tfarmer> querySpecialFamily(String page, String rows, String query, Filters filtersClass);
    List<Tfarmer> queryHousingSituation(String page, String rows, String query, Filters filtersClass);

    Long getSpecialFamilyCount(String query, Filters filtersClass);
    Long getHousingSituationCount(String query, Filters filtersClass);


    Tfarmer getFarmer(String householdername, String contactnumber);

    Tfarmer getFarmer(String name);

    void updateGridCharger(Tfarmer tfarmer);

    Tfarmer getFarmerMassesById(int appealid);

    Tfarmer getFarmerByNameAndTel(String name, String telephone);

    List<Tfarmer> getFarmerByTownName(String townName);

    List<Tfarmer> getFarmerByVillageName(String villageName);
}
