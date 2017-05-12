package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 锋情 on 2014/4/19.
 */
@Entity
@Table(name = "tgroup")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tgroup implements Serializable {
    private int id;
    private String city;
    private String town;
    private String groupname;//集体名称
    private String groupchargername;//集体负责人
    private String groupchargertel;//集体负责人联系号码

    public Tgroup() {
    }

    public Tgroup(int id, String city, String town, String groupname, String groupchargername, String groupchargertel) {
        this.id = id;
        this.city = city;
        this.town = town;
        this.groupname = groupname;
        this.groupchargername = groupchargername;
        this.groupchargertel = groupchargertel;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupchargername() {
        return groupchargername;
    }

    public void setGroupchargername(String groupchargername) {
        this.groupchargername = groupchargername;
    }

    public String getGroupchargertel() {
        return groupchargertel;
    }

    public void setGroupchargertel(String groupchargertel) {
        this.groupchargertel = groupchargertel;
    }

    @Override
    public String toString() {
        return "Tgroup{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", groupname='" + groupname + '\'' +
                ", groupchargername='" + groupchargername + '\'' +
                ", groupchargertel='" + groupchargertel + '\'' +
                '}';
    }
}
