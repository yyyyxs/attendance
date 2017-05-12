package com.jmhz.device.sys.service;

import com.jmhz.device.sys.entity.LoginLog;

import java.util.List;

public interface ILoginLogService {

    public List<LoginLog> getAllLoginLog(int page, int rows, String _search, String filters);

    public String loginLog(LoginLog loginLog);

    public Long countAll();
}
