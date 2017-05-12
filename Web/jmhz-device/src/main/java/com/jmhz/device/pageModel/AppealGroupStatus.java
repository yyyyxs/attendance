package com.jmhz.device.pageModel;

/**
 * Created by 锋情 on 2014/4/19.
 */
public class AppealGroupStatus {
    private String id;// appeal id
    private String uuid;
    private String groupname;// 户主name new
    private String groupnamehidden;// 户主name old
    private String groupidhidden;// 户主id old
    private String groupchargertel;// 户主联系号码
    private String appealtype;// 诉求类别
    private String affairtype;// 事务类别 0 个人事务 ； 1 集体事务
    private String hardshipappeal;// 诉求内容
    private String dutyleader;// 责任领导
    private String dutydept;// 责任部门
    private String dutymenber;// 责任人
    private String solution;// 解决措施
    private String timelimit;// 完成时限
    private String status;// 状态 0 未解决 ； 1 以上报上级协调解决 ；2 延时解决 ；3 正在解决 ；4 已做好解释说明工作 ；5 已解决
    private String doingstatus;//正在解决的状态，三个状态
    private String processupdatetime;// 添加备注时间
    private String processupdateremark;// 备注内容
    private String createdate;// 创建时间

    @Override
    public String toString() {
        return "AppealGroupStatus{" +
                "id='" + id + '\'' +
                ", uuid='" + uuid + '\'' +
                ", groupname='" + groupname + '\'' +
                ", groupnamehidden='" + groupnamehidden + '\'' +
                ", groupidhidden='" + groupidhidden + '\'' +
                ", groupchargertel='" + groupchargertel + '\'' +
                ", appealtype='" + appealtype + '\'' +
                ", affairtype='" + affairtype + '\'' +
                ", hardshipappeal='" + hardshipappeal + '\'' +
                ", dutyleader='" + dutyleader + '\'' +
                ", dutydept='" + dutydept + '\'' +
                ", dutymenber='" + dutymenber + '\'' +
                ", solution='" + solution + '\'' +
                ", timelimit='" + timelimit + '\'' +
                ", status='" + status + '\'' +
                ", doingstatus='" + doingstatus + '\'' +
                ", processupdatetime='" + processupdatetime + '\'' +
                ", processupdateremark='" + processupdateremark + '\'' +
                ", createdate='" + createdate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupnamehidden() {
        return groupnamehidden;
    }

    public void setGroupnamehidden(String groupnamehidden) {
        this.groupnamehidden = groupnamehidden;
    }

    public String getGroupidhidden() {
        return groupidhidden;
    }

    public void setGroupidhidden(String groupidhidden) {
        this.groupidhidden = groupidhidden;
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

    public String getProcessupdatetime() {
        return processupdatetime;
    }

    public void setProcessupdatetime(String processupdatetime) {
        this.processupdatetime = processupdatetime;
    }

    public String getProcessupdateremark() {
        return processupdateremark;
    }

    public void setProcessupdateremark(String processupdateremark) {
        this.processupdateremark = processupdateremark;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getGroupchargertel() {
        return groupchargertel;
    }

    public void setGroupchargertel(String groupchargertel) {
        this.groupchargertel = groupchargertel;
    }
}