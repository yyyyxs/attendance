package com.jmhz.device.backModel;

import com.jmhz.device.model.Tsmhlappeal;

import java.util.List;

/**
 * Created by 锋情 on 2015-04-10.
 */
public class SmhlAppealBack extends BaseBack{

    private int total;//总条数
    private int curRows;//每页记录数
    private int curPage;//当前页数
    List<Tsmhlappeal> appeals;

    public SmhlAppealBack() {
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

    public List<Tsmhlappeal> getAppeals() {
        return appeals;
    }

    public void setAppeals(List<Tsmhlappeal> appeals) {
        this.appeals = appeals;
    }
}
