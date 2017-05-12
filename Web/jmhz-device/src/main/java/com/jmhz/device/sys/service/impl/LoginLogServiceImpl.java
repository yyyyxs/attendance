package com.jmhz.device.sys.service.impl;

import com.jmhz.device.sys.entity.LoginLog;
import com.jmhz.device.sys.service.ILoginLogService;
import com.jmhz.device.sys.dao.ILoginLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginLogServiceImpl implements ILoginLogService {

    @Autowired
    private ILoginLogDao loginLogDao;

    @Override
    public List<LoginLog> getAllLoginLog(int page, int rows, String _search, String filters) {
        String hql = "from LoginLog t order by t.id desc";
        return loginLogDao.find(hql, page, rows);
    }

    @Override
    public String loginLog(LoginLog loginLog) {
        return loginLogDao.save(loginLog).toString();
    }

    @Override
    public Long countAll() {
        return loginLogDao.count("select count(*) from LoginLog");
    }

}
