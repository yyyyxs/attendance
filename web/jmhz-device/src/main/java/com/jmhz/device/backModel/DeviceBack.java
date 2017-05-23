package com.jmhz.device.backModel;


import com.jmhz.device.model.device;

import java.util.List;


public class DeviceBack extends BaseBack {

    private int total;//总设备数
    private int curRows;//每页记录数
    private int curPage;//当前页数
    private int curPageCount;//当前页总条数
    List<device> devices;//返回对象为多个设备
    public DeviceBack()
    {

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurRows() {
        return curRows;
    }

    public void setCurRows(int curRows) {
        this.curRows = curRows;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getCurPageCount() {
        return curPageCount;
    }

    public void setCurPageCount(int curPageCount) {
        this.curPageCount = curPageCount;
    }

    public List<device> getDevices() {
        return devices;
    }

    public void setDevices(List<device> devices) {
        this.devices = devices;
    }

}
