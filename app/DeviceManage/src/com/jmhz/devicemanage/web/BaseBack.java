package com.jmhz.devicemanage.web;

/**
 * Created by 锋情 on 2015-04-10.
 */
public class BaseBack {

    public Boolean success = true;// 默认成功
    public int errcode = ErrorCodeUtils.EC_UNKNOWN;// 未知错误

    public BaseBack() {
    }

    public BaseBack(Boolean success, int errcode) {
        this.success = success;
        this.errcode = errcode;
    }

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }
}
