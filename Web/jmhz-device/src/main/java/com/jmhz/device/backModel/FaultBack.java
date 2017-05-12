package com.jmhz.device.backModel;

import com.jmhz.device.model.FaultApply;
import com.jmhz.device.model.Faultrepair;

import java.util.List;

/**
 * Created by 林炜 on 2015-04-10.
 */
public class FaultBack extends BaseBack {

    private int total;//总条数
    private int curRows;//每页记录数
    private int curPage;//当前页数


    private int curPageCount;//当前页总条数


    List<FaultApply> applys;//返回对象为申请维修记录


    Faultrepair log;

    public FaultBack() {
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

    public List<FaultApply> getApplys() {
        return applys;
    }

    public void setApplys(List<FaultApply> applys) {
        this.applys = applys;
    }

    public Faultrepair getLog() {
        return log;
    }

    public void setLog(Faultrepair log) {
        this.log = log;
    }

    public int getCurPageCount() {
        return curPageCount;
    }

    public void setCurPageCount(int curPageCount) {
        this.curPageCount = curPageCount;
    }


}
