package com.jmhz.devicemanage.web;

import java.util.List;

/**
 * Created by ��� on 2015-04-10.
 */
public class FaultBack extends BaseBack {

    private int total;//������
    private int curRows;//ÿҳ��¼��
    private int curPage;//��ǰҳ��
    private FaultApply faultApply;

    private int curPageCount;//��ǰҳ������


    List<FaultApply> applys;//���ض���Ϊ����ά�޼�¼


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


	public FaultApply getFaultApply() {
		return faultApply;
	}


	public void setFaultApply(FaultApply faultApply) {
		this.faultApply = faultApply;
	}


}
