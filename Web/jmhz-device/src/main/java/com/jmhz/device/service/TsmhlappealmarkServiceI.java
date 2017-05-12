package com.jmhz.device.service;

import com.jmhz.device.model.Tsmhlappealmark;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

/**
 * Created by wby on 2014/7/11.
 */
public interface TsmhlappealmarkServiceI {
    int add(Tsmhlappealmark tsmhlappealmark);
    List<Tsmhlappealmark> getSmhlappealmarkByUuid(String uuid);
    List<Tsmhlappealmark> getSmhlappealmarkByUuid(String uuid, int page, int rows);
    Long getCountByUuid(String uuid);
    List<Tsmhlappealmark> getAllSmhlappealmark(int page, int rows, Filters filtersClass, String uuid);
    Long getCount(Filters filtersClass, String uuid);
    void updateRemark(int id, String remark, String newDate);
    Boolean delAppealmark(Tsmhlappealmark tsmhlappealmark);
}
