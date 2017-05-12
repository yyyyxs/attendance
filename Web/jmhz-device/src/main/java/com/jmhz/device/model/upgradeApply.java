package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by 林炜 on 2014/4/18.
 */
@Entity
@Table(name = "upgradeapply_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class upgradeApply implements Serializable {

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDeviceId(int deviceId) {
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

    public String getDeviceUser() {
        return deviceUser;
    }

    public void setDeviceUser(String deviceUser) {
        this.deviceUser = deviceUser;
    }

    public String getApplytime() {
        return applytime;
    }

    public void setApplytime(String applytime) {
        this.applytime = applytime;
    }

    public String getUpgradeDescribe() {
        return upgradeDescribe;
    }

    public void setUpgradeDescribe(String upgradeDescribe) {
        this.upgradeDescribe = upgradeDescribe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getApproveRemark() {
        return approveRemark;
    }

    public void setApproveRemark(String approveRemark) {
        this.approveRemark = approveRemark;
    }

    public int getLogmark() {
        return logmark;
    }

    public void setLogmark(int logmark) {
        this.logmark = logmark;
    }


    private int id;//ID
    private int deviceId;//设备ID
    private int userId;//用户ID
    private String deviceName;//设备名字
    private String deviceType;//设备类型
    private String deviceUser;//使用者
    private String applytime;//申报时间
    private String upgradeDescribe;//升级描述
    private String status;//设备状态
    private String approve;//审核进度，0是待审核，1是同意审核，2是拒绝审核，3是已升级
    private String approveRemark;//审核备注
    private int logmark;//是否有报告标志


    public upgradeApply() {
    }

    public upgradeApply(int id, int deviceId,int userId, String deviceName, String deviceType, String deviceUser, String applytime, String upgradeDescribe, String status, String approve, String approveRemark, int logmark) {
        this.id = id;
        this.deviceId = deviceId;
        this.userId=userId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.deviceUser = deviceUser;
        this.applytime = applytime;
        this.upgradeDescribe = upgradeDescribe;
        this.status = status;
        this.approve = approve;
        this.approveRemark = approveRemark;
        this.logmark = logmark;

    }


    @Override
    public String toString() {
        return "device{" +
                ", id='" + id + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", userId='" + userId + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceUser='" + deviceUser + '\'' +
                ", applytime='" + applytime + '\'' +
                ", upgradeDescribe='" + upgradeDescribe + '\'' +
                ", status='" + status + '\'' +
                ", approve='" + approve + '\'' +
                ", approveRemark='" + approveRemark + '\'' +
                ", logmark='" + logmark + '\'' +
                '}';
    }
}
