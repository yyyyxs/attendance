package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 锋情 on 2014/4/18.
 */
@Entity
@Table(name = "tapp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tapp {

    private int id;
    private String versioncode;// 版本号
    private String versionname;// 版本名
    private int type;// 类型：1 android/  2 ios
    private int start;//是否启用 1 启用 0 关闭

    public Tapp() {
    }

    public Tapp(int id, String versioncode, String versionname, int type, int start) {
        this.id = id;
        this.versioncode = versioncode;
        this.versionname = versionname;
        this.type = type;
        this.start = start;
    }
    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(String versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Tapp{" +
                "id=" + id +
                ", versioncode='" + versioncode + '\'' +
                ", versionname='" + versionname + '\'' +
                ", type=" + type +
                ", start=" + start +
                '}';
    }
}
