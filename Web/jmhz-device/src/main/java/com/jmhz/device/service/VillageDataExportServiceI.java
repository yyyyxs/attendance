package com.jmhz.device.service;

import com.jmhz.device.pageModel.Filters;

/**
 * Created by ChenXin on 2014/6/16.
 */
public interface VillageDataExportServiceI {

    public void exportExcel2007(final String contextRootPath);

    void exportExcel2007(String contextRootPath, Filters filtersClass);
}
