package com.jmhz.device.sys.service;

import com.jmhz.device.sys.entity.ExportData;

import java.util.List;

public interface IExportDataService {

    public List<ExportData> findAllByType(String type);

    public boolean delete(int id);

}
