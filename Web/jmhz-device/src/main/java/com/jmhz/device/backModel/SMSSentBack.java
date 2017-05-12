package com.jmhz.device.backModel;

import com.jmhz.device.model.Tsmssent;

import java.util.List;

public class SMSSentBack extends BaseBack {

    List<Tsmssent> tsmssentList;
    private int total;//总条数
    private int curRows;//每页记录数
    private int curPage;//当前页数

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

    public List<Tsmssent> getTsmssentList() {
        return tsmssentList;
    }

    public void setTsmssentList(List<Tsmssent> tsmssentList) {
        this.tsmssentList = tsmssentList;
    }
}
