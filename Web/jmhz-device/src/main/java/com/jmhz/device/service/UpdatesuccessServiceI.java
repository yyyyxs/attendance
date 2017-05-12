package com.jmhz.device.service;

import com.jmhz.device.model.Updatesuccess;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

/**
 * Created by 脐橙 on 2015/10/11
 */
public interface UpdatesuccessServiceI {
    int add(Updatesuccess updatesuccess);

    List<Updatesuccess> getAllAppeal(int page, int rows);

    Long getCount();

    Boolean delAppeal(Updatesuccess updatesuccess);

    Boolean update(Updatesuccess updatesuccess);

    List<Updatesuccess> getAllAppeal(int page, int rows, Filters filtersClass);

    int getCount(String condition);

    Long getCount(Filters filtersClass);


    List<Updatesuccess> getMassesAppeal(String page, String rows, String[] queryList);

    Long getMassesAppealCount(String[] queryList);

    Updatesuccess getAppealById(int id);

    void synchronizationUpdate(Updatesuccess updatesuccess);

    int getCountByUuid(String uuid);

    void delAppealByFarmerId(int id);

    List<Updatesuccess> getAppealId(int id);

    List<Updatesuccess> getAppealIdByGroup(int id);

    void delAppealByGroupId(int id);

    List<Updatesuccess> getAppealByDoingstatus(int page, int rows, String doingstatus);

    Long getCountByDoingstatus(String doingstatus);


    List<Updatesuccess> getAppealByDoingstatus(int page, int rows, String doingstatus, Filters filtersClass);

    Long getCountByDoingstatus(String doingstatus, Filters filtersClass);

    List<Updatesuccess> getPublicAppeal(int page, int rows);

    List<Updatesuccess> getPublicAppeal();

    int getPublicAppealCount();
}
