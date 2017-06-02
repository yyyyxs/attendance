package com.jmhz.devicemanage.web;

/**
 * Created by ��� on 2015/10/11.
 */

public class Faultrepair {

    private int id;            //����ά�ޱ�id
    private int applyId;    //�걨��¼ID
    private String deviceName;  // �����豸����
    private String deviceUser;//�豸ʹ����
    private String deviceType;  //�����豸����
    private String dealStatus;  //����״̬  0-��δά�ޣ�1-��ʾ����ά�ޣ�2-��ʾ��ά��
    private String cost;        //���ϴ�����
    private String repairpart;      // �������
    private String repairResult;// �������

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
