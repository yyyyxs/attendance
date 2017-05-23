package com.jmhz.device.backModel;

import java.util.List;


public class StatisticsBack extends BaseBack {
    private int maintain;//维修数据
    private int update;//升级数据
    private int using;//使用中数据
    private int discard;//废弃数据
    private int unused;//未使用数据
    public StatisticsBack(){}

    public int getMaintain() {
        return maintain;
    }

    public void setMaintain(int maintain) {
        this.maintain = maintain;
    }

    public int getUpdate() {
        return update;
    }

    public void setUpdate(int update) {
        this.update = update;
    }

    public int getUsing() {
        return using;
    }

    public void setUsing(int using) {
        this.using = using;
    }

    public int getDiscard() {
        return discard;
    }

    public void setDiscard(int discard) {
        this.discard = discard;
    }

    public int getUnused() {
        return unused;
    }

    public void setUnused(int unused) {
        this.unused = unused;
    }
}
