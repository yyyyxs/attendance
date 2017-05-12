package com.jmhz.device.pageModel;

/**
 * Create by 陈鑫on 2014/4/22.
 */
public class AppealmarkStatus {


    private String id;//备注状态id
    private String uuid;
    private String appealid;// appeal id
    private String householdername;// 户主name new
    private String processupdatetime;// 添加备注时间
    private String processupdateremark;// 备注内容

    @Override
    public String toString() {
        return "AppealmarkStatus{" + "id='" + id + '\''+
                ", uuid='" + uuid + '\'' +
                ",appealid='" + appealid + '\'' +
                ", householdername='" + householdername + '\'' +
                ", processupdatetime='" + processupdatetime + '\'' +
                ", processupdateremark='" + processupdateremark + '\'' +
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


    public String getAppealid() {
        return appealid;
    }

    public void setAppealid(String appealid) {
        this.appealid = appealid;
    }

    public String getHouseholdername() {
        return householdername;
    }

    public void setHouseholdername(String householdername) {
        this.householdername = householdername;
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

}
