package com.jmhz.devicemanage.web;

import java.io.Serializable;

/**
 * Created by 林炜 on 2014/4/18.
 */

public class Device implements Serializable {

    private int id;//设备ID
    private String uuid;//设备UUID,用于生成二维码
    private int userId;//用户ID绑定设备
    private String deviceName;//设备名字
    private String deviceType;//设备类型
    private String brand;//品牌
    private String price;//购入价格
    private String buyTime;//购买时间
    private String buyYear;//购买年份
    private String position;//放置地点，0表示301，1表是302，2表示303，3表示304,4代表305
    private String deviceUser;//使用者
    private String status;//设备状态,0表示使用者，1表示废弃，2表示维修，3表示升级
    private String cpu;//个人电脑CPU
    private String internalMemory;//个人电脑内存
    private String graphicsCard;//个人电脑显卡
    private String otherInfo;//个人电脑其他信息
    private int deleteFlag;//删除标记
    


    public Device() {
    }


    public Device(int id, String UUID, int userId, String deviceName, String deviceType, String brand, String price, String buyTime, String buyYear, String position, String deviceUser, String status, String CPU, String internalMemory, String graphicsCard, String otherInfo, int deleteFlag) {
        this.id = id;
        this.uuid = UUID;
        this.userId = userId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.brand = brand;
        this.price = price;
        this.buyTime = buyTime;
        this.buyYear = buyYear;
        this.position = position;
        this.deviceUser = deviceUser;
        this.status = status;
        this.cpu = CPU;
        this.internalMemory = internalMemory;
        this.graphicsCard = graphicsCard;
        this.otherInfo = otherInfo;
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "device{" +
                "id=" + id +
                ", UUID='" + uuid + '\'' +
                ", userId=" + userId +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", buyYear='" + buyYear + '\'' +
                ", position='" + position + '\'' +
                ", deviceUser='" + deviceUser + '\'' +
                ", status='" + status + '\'' +
                ", CPU='" + cpu + '\'' +
                ", internalMemory='" + internalMemory + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String UUID) {
        this.uuid = UUID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getBuyYear() {
        return buyYear;
    }

    public void setBuyYear(String buyYear) {
        this.buyYear = buyYear;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDeviceUser() {
        return deviceUser;
    }

    public void setDeviceUser(String deviceUser) {
        this.deviceUser = deviceUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCPU() {
        return cpu;
    }

    public void setCPU(String CPU) {
        this.cpu = CPU;
    }

    public String getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(String internalMemory) {
        this.internalMemory = internalMemory;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
