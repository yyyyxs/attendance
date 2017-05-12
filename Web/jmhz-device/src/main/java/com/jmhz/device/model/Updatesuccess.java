package com.jmhz.device.model;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by fjfzuhqc on 2015/10/12.
 */

@Entity
@Table(name = "updatesuccess_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Updatesuccess {
    private int id;         //updatesuccess_info的id
    private String deviceId;//设备ID号
    private String deviceName;//设备名称
    private String deviceType;//设备型号
    private String dealStatus;//处理状态，0-表未升级，1-表示正在升级，2-表示已经升级
    private String cost;       //此次升级开销
    private String change;    //更换哪些配件
    private String updateResult;//升级详况

    public Updatesuccess() {
    }

    public Updatesuccess(int id, String deviceId, String deviceName, String deviceType, String dealStatus, String cost, String change, String updateResult) {
        this.id = id;
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.dealStatus = dealStatus;
        this.cost = cost;
        this.change = change;
        this.updateResult = updateResult;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getUpdateResult() {
        return updateResult;
    }

    public void setUpdateResult(String updateResult) {
        this.updateResult = updateResult;
    }

    @Override
    public String toString() {
        return "Updatesuccess{" +
                "id=" + id +
                ", deviceId='" + deviceId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", dealStatus='" + dealStatus + '\'' +
                ", cost=" + cost +
                ", change='" + change + '\'' +
                ", updateResult='" + updateResult + '\'' +
                '}';
    }
}
