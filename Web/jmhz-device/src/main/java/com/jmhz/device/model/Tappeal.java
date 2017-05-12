package com.jmhz.device.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by 锋情 on 2014/4/18.
 */
@Entity
@Table(name = "tappeal")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tappeal {

    private int id;
    private String uuid;
    private int appealid;// 诉求方id  就是农户id？
    private String appealname;// 诉求方名
    private String city;//县市
    private String town;//镇
    private String village;//村
    private String proposer;// 个人提出的诉求0 | 集体提出的诉求1
    private String appealtype;// 诉求类别
    private String affairtype;// 事务类别 0 个人事务 ； 1 集体事务
    private String status;// 状态 0 未解决 ； 1 以上报上级协调解决 ；2 延时解决 ；3 正在解决 ；4 已做好解释说明工作 ；5 已解决
    private String doingstatus; //正在解决的状态
    private String hardshipappeal;// 诉求内容
    private String dutyleader;// 责任领导
    private String dutydept;// 责任部门
    private String dutymenber;// 责任人
    private String solution;// 解决措施
    private String timelimit;// 完成时限
    private String createdate;// 创建时间
    private String appealtel;// 诉求者联系号码
    private String publish;// 是否公开
    public Tappeal() {
    }

    public Tappeal(int id, String uuid, int appealid, String appealname, String city, String town, String village, String proposer, String appealtype, String affairtype, String status, String doingstatus, String hardshipappeal, String dutyleader, String dutydept, String dutymenber, String solution, String timelimit, String createdate, String appealtel, String publish) {
        this.id = id;
        this.uuid = uuid;
        this.appealid = appealid;
        this.appealname = appealname;
        this.city = city;
        this.town = town;
        this.village = village;
        this.proposer = proposer;
        this.appealtype = appealtype;
        this.affairtype = affairtype;
        this.status = status;
        this.doingstatus = doingstatus;
        this.hardshipappeal = hardshipappeal;
        this.dutyleader = dutyleader;
        this.dutydept = dutydept;
        this.dutymenber = dutymenber;
        this.solution = solution;
        this.timelimit = timelimit;
        this.createdate = createdate;
        this.appealtel = appealtel;
        this.publish = publish;
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getAppealtype() {
        return appealtype;
    }

    public void setAppealtype(String appealtype) {
        this.appealtype = appealtype;
    }

    public String getAffairtype() {
        return affairtype;
    }

    public void setAffairtype(String affairtype) {
        this.affairtype = affairtype;
    }

    public String getHardshipappeal() {
        return hardshipappeal;
    }

    public void setHardshipappeal(String hardshipappeal) {
        this.hardshipappeal = hardshipappeal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDoingstatus() {
        return doingstatus;
    }

    public void setDoingstatus(String doingstatus) {
        this.doingstatus = doingstatus;
    }

    public int getAppealid() {
        return appealid;
    }

    public void setAppealid(int appealid) {
        this.appealid = appealid;
    }

    public String getAppealname() {
        return appealname;
    }

    public void setAppealname(String appealname) {
        this.appealname = appealname;
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

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
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

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getAppealtel() {
        return appealtel;
    }

    public void setAppealtel(String appealtel) {
        this.appealtel = appealtel;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    @Override
    public String toString() {
        return "Tappeal{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", appealid=" + appealid +
                ", appealname='" + appealname + '\'' +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", village='" + village + '\'' +
                ", proposer='" + proposer + '\'' +
                ", appealtype='" + appealtype + '\'' +
                ", affairtype='" + affairtype + '\'' +
                ", status='" + status + '\'' +
                ", doingstatus='" + doingstatus + '\'' +
                ", hardshipappeal='" + hardshipappeal + '\'' +
                ", dutyleader='" + dutyleader + '\'' +
                ", dutydept='" + dutydept + '\'' +
                ", dutymenber='" + dutymenber + '\'' +
                ", solution='" + solution + '\'' +
                ", timelimit='" + timelimit + '\'' +
                ", createdate='" + createdate + '\'' +
                ", appealtel='" + appealtel + '\'' +
                ", publish='" + publish + '\'' +
                '}';
    }
}
