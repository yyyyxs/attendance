package com.baidu.yun.push.model;

import com.baidu.yun.core.annotation.JSonPath;

public class KeyValueForDevice {

    @JSonPath(path = "key")
    private String timestamp;

    @JSonPath(path = "value")
    private DeviceStatUnit value = null;

    public String getKey() {
        return timestamp;
    }

    public void setKey(String key) {
        this.timestamp = key;
    }

    public DeviceStatUnit getValue() {
        return value;
    }

    public void setValue(DeviceStatUnit value) {
        this.value = value;
    }
}
