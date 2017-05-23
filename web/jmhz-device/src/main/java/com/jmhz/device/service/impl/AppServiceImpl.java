package com.jmhz.device.service.impl;

import com.jmhz.device.dao.AppDaoI;
import com.jmhz.device.model.Tapp;
import com.jmhz.device.service.AppServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AppServiceImpl implements AppServiceI {
    @Autowired
    private AppDaoI appDao;

    @Override
    public Tapp getStartAndroidAppInfo() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("type", 1);
        params.put("start", 1);
        String hql = "from Tapp t where t.type=:type and t.start=:start";
        Tapp tapp = appDao.get(hql ,params);
        return tapp;
    }
}
