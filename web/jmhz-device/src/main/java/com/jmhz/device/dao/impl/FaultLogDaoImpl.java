package com.jmhz.device.dao.impl;

import com.jmhz.device.dao.FaultDaoI;
import com.jmhz.device.dao.FaultLogDaoI;
import com.jmhz.device.model.FaultApply;
import com.jmhz.device.model.Faultrepair;
import org.springframework.stereotype.Repository;

@Repository
public class FaultLogDaoImpl extends BaseDaoImpl<Faultrepair> implements FaultLogDaoI {
}
