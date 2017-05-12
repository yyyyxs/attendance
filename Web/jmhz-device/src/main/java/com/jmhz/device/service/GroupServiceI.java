package com.jmhz.device.service;

import com.jmhz.device.model.Tgroup;
import com.jmhz.device.model.Tvillage;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 锋情 on 2014/4/19.
 */
public interface GroupServiceI {
    int add(Tgroup tgroup);
    List<Tgroup> getAllGroup(String page,String rows,ArrayList<FilterRule>rules);
    List<Tgroup> getAllGroup(String page,String rows,Filters filtersClass);
    List<Tgroup> getAllGroup(String page,String rows);
    Long getCount();
    Boolean updateTgroup(Tgroup tgroup);
    Boolean delTgroup(Tgroup tgroup);
    Tgroup getGroupByName(String name);
    Tgroup getGroupById(int id);

//   村情
    int addv(Tvillage tvillage);
    Tvillage getVillage(String villageName);
    List<Tvillage> getAllVillage(String page, String rows);
    List<Tvillage> getAllVillage(String page, String rows, Filters filtersClass);
    Long getCount(Filters filtersClass);
    Long getvillageCount();
    Boolean delVillage(Tvillage  tvillage);
    Tvillage getVillageById(int id);
    Boolean update(Tvillage tvillage);

    Tgroup getGroupMassesById(int appealid);
}
