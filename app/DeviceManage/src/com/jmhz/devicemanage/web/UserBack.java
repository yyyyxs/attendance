package com.jmhz.devicemanage.web;


import java.util.List;

/**
 * Created by ¡÷Ïø on 2015-04-10.
 */
public class UserBack extends BaseBack {
    User user;
    String errorReason; //¥ÌŒÛ‘≠“Ú
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
