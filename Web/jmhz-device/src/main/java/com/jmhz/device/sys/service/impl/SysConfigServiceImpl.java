package com.jmhz.device.sys.service.impl;

import com.jmhz.device.sys.service.ISysConfigService;
import com.jmhz.device.sys.dao.ISysConfigDao;
import com.jmhz.device.sys.entity.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysConfigServiceImpl implements ISysConfigService {

    @Autowired
    private ISysConfigDao sysConfigDao;

    @Override
    public List<String> getConfigValuesByType(int configType) {
        String findHQL = "from SysConfig s where s.type = :configType";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("configType", configType);
        List<SysConfig> sysConfigList = sysConfigDao.find(findHQL, paramMap);
        List<String> sysConfigValues = new ArrayList<String>();
        for (SysConfig sysConfig : sysConfigList) {
            sysConfigValues.add(sysConfig.getValue());
        }
        return sysConfigValues;
    }

}
