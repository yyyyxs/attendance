package com.jmhz.devicemanage.web;

/**
 * Created by fjfzuhqc on 2015/10/30.
 */
public class StatisticBack extends BaseBack {
    private int wait;
    private int being;
    private int already;

    public StatisticBack(){}

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getBeing() {
        return being;
    }

    public void setBeing(int being) {
        this.being = being;
    }

    public int getAlready() {
        return already;
    }

    public void setAlready(int already) {
        this.already = already;
    }
}
