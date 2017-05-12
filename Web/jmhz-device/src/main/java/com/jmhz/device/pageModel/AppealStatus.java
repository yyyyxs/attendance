package com.jmhz.device.pageModel;

/**
 * Created by 锋情 on 2014/4/19.
 */
public class AppealStatus {
    private int id;// appeal id
    private String uuid;
    private String householdername;// 户主name new
    private String appealvillage;
    private String householdernamehidden;// 户主name old
    private String householderidhidden;// 户主id old
    private String contactnumber;// 户主联系号码
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
    private String publish;// 是否公开
    public AppealStatus() {
    }

    public AppealStatus(int id, String uuid, String householdername, String appealvillage, String householdernamehidden, String householderidhidden, String contactnumber, String appealtype, String affairtype, String hardshipappeal, String dutyleader, String dutydept, String dutymenber, String solution, String timelimit, String status, String doingstatus, String processupdatetime, String processupdateremark, String createdate, String publish) {
        this.id = id;
        this.uuid = uuid;
        this.householdername = householdername;
        this.appealvillage = appealvillage;
        this.householdernamehidden = householdernamehidden;
        this.householderidhidden = householderidhidden;
        this.contactnumber = contactnumber;
        this.appealtype = appealtype;
        this.affairtype = affairtype;
        this.hardshipappeal = hardshipappeal;
        this.dutyleader = dutyleader;
        this.dutydept = dutydept;
        this.dutymenber = dutymenber;
        this.solution = solution;
        this.timelimit = timelimit;
        this.status = status;
        this.doingstatus = doingstatus;
        this.processupdatetime = processupdatetime;
        this.processupdateremark = processupdateremark;
        this.createdate = createdate;
        this.publish = publish;
    }

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

    public String getHouseholdername() {
        return householdername;
    }

    public void setHouseholdername(String householdername) {
        this.householdername = householdername;
    }

    public String getAppealvillage() {
        return appealvillage;
    }

    public void setAppealvillage(String appealvillage) {
        this.appealvillage = appealvillage;
    }

    public String getHouseholdernamehidden() {
        return householdernamehidden;
    }

    public void setHouseholdernamehidden(String householdernamehidden) {
        this.householdernamehidden = householdernamehidden;
    }

    public String getHouseholderidhidden() {
        return householderidhidden;
    }

    public void setHouseholderidhidden(String householderidhidden) {
        this.householderidhidden = householderidhidden;
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

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
