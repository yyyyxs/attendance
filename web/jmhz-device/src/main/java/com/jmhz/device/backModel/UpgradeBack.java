package com.jmhz.device.backModel;

import com.jmhz.device.model.FaultApply;
import com.jmhz.device.model.Faultrepair;
import com.jmhz.device.model.upgradeApply;
import com.jmhz.device.model.upgraderepair;

import java.util.List;

public class UpgradeBack extends BaseBack {

    private int total;//总条数
    private int curRows;//每页记录数
    private int curPage;//当前页数


    private int curPageCount;//当前页总条数


    List<upgradeApply> applys;//返回对象为申请升级记录


    upgraderepair log;

    public UpgradeBack() {
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

    public List<upgradeApply> getApplys() {
        return applys;
    }

    public void setApplys(List<upgradeApply> applys) {
        this.applys = applys;
    }

    public upgraderepair getLog() {
        return log;
    }

    public void setLog(upgraderepair log) {
        this.log = log;
    }

    public int getCurPageCount() {
        return curPageCount;
    }

    public void setCurPageCount(int curPageCount) {
        this.curPageCount = curPageCount;
    }


}
