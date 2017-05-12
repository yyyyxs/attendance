package com.jmhz.device.sys.service.impl;

import com.jmhz.device.sys.dao.IExportDataDao;
import com.jmhz.device.sys.entity.ExportData;
import com.jmhz.device.sys.service.IExportDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExportDataServiceImpl implements IExportDataService {

    @Autowired
    private IExportDataDao exportDataDao;

    @Override
    public List<ExportData> findAllByType(String type) {
        String hql = "from ExportData t where t.type=:type order by t.exporttime desc";
        Map<String, Object> params = new HashMap<String, Object>();
        if (type.equals("appeal")) {
            params.put("type", "0");
        } else if (type.equals("farmer")) {
            params.put("type", "1");
        }else if (type.equals("village")) {
            params.put("type", "2");
        }
        return exportDataDao.find(hql, params);
    }

    @Override
    public boolean delete(int id) {
        ExportData exportData = new ExportData();
        exportData.setId(id);
        String hql = "from ExportData t where t.id=:id";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        ExportData current = exportDataDao.get(hql, params);
        String filePath = current.getFilepath();
        String sqlToDelete = "delete from sys_export where id=" + id;
        exportDataDao.executeSql(sqlToDelete);
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                return file.delete();
            } else {
                return false;
            }
        }
    }

}
