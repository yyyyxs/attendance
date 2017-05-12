package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * <p> .
 * <p> Created at 2014/7/11 23:44
 *
 * @author Charkey
 * @version 1.0
 */
@Entity
@Table(name = "tsmssent")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tsmssent {

    private int id;//id
    private String tplname;//模板名称
    private String parenttype;//短信类型
    private String childtype;//短信类型
    private int qrytype;//查询类型
    private int ispublic;//是否公开
    private String smsto;//发送给
    private String smscontent;//短信内容
    private Date senddate;//发送日期

    public Tsmssent() {
    }

    public Tsmssent(String tplname, String parenttype, String childtype, int qrytype, String smsto, String smscontent, Date senddate) {
        this.tplname = tplname;
        this.parenttype = parenttype;
        this.childtype = childtype;
        this.smsto = smsto;
        this.smscontent = smscontent;
        this.senddate = senddate;
        this.qrytype = qrytype;
    }

    public Tsmssent(int id, String tplname, String smsto, String smscontent, Date senddate) {
        this.id = id;
        this.tplname = tplname;
        this.smsto = smsto;
        this.smscontent = smscontent;
        this.senddate = senddate;
    }

    public Tsmssent(String tplname, String smsto, String smscontent, Date senddate) {
        this.tplname = tplname;
        this.smsto = smsto;
        this.smscontent = smscontent;
        this.senddate = senddate;
    }

    public Tsmssent(String tplname, String parenttype, String childtype, int qrytype, String smsto, String smscontent, boolean ispublic,  Date senddate) {
        this.tplname = tplname;
        this.parenttype = parenttype;
        this.childtype = childtype;
        this.smsto = smsto;
        this.smscontent = smscontent;
        this.senddate = senddate;
        this.qrytype = qrytype;
        this.ispublic = ispublic ? 1 : 0;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTplname() {
        return tplname;
    }

    public void setTplname(String tplname) {
        this.tplname = tplname;
    }

    public String getParenttype() {
        return parenttype;
    }

    public void setParenttype(String parenttype) {
        this.parenttype = parenttype;
    }

    public String getChildtype() {
        return childtype;
    }

    public void setChildtype(String childtype) {
        this.childtype = childtype;
    }

    public int getQrytype() {
        return qrytype;
    }

    public void setQrytype(int qrytype) {
        this.qrytype = qrytype;
    }

    public int getIspublic() {
        return ispublic;
    }

    public void setIspublic(int ispublic) {
        this.ispublic = ispublic;
    }

    public String getSmsto() {
        return smsto;
    }

    public void setSmsto(String smsto) {
        this.smsto = smsto;
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

    @Column(name = "senddate")
    @Temporal(TemporalType.DATE)
    public Date getSenddate() {
        return senddate;
    }

    public void setSenddate(Date senddate) {
        this.senddate = senddate;
    }

    @Override
    public String toString() {
        return "Tsmssent{" +
                "id=" + id +
                ", tplname='" + tplname + '\'' +
                ", parenttype='" + parenttype + '\'' +
                ", childtype='" + childtype + '\'' +
                ", smsto='" + smsto + '\'' +
                ", smscontent='" + smscontent + '\'' +
                ", senddate=" + senddate +
                '}';
    }
}
