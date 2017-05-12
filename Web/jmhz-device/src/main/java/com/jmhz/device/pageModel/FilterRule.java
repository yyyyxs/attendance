package com.jmhz.device.pageModel;

/**
 * Created by 锋情 on 2014/4/20.
 */
public class FilterRule {
    private String field;
    private String op;
    private String data;

    public FilterRule() {
    }

    public FilterRule(String field, String op, String data) {
        this.field = field;
        this.op = op;
        this.data = data;
    }

    @Override
    public String toString() {
        return "FilterRule{" +
                "field='" + field + '\'' +
                ", op='" + op + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
