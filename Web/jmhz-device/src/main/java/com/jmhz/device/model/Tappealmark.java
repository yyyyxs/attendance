package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Create by 陈鑫 on 2014/4/22.
 */
@Entity
@Table(name = "tappealmark")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tappealmark implements Serializable {
    private int id;//备注状态id
    private String uuid;
    private int appealid;// appeal id
    private String householdername;// 户主name new
    private String processupdatetime;// 添加备注时间
    private String processupdateremark;// 备注内容
    public Tappealmark() {
    }

    public Tappealmark(int id,String uuid, int appealid, String householdername, String processupdatetime, String processupdateremark) {
        this.id = id;
        this.uuid=uuid;
        this.appealid = appealid;
        this.householdername = householdername;
        this.processupdatetime = processupdatetime;
        this.processupdateremark = processupdateremark;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getAppealid() {
        return appealid;
    }

    public void setAppealid(int appealid) {
        this.appealid = appealid;
    }

    public String getHouseholdername() {
        return householdername;
    }

    public void setHouseholdername(String householdername) {
        this.householdername = householdername;
    }

    public String getProcessupdatetime() {
        return processupdatetime;
    }

    public void setProcessupdatetime(String processupdatetime) {
        this.processupdatetime = processupdatetime;
    }

    public String getProcessupdateremark() {
        return processupdateremark;
    }

    public void setProcessupdateremark(String processupdateremark) {
        this.processupdateremark = processupdateremark;
    }

    @Override
    public String toString() {
        return "Tappealmark{" +
                "id=" + id +
                ", appealid=" + appealid +
                ", householdername='" + householdername + '\'' +
                ", processupdatetime='" + processupdatetime + '\'' +
                ", processupdateremark='" + processupdateremark + '\'' +
                '}';
    }
}
