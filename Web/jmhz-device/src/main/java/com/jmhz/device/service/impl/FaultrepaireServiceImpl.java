package com.jmhz.device.service.impl;

/**import com.jmhz.device.dao.FaultrepairDaoI;
import com.jmhz.device.service.FaultrepaireServiceI;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fjfzuhqc on 2015/10/22.
 */
/**
public class FaultrepaireServiceImpl implements FaultrepaireServiceI {

    @Autowired
    private FaultrepairDaoI FaultrepairDao;

    @Override
    public Long getCount() {
        return FaultrepairDao.count("select count(*) from Faultrepair t");
    }

    @Override
    public int getCount(String condition) {
        Map<String, Object> params =new HashMap<String, Object>();
        params.put("condition", condition);
        Long count = FaultrepairDao.count("select count(*) from Faultrepair t where t.dealStatus = :condition",params);
        return count.intValue();
    }
}
*/