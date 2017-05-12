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
@Table(name = "teacher_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Teacher_info implements Serializable {

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    private int id;
    private String teacherId;//老师工号
    private String Password;//密码
    private String teachername;//老师姓名
    private String deviceId;//设备UUID

    public Teacher_info() {
    }

    @Override
    public String toString() {
        return "Teacher_info{" +
                "id=" + id +
                ", teacherId='" + teacherId + '\'' +
                ", Password='" + Password + '\'' +
                ", teachername='" + teachername + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
