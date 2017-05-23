package com.jmhz.device.pageModel;

import java.util.ArrayList;


public class Filters {
    private String groupOp;
    private ArrayList<FilterRule> rules;

    public Filters() {
    }

    public Filters(String groupOp, ArrayList<FilterRule> rules) {
        this.groupOp = groupOp;
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "Filters{" +
                "groupOp='" + groupOp + '\'' +
                ", rules=" + rules +
                '}';
    }

    public String getGroupOp() {
        return groupOp;
    }

    public void setGroupOp(String groupOp) {
        this.groupOp = groupOp;
    }

    public ArrayList<FilterRule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<FilterRule> rules) {
        this.rules = rules;
    }
}
