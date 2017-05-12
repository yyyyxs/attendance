package com.jmhz.device.service;

import com.jmhz.device.model.Tsmhlappeal;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

/**
 * Created by 锋情 on 2014/4/18.
 */
public interface SmhlAppealServiceI {

    int add(Tsmhlappeal tsmhlappeal);

    List<Tsmhlappeal> getAllAppeal(int page, int rows, int source);

    Long getCount(int source);

    List<Tsmhlappeal> getAllAppeal(int page, int rows, Filters filtersClass, int source_session);

    Long getCount(Filters filtersClass, int source_session);

    Tsmhlappeal getAppealById(int id);

    void update(Tsmhlappeal tsmhlappeal);

    void delAppeal(Tsmhlappeal tsmhlappeal);

    void synchronizationUpdate(Tsmhlappeal tsmhlappeal);

    List<Tsmhlappeal> getMassesSmhlAppeal(String s, String s1, String[] queryList, int source);

    Long getMassesSmhlAppealCount(String[] queryList, int source);
}
