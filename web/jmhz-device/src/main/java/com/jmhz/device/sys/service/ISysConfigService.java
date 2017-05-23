package com.jmhz.device.sys.service;

import com.jmhz.device.Constants;

import java.util.List;

public interface ISysConfigService {

    /**
     * 根据配置类型查找配置值
     * @see Constants.SYS_CONFIG_TYPE
     * @param configType 配置类型
     * @return 字符串数组的配置值
     */
    public List<String> getConfigValuesByType(int configType);
}
