package com.jmhz.device.pageModel;

/**
 * Created by 锋情 on 2014/4/18.
 */
public class MqCard {
    private  int id;
    private String uuid;
    private String village;//村
    private String grid;//网格
    private String gridcharger;//网格责任人
    private String hiddengridcharger;//网格责任人
    private String family;//户
    private String visittime;//走访时间
    private String visitremark;//走访备注
    private String householdername;//户主姓名
    private String gender;//性 别
    private String representation;//任职情况
    private String birthday;//出生年月
    private String politicalstatus;//政治面貌
    private String familypopulation;//家庭人口
    private String contactnumber;//联系电话
    private String plantingproject;//种植业 项目
    private String plantingscale;//种植业 规模
    private String farmingproject;//养殖业 项目
    private String farmingscale;//养殖业 规模
    private String scaleunit;//养殖业 规模单位

    private String snackprovince;//小吃业 省份
    private String snackcity;//小吃业 市
    private String snackarea;//小吃业 地区
    private String snackscale;//小吃业 月营业收入约

    private String workprofession;//务工 职业

    private String workprovince;//务工 所在省份
    private String workcity;//务工 所在市
    private String workarea;//务工 所在地区

    private String foundedname;//创办实体 实体名称
    private String foundedvalue;//创办实体 年产值
    private String othersofproduction;//其他
    private String housingsituation;//住房情况
    private String otherhousingsituation;//住房其他
    private String specialfamily;//特殊家庭
    private String otherspecialfamily;//特殊家庭 其他
    private String appealtype;//诉求类别
    private String affairtype;//事务类别
    private String hardshipappeal;//诉求内容

    public MqCard() {
    }

