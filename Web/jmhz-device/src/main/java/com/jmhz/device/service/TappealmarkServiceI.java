package com.jmhz.device.service;

import com.jmhz.device.model.Tappealmark;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

/**
 * Create by 陈鑫 on 2014/4/22.
 */
public interface TappealmarkServiceI {
    int add(Tappealmark tappealmark);
    List<Tappealmark> getAllAppealmark(int page, int rows);
    List<Tappealmark> getAllAppealmark(int  appealid, int page, int rows);
    List<Tappealmark> getAllAppealmark(int appealid);
    List<Tappealmark> getAllAppealmark(int page, int rows,Filters filtersClass);
    Long getCount();
    Long getCount(int id);
    Boolean delAppealmark(Tappealmark tappealmark);
    Boolean update(Tappealmark tappealmark);
    void updateRemark(int id, String remark, String newDate);
    Tappealmark getTappealmarkByHouseholdername(String name);//诉求方 查询诉求信息
    Tappealmark getAppealmarkByappealid(int appealid);//诉求状态跟踪 显示调用方法（炳阳用的接口）

    List<Tappealmark> getAppealmarkByUuid(String uuid);

    List<Tappealmark> getAppealmarkByUuid(String uuid, int page, int rows);

    Long getCountByUuid(String uuid);

    Long getCount(Filters filtersClass);

    List<Tappealmark> getAllAppealmark(int page, int rows, Filters filtersClass, String uuid);

    Long getCount(Filters filtersClass, String uuid);

    void delAppealmarkByUuid(String uuid);
}
