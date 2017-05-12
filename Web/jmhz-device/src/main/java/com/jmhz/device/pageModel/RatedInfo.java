package com.jmhz.device.pageModel;

import java.util.Date;

/**
 * <p> .
 * <p> Created at 2014/5/21 18:52
 *
 * @author Charkey
 * @version 1.0
 */
public class RatedInfo {

    private int appealid;//诉求id，诉求表中id
    private String uuid;//诉求uuid
    private String appealname;//诉求方
    private String appealvillage;//诉求方所属村
    private String dutyleader;// 责任领导
    private String dutydept;// 责任部门
    private String dutymenber;// 责任人
    private String status;//诉求状态

    private int id;//评价id，评价表中id
    private int ratelevel;//评价等级
    private String ratecontent;// 评价内容
    private Date ratedate;// 评价日期

    public RatedInfo() {
    }

    public RatedInfo(int appealid, String uuid, String appealname, String appealvillage, String dutyleader, String dutydept, String dutymenber,
                     String status, int id, int ratelevel, String ratecontent, Date ratedate) {
        this.appealid = appealid;
        this.uuid = uuid;
        this.appealname = appealname;
        this.appealvillage = appealvillage;
        this.dutyleader = dutyleader;
        this.dutydept = dutydept;
        this.dutymenber = dutymenber;
        this.status = status;
        this.id = id;
        this.ratelevel = ratelevel;
        this.ratecontent = ratecontent;
        this.ratedate = ratedate;
    }

    public int getAppealid() {
        return appealid;
    }

    public void setAppealid(int appealid) {
        this.appealid = appealid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAppealname() {
        return appealname;
    }

    public void setAppealname(String appealname) {
        this.appealname = appealname;
    }

    public String getAppealvillage() {
        return appealvillage;
    }

    public void setAppealvillage(String appealvillage) {
        this.appealvillage = appealvillage;
    }

    public String getDutyleader() {
        return dutyleader;
    }

    public void setDutyleader(String dutyleader) {
        this.dutyleader = dutyleader;
    }

    public String getDutydept() {
        return dutydept;
    }

    public void setDutydept(String dutydept) {
        this.dutydept = dutydept;
    }

    public String getDutymenber() {
        return dutymenber;
    }

    public void setDutymenber(String dutymenber) {
        this.dutymenber = dutymenber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getRatedate() {
        return ratedate;
    }

    public void setRatedate(Date ratedate) {
        this.ratedate = ratedate;
    }

    @Override
    public String toString() {
        return "RatedInfo{" +
                "appealid=" + appealid +
                ", uuid='" + uuid + '\'' +
                ", appealname='" + appealname + '\'' +
                ", appealvillage='" + appealvillage + '\'' +
                ", dutyleader='" + dutyleader + '\'' +
                ", dutydept='" + dutydept + '\'' +
                ", dutymenber='" + dutymenber + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                ", ratelevel=" + ratelevel +
                ", ratecontent='" + ratecontent + '\'' +
                ", ratedate=" + ratedate +
                '}';
    }
}
