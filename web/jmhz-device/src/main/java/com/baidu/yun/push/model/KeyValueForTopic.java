package com.baidu.yun.push.model;

import com.baidu.yun.core.annotation.JSonPath;

public class KeyValueForTopic {

    @JSonPath(path = "key")
    private String timestamp;

    @JSonPath(path = "value")
    private TopicStatUnit value = null;

    // get
    public String getKey() {
        return timestamp;
    }

    public void setKey(String key) {
        this.timestamp = key;
    }

    public TopicStatUnit getValue() {
        return value;
    }

    public void setValue(TopicStatUnit value) {
        this.value = value;
    }
}
