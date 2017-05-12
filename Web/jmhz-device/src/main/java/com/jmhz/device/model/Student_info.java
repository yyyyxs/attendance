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
@Table(name = "Student_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Student_info implements Serializable {
    private int id;
    private String studentId;//学生学号
    private String password;//密码
    private String Grade;//年级
    private int teacherId;//导师ID
    private String deviceId;//设备UUID

    public Student_info() {
    }

    @Id
    @GeneratedValue

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String grade) {
        Grade = grade;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "Student_info{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", password='" + password + '\'' +
                ", Grade='" + Grade + '\'' +
                ", teacherId=" + teacherId +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
