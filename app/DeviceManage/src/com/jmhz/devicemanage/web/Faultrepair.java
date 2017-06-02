package com.jmhz.devicemanage.web;

/**
 * Created by 脐橙 on 2015/10/11.
 */

public class Faultrepair {

    private int id;            //故障维修表id
    private int applyId;    //申报记录ID
    private String deviceName;  // 故障设备名称
    private String deviceUser;//设备使用者
    private String deviceType;  //故障设备类型
    private String dealStatus;  //处理状态  0-表未维修，1-表示正在维修，2-表示已维修
    private String cost;        //故障处理开销
    private String repairpart;      // 更换配件
    private String repairResult;// 处理情况

    public Faultrepair() {
    }


    public Faultrepair(int id, int applyId, String deviceName, String deviceUser,
                       String deviceType, String dealStatus, String cost,
                       String repairpart, String repairResult) {
        this.id = id;
        this.applyId = applyId;
        this.deviceName = deviceName;
        this.deviceUser = deviceUser;
        this.deviceType = deviceType;
        this.dealStatus = dealStatus;
        this.cost = cost;
        this.repairpart = repairpart;
        this.repairResult = repairResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceUser() {
        return deviceUser;
    }

    public void setDeviceUser(String deviceUser) {
        this.deviceUser = deviceUser;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getRepairpart() {
        return repairpart;
    }

    public void setRepairpart(String repairpart) {
        this.repairpart = repairpart;
    }


    public String getRepairResult() {
        return repairResult;
    }

    public void setRepairResult(String repairResult) {
        this.repairResult = repairResult;
    }

    @Override
    public String toString() {
        return "Faultrepair{" +
                "id=" + id +
                ", applyId='" + applyId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceUser='" + deviceUser + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", dealStatus='" + dealStatus + '\'' +
                ", cost='" + cost + '\'' +
                ", repairpart='" + repairpart + '\'' +
                ", repairResult='" + repairResult + '\'' +
                '}';
    }
}
