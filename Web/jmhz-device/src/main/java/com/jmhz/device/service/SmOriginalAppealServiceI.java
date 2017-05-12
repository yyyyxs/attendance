package com.jmhz.device.service;

import com.jmhz.device.model.Tsmoriginalappeal;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

/**
 * Created by 锋情 on 2014/4/18.
 */
public interface SmOriginalAppealServiceI {


    List<Tsmoriginalappeal> getAllAppeal(int page, int rows);

    Long getCount();

    List<Tsmoriginalappeal> getAllAppeal(int page, int rows, Filters filtersClass);

    Long getCount(Filters filtersClass);

    void delAppeal(Tsmoriginalappeal tsmoriginalappeal);

    Tsmoriginalappeal getAppealById(String id);

    void update(Tsmoriginalappeal tsmoriginalappeal);

    Boolean add(Tsmoriginalappeal tsmoriginalappeal);
}
