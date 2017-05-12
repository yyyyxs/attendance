package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> .
 * <p> Created at 2014/7/11 23:44
 *
 * @author Charkey
 * @version 1.0
 */
@Entity
@Table(name = "tsmstemplate")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tsmstemplate {

    private int id;//id
    private String tplname;//模板名称
    private String tplcontent;//模板内容
    private String tplcreator;//模板创建者
    private int isvalid;//是否生效

    public Tsmstemplate() {
    }

    public Tsmstemplate(int id, String tplname, String tplcontent, String tplcreator, int isvalid) {
        this.id = id;
        this.tplname = tplname;
        this.tplcontent = tplcontent;
        this.tplcreator = tplcreator;
        this.isvalid = isvalid;
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

    public String getTplcontent() {
        return tplcontent;
    }

    public void setTplcontent(String tplcontent) {
        this.tplcontent = tplcontent;
    }

    public String getTplcreator() {
        return tplcreator;
    }

    public void setTplcreator(String tplcreator) {
        this.tplcreator = tplcreator;
    }

    public int getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(int isvalid) {
        this.isvalid = isvalid;
    }

    @Override
    public String toString() {
        return "Tsmstemplate{" +
                "id=" + id +
                ", tplname='" + tplname + '\'' +
                ", tplcontent='" + tplcontent + '\'' +
                ", tplcreator='" + tplcreator + '\'' +
                ", isvalid=" + isvalid +
                '}';
    }

}
