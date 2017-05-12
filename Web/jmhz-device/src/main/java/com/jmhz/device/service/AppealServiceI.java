package com.jmhz.device.service;

import com.jmhz.device.model.Tappeal;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

/**
 * Created by 锋情 on 2014/4/18.
 */
public interface AppealServiceI {
    int add(Tappeal tappeal);

    List<Tappeal> getAllAppeal(int page, int rows);

    Long getCount();

    Boolean delAppeal(Tappeal tappeal);

    Boolean update(Tappeal tappeal);

    List<Tappeal> getAllAppeal(int page, int rows, Filters filtersClass);

    int getCount(String condition);

    Long getCount(Filters filtersClass);


    List<Tappeal> getMassesAppeal(String page, String rows, String[] queryList);

    Long getMassesAppealCount(String[] queryList);

    Tappeal getAppealById(int id);

    void synchronizationUpdate(Tappeal tappeal);

    int getCountByUuid(String uuid);

    void delAppealByFarmerId(int id);

    List<Tappeal> getAppealId(int id);

    List<Tappeal> getAppealIdByGroup(int id);

    void delAppealByGroupId(int id);

    List<Tappeal> getAppealByDoingstatus(int page, int rows, String doingstatus);

    Long getCountByDoingstatus(String doingstatus);


    List<Tappeal> getAppealByDoingstatus(int page, int rows, String doingstatus, Filters filtersClass);

    Long getCountByDoingstatus(String doingstatus, Filters filtersClass);

    List<Tappeal> getPublicAppeal(int page, int rows);

    List<Tappeal> getPublicAppeal();

    int getPublicAppealCount();
}
