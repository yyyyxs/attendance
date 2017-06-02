package com.jmhz.devicemanage.web;

import java.util.List;

/**
 * Created by fjfzuhqc on 2015/10/29.
 */
public class StatisticsBack extends BaseBack {
    private int maintain;
    private int update;
    private int using;
    private int discard;
    private int unused;
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
