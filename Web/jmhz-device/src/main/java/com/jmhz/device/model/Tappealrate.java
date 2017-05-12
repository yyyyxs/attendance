package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tappealrate")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tappealrate {

    private int id;
    private int tappealid;// 诉求id
    private int ratelevel;// 评价等级
    private String ratecontent;// 评价内容
    private Date ratedate;// 评价内容


    public Tappealrate() {
    }

    public Tappealrate(int id, int tappealid, int ratelevel, String ratecontent, Date ratedate) {
        this.id = id;
        this.tappealid = tappealid;
        this.ratelevel = ratelevel;
        this.ratecontent = ratecontent;
        this.ratedate = ratedate;
    }

    public Tappealrate(int tappealid, int ratelevel, String ratecontent, Date ratedate) {
        this.tappealid = tappealid;
        this.ratelevel = ratelevel;
        this.ratecontent = ratecontent;
        this.ratedate = ratedate;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "ratedate")
    public Date getRatedate() {
        return ratedate;
    }

    public void setRatedate(Date ratedate) {
        this.ratedate = ratedate;
    }

    public int getTappealid() {
        return tappealid;
    }

    public void setTappealid(int tappealid) {
        this.tappealid = tappealid;
    }

    public int getRatelevel() {
        return ratelevel;
    }

    public void setRatelevel(int ratelevel) {
        this.ratelevel = ratelevel;
    }

    public String getRatecontent() {
        return ratecontent;
    }

    public void setRatecontent(String ratecontent) {
        this.ratecontent = ratecontent;
    }

    @Override
    public String toString() {
        return "Tappealrate{" +
                "id=" + id +
                ", tappealid=" + tappealid +
                ", ratelevel=" + ratelevel +
                ", ratecontent='" + ratecontent + '\'' +
                ", ratedate=" + ratedate +
                '}';
    }
}
