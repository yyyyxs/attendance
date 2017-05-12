package com.jmhz.device.service;

import com.jmhz.device.model.Tappealrate;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

public interface AppealRateServiceI {

    int add(Tappealrate tappealrate);

    Long getCountById(int id);

    Boolean update(Tappealrate tappealrate);

    int getCount(String condition);

    Long getCount(Filters filtersClass);

    List<Tappealrate> getAllAppealRate(int appealid, int page, int rows);

    List<Tappealrate> getAllAppealRate(int appealid);

    void updateAppealRate(int appealId, int rateLevel, String rateContent, String rateDate);

    void deleteAppealRate(int rateId);

}
