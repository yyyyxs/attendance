package com.jmhz.device.service;

import com.jmhz.device.model.Tsmssent;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

public interface SMSSentServiceI {

    List<Tsmssent> getAllSMSSent(int page, int rows, int qryType);

    Long getCount(int qryType);

    List<Tsmssent> getAllSMSSent(int page, int rows, Filters filtersClass);

    Long getCount(Filters filtersClass);

    void delSMSSent(Tsmssent tsmssent);

    int add(Tsmssent tsmssent);

    boolean addJDBC(String rar, String tel, String s);

    List<Tsmssent> getSMSSent(int page, int rows, int qrytype);
    Long getSMSSentCount(int qrytype);

    List<Tsmssent> getPublicSMSSent(int page, int rows);

    Long getPublicSMSSentCount();

    List<Tsmssent> getPublicDWForIndex(int page, int rows);

    List<Tsmssent> getPublicZWForIndex(int page, int rows);


}
