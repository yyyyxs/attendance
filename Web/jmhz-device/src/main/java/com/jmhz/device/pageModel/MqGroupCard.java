package com.jmhz.device.pageModel;

/**
 * Created by 锋情 on 2014/4/18.
 */
public class MqGroupCard {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String city;
    private String town;
    private String uuid;//诉求标号
    private String groupname;//集体名称
    private String groupchargername;//集体负责人
    private String groupchargertel;//集体负责人联系号码
    private String hardshipappeal;//诉求内容
    private String appealtype;
    private String affairtype;

    @Override
    public String toString() {
        return "MqGroupCard{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", uuid='" + uuid + '\'' +
                ", groupname='" + groupname + '\'' +
                ", groupchargername='" + groupchargername + '\'' +
                ", groupchargertel='" + groupchargertel + '\'' +
                ", hardshipappeal='" + hardshipappeal + '\'' +
                ", appealtype='" + appealtype + '\'' +
                ", affairtype='" + affairtype + '\'' +
                '}';
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getAffairtype() {
        return affairtype;
    }

    public void setAffairtype(String affairtype) {
        this.affairtype = affairtype;
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

    public String getHardshipappeal() {
        return hardshipappeal;
    }

    public void setHardshipappeal(String hardshipappeal) {
        this.hardshipappeal = hardshipappeal;
    }

    public String getAppealtype() {
        return appealtype;
    }

    public void setAppealtype(String appealtype) {
        this.appealtype = appealtype;
    }
}

