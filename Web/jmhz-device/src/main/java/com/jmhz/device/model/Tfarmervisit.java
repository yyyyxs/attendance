package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tfarmervisit")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tfarmervisit {

    private int id;
    private int farmerid;// 农户id
    private String visittime;// 走访时间
    private String visitremark;// 走访备注
    private Date inserttime;//记录添加时间

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFarmerid() {
        return farmerid;
    }

    public void setFarmerid(int farmerid) {
        this.farmerid = farmerid;
    }

    public String getVisittime() {
        return visittime;
    }

    public void setVisittime(String visittime) {
        this.visittime = visittime;
    }

    public String getVisitremark() {
        return visitremark;
    }

    public void setVisitremark(String visitremark) {
        this.visitremark = visitremark;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "inserttime")
    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    @Override
    public String toString() {
        return "Tfarmervisit{" +
                "id=" + id +
                ", farmerid=" + farmerid +
                ", visittime=" + visittime +
                ", visitremark='" + visitremark + '\'' +
                ", inserttime=" + inserttime +
                '}';
    }
}
