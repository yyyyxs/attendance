package com.jmhz.device.service;

import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.sys.entity.User;

public interface AppealDataExportServiceI {

    public void exportExcel2007(final String contextRootPath, User user);

    void exportExcel2007(String contextRootPath, Filters filtersClass, User user);

    void exportExcel2007(String contextRootPath, String starttime, String endtime, User user);
}
