package com.jmhz.device.service;

import com.jmhz.device.pageModel.Filters;


public interface VillageDataExportServiceI {

    public void exportExcel2007(final String contextRootPath);

    void exportExcel2007(String contextRootPath, Filters filtersClass);
}