    public MqCard(int id, String uuid, String village, String grid, String gridcharger, String hiddengridcharger, String family, String visittime, String visitremark, String householdername, String gender, String representation, String birthday, String politicalstatus, String familypopulation, String contactnumber, String plantingproject, String plantingscale, String farmingproject, String farmingscale, String scaleunit, String snackprovince, String snackcity, String snackarea, String snackscale, String workprofession, String workprovince, String workcity, String workarea, String foundedname, String foundedvalue, String othersofproduction, String housingsituation, String otherhousingsituation, String specialfamily, String otherspecialfamily, String appealtype, String affairtype, String hardshipappeal) {
        this.id = id;
        this.uuid = uuid;
        this.village = village;
        this.grid = grid;
        this.gridcharger = gridcharger;
        this.hiddengridcharger = hiddengridcharger;
        this.family = family;
        this.visittime = visittime;
        this.visitremark = visitremark;
        this.householdername = householdername;
        this.gender = gender;
        this.representation = representation;
        this.birthday = birthday;
        this.politicalstatus = politicalstatus;
        this.familypopulation = familypopulation;
        this.contactnumber = contactnumber;
        this.plantingproject = plantingproject;
        this.plantingscale = plantingscale;
        this.farmingproject = farmingproject;
        this.farmingscale = farmingscale;
        this.scaleunit = scaleunit;
        this.snackprovince = snackprovince;
        this.snackcity = snackcity;
        this.snackarea = snackarea;
        this.snackscale = snackscale;
        this.workprofession = workprofession;
        this.workprovince = workprovince;
        this.workcity = workcity;
        this.workarea = workarea;
        this.foundedname = foundedname;
        this.foundedvalue = foundedvalue;
        this.othersofproduction = othersofproduction;
        this.housingsituation = housingsituation;
        this.otherhousingsituation = otherhousingsituation;
        this.specialfamily = specialfamily;
        this.otherspecialfamily = otherspecialfamily;
        this.appealtype = appealtype;
        this.affairtype = affairtype;
        this.hardshipappeal = hardshipappeal;
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

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getGrid() {
        return grid;
    }

    public void setGrid(String grid) {
        this.grid = grid;
    }

    public String getGridcharger() {
        return gridcharger;
    }

    public void setGridcharger(String gridcharger) {
        this.gridcharger = gridcharger;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
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

    public String getHouseholdername() {
        return householdername;
    }

    public void setHouseholdername(String householdername) {
        this.householdername = householdername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPoliticalstatus() {
        return politicalstatus;
    }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus;
    }

    public String getFamilypopulation() {
        return familypopulation;
    }

    public void setFamilypopulation(String familypopulation) {
        this.familypopulation = familypopulation;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public String getPlantingproject() {
        return plantingproject;
    }

    public void setPlantingproject(String plantingproject) {
        this.plantingproject = plantingproject;
    }

    public String getPlantingscale() {
        return plantingscale;
    }

    public void setPlantingscale(String plantingscale) {
        this.plantingscale = plantingscale;
    }

    public String getFarmingproject() {
        return farmingproject;
    }

    public void setFarmingproject(String farmingproject) {
        this.farmingproject = farmingproject;
    }

    public String getFarmingscale() {
        return farmingscale;
    }

    public void setFarmingscale(String farmingscale) {
        this.farmingscale = farmingscale;
    }



    public String getSnackscale() {
        return snackscale;
    }

    public void setSnackscale(String snackscale) {
        this.snackscale = snackscale;
    }

    public String getWorkprofession() {
        return workprofession;
    }

    public void setWorkprofession(String workprofession) {
        this.workprofession = workprofession;
    }



    public String getFoundedname() {
        return foundedname;
    }

    public void setFoundedname(String foundedname) {
        this.foundedname = foundedname;
    }

    public String getFoundedvalue() {
        return foundedvalue;
    }

    public void setFoundedvalue(String foundedvalue) {
        this.foundedvalue = foundedvalue;
    }

    public String getOthersofproduction() {
        return othersofproduction;
    }

    public void setOthersofproduction(String othersofproduction) {
        this.othersofproduction = othersofproduction;
    }

    public String getHousingsituation() {
        return housingsituation;
    }

    public void setHousingsituation(String housingsituation) {
        this.housingsituation = housingsituation;
    }

    public String getSpecialfamily() {
        return specialfamily;
    }

    public void setSpecialfamily(String specialfamily) {
        this.specialfamily = specialfamily;
    }

    public String getOtherspecialfamily() {
        return otherspecialfamily;
    }

    public void setOtherspecialfamily(String otherspecialfamily) {
        this.otherspecialfamily = otherspecialfamily;
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

    public String getOtherhousingsituation() {
        return otherhousingsituation;
    }

    public void setOtherhousingsituation(String otherhousingsituation) {
        this.otherhousingsituation = otherhousingsituation;
    }

    public String getScaleunit() {
        return scaleunit;
    }

    public void setScaleunit(String scaleunit) {
        this.scaleunit = scaleunit;
    }

    public String getSnackprovince() {
        return snackprovince;
    }

    public void setSnackprovince(String snackprovince) {
        this.snackprovince = snackprovince;
    }

    public String getSnackcity() {
        return snackcity;
    }

    public void setSnackcity(String snackcity) {
        this.snackcity = snackcity;
    }

    public String getSnackarea() {
        return snackarea;
    }

    public void setSnackarea(String snackarea) {
        this.snackarea = snackarea;
    }

    public String getWorkprovince() {
        return workprovince;
    }

    public void setWorkprovince(String workprovince) {
        this.workprovince = workprovince;
    }

    public String getWorkcity() {
        return workcity;
    }

    public void setWorkcity(String workcity) {
        this.workcity = workcity;
    }

    public String getWorkarea() {
        return workarea;
    }

    public void setWorkarea(String workarea) {
        this.workarea = workarea;
    }

    public String getHiddengridcharger() {
        return hiddengridcharger;
    }

    public void setHiddengridcharger(String hiddengridcharger) {
        this.hiddengridcharger = hiddengridcharger;
    }
}
