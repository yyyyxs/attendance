package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wby on 2014/7/11.
 */
@Entity
@Table(name = "tsmhlappealmark")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tsmhlappealmark implements Serializable {
    private int id;
    private String uuid;
    private String appealname;// 诉求方名
    private String processupdatetime;// 添加备注时间
    private String processupdateremark;// 备注内容
    public Tsmhlappealmark(){
        }

    public Tsmhlappealmark(int id, String uuid, String appealname, String processupdatetime, String processupdateremark) {
        this.id = id;
        this.uuid = uuid;
        this.appealname = appealname;
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

    public String getAppealname() {
        return appealname;
    }

    public void setAppealname(String appealname) {
        this.appealname = appealname;
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
        return "Tsmhlappealmark{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", appealname='" + appealname + '\'' +
                ", processupdatetime='" + processupdatetime + '\'' +
                ", processupdateremark='" + processupdateremark + '\'' +
                '}';
    }
}
