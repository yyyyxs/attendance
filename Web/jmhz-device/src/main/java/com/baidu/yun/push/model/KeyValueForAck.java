package com.baidu.yun.push.model;

import com.baidu.yun.core.annotation.JSonPath;

public class KeyValueForAck {

    @JSonPath(path = "key")
    private String timestamp;

    @JSonPath(path = "value")
    private int value;

    public String getKey() {
        return timestamp;
    }

    public void setKey(String key) {
        this.timestamp = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
