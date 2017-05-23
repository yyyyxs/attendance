package com.jmhz.device.backModel;


public class Statistics {
    private String label;
    private int data;
    private String color;

    public Statistics(String label, int data, String color) {
        this.label = label;
        this.data = data;
        this.color = color;
    }

    public Statistics() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "StatisticData{" +
                "label='" + label + '\'' +
                ", data=" + data +
                ", color='" + color + '\'' +
                '}';
    }
}
