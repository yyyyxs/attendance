package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tsmoriginalappeal")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tsmoriginalappeal {

    private int id;
    private String tel;// 诉求号码
    private String content;// 诉求内容
    private String status;// 状态 1.新增、2.正在处理、3.已回复
    private Date createtime;// 接收时间


    public Tsmoriginalappeal() {
    }

    public Tsmoriginalappeal(String tel, String content, Date createtime) {
        this.tel = tel;
        this.content = content;
        this.createtime = createtime;
    }

    public Tsmoriginalappeal(int id, String tel, String content, String status, Date createtime) {
        this.id = id;
        this.tel = tel;
        this.content = content;
        this.status = status;
        this.createtime = createtime;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "createtime")
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Tsmoriginalappeal{" +
                "id=" + id +
                ", tel='" + tel + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                '}';
    }
}
