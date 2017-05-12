package com.jmhz.device.service;

import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.sys.entity.User;

/**
 * Created by ChenXin on 2014/6/16.
 */
public interface FamerDataExportServiceI {

    public void exportExcel2007(final String contextRootPath, User user);

    void exportExcel2007(String contextRootPath, Filters filtersClass, User user);
}
