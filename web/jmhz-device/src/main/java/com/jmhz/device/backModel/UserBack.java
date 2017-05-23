package com.jmhz.device.backModel;


import com.jmhz.device.sys.entity.User;

import java.util.List;


public class UserBack extends BaseBack {
    User user;
    String errorReason; //错误原因
    public UserBack() {
    }

    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
