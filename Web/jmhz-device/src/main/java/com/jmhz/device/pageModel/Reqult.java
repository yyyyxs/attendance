package com.jmhz.device.pageModel;

/**
 * Reqult
 * <p/>
 * 用户后台向前台返回的JSON对象:结果
 */
public class Reqult implements java.io.Serializable {

    //请求是否成功
    private boolean success = false;
    //返回消息
    private String msg = "";
    //请求返回内容
    private Object dataObj = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDataObj() {
        return dataObj;
    }

    public void setDataObj(Object dataObj) {
        this.dataObj = dataObj;
    }

}
