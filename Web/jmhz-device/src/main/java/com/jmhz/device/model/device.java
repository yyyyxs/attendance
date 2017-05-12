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
@Table(name = "device_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class device implements Serializable {

    private int id;//设备ID
    private String UUID;//设备UUID,用于生成二维码
    private int userId;//用户ID绑定设备
    private String deviceName;//设备名字
    private String deviceType;//设备类型0表示私有，1表示公有
    private String brand;//品牌
    private String price;//购入价格
    private String buyTime;//购买时间
    private String buyYear;//购买年份
    private String position;//放置地点，0表示301，1表是302，2表示303，3表示304,4代表305
    private String deviceUser;//使用者
    private String status;//设备状态,0表示使用中，1表示废弃，2表示维修，3表示升级
    private String CPU;//个人电脑CPU
    private String internalMemory;//个人电脑内存
    private String graphicsCard;//个人电脑显卡
    private String otherInfo;//个人电脑其他信息
    private String serialNumber;// 电脑sn码
    private int deleteFlag;//删除标记
    private String codeUrl;//二维码图片保存路径



    public device() {
    }


    public device(int id, String UUID, int userId, String deviceName, String deviceType, String brand, String price, String buyTime, String buyYear,
                  String position, String deviceUser, String status, String CPU, String internalMemory, String graphicsCard, String otherInfo,
                  String serialNumber, int deleteFlag, String codeUrl) {
        this.id = id;
        this.UUID = UUID;
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
        this.CPU = CPU;
        this.internalMemory = internalMemory;
        this.graphicsCard = graphicsCard;
        this.otherInfo = otherInfo;
        this.serialNumber = serialNumber;
        this.deleteFlag = deleteFlag;
        this.codeUrl = codeUrl;
    }



    @Id
    @GeneratedValue
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
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
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
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }
}
