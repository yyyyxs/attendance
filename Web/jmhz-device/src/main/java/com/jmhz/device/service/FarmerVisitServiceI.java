package com.jmhz.device.service;

import com.jmhz.device.model.Tfarmervisit;
import com.jmhz.device.pageModel.Filters;

import java.util.List;

public interface FarmerVisitServiceI {

    int add(Tfarmervisit tfarmervisit);

    Long getCountById(int id);

    Boolean update(Tfarmervisit tfarmervisit);

    int getCount(String condition);

    Long getCount(Filters filtersClass);

    List<Tfarmervisit> getAllVisitList(int farmerid, int page, int rows);

    List<Tfarmervisit> getAllVisitList(int farmerid);

    void updateFarmerVisit(int id, String visittime, String visitremark);

    void deleteFarmerVisit(int rateId);

}
