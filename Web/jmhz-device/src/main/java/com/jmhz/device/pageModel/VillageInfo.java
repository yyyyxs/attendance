package com.jmhz.device.pageModel;

/**
 * Created by wby on 2014/7/30.
 */
public class VillageInfo {
    private int id;
    private String city;
    private String town;
    private String village;
    private String villagerTeam;
    private String households;
    private String totalPepCnt;
    private String totalPepMan;
    private String totalPepWoman;
    private String belowEighteen;
    private String belowSixty;
    private String aboveSixty;
    private String mainNation;
    private String nationPeps;
    private String otherNation;
    private String otherPeps;
    private String partyGroupCnt;
    private String partyMemCnt;
    private String hometownRemain;
    private String fiveGuarantees;
    private String lowIncome;
    private String poorFamily;
    private String otherFamily;
    private String totalCultArea;
    private String perCultArea;
    private String forestArea;
    private String fruitArea;
    private String bambooArea;
    private String landCycleCond;
    private String primaryIndValue;
    private String primaryIntPepCnt;
    private String secondIndValue;
    private String secondIndPepCnt;
    private String thirdIndValue;
    private String thirdIndPepCnt;
    private String snackHouseholds;
    private String snackPepCnt;
    private String snackIncome;
    private String coopPlant;
    private String coopBreed;
    private String coopAgro;
    private String coopOther;
    private String totalIncome;
    private String perFarmerIncome;
    private String villageOrgArea;
    private String farmerFitWeap;
    private String farmerFitArea;
    private String elderRoomArea;
    private String healthRoomArea;
    private String healthRoomBeds;
    private String eduPotTeachers;
    private String eduPotStudents;
    private String kidTeachers;
    private String kidStudents;
    private String vilTotalIncome;
    private String vilTotalAssets;
    private String vilTotalDebts;
    private String twoCommitsCnt;
    private String vilRepresentCnt;

    public VillageInfo(){

    }

    public VillageInfo(int id, String city, String town, String village, String villagerTeam, String households, String totalPepCnt, String totalPepMan, String totalPepWoman, String belowEighteen, String belowSixty, String aboveSixty, String mainNation, String nationPeps, String otherNation, String otherPeps, String partyGroupCnt, String partyMemCnt, String hometownRemain, String fiveGuarantees, String lowIncome, String poorFamily, String otherFamily, String totalCultArea, String perCultArea, String forestArea, String fruitArea, String bambooArea, String landCycleCond, String primaryIndValue, String primaryIntPepCnt, String secondIndValue, String secondIndPepCnt, String thirdIndValue, String thirdIndPepCnt, String snackHouseholds, String snackPepCnt, String snackIncome, String coopPlant, String coopBreed, String coopAgro, String coopOther, String totalIncome, String perFarmerIncome, String villageOrgArea, String farmerFitWeap, String farmerFitArea, String elderRoomArea, String healthRoomArea, String healthRoomBeds, String eduPotTeachers, String eduPotStudents, String kidTeachers, String kidStudents, String vilTotalIncome, String vilTotalAssets, String vilTotalDebts, String twoCommitsCnt, String vilRepresentCnt) {
        this.id = id;
        this.city = city;
        this.town = town;
        this.village = village;
        this.villagerTeam = villagerTeam;
        this.households = households;
        this.totalPepCnt = totalPepCnt;
        this.totalPepMan = totalPepMan;
        this.totalPepWoman = totalPepWoman;
        this.belowEighteen = belowEighteen;
        this.belowSixty = belowSixty;
        this.aboveSixty = aboveSixty;
        this.mainNation = mainNation;
        this.nationPeps = nationPeps;
        this.otherNation = otherNation;
        this.otherPeps = otherPeps;
        this.partyGroupCnt = partyGroupCnt;
        this.partyMemCnt = partyMemCnt;
        this.hometownRemain = hometownRemain;
        this.fiveGuarantees = fiveGuarantees;
        this.lowIncome = lowIncome;
        this.poorFamily = poorFamily;
        this.otherFamily = otherFamily;
        this.totalCultArea = totalCultArea;
        this.perCultArea = perCultArea;
        this.forestArea = forestArea;
        this.fruitArea = fruitArea;
        this.bambooArea = bambooArea;
        this.landCycleCond = landCycleCond;
        this.primaryIndValue = primaryIndValue;
        this.primaryIntPepCnt = primaryIntPepCnt;
        this.secondIndValue = secondIndValue;
        this.secondIndPepCnt = secondIndPepCnt;
        this.thirdIndValue = thirdIndValue;
        this.thirdIndPepCnt = thirdIndPepCnt;
        this.snackHouseholds = snackHouseholds;
        this.snackPepCnt = snackPepCnt;
        this.snackIncome = snackIncome;
        this.coopPlant = coopPlant;
        this.coopBreed = coopBreed;
        this.coopAgro = coopAgro;
        this.coopOther = coopOther;
        this.totalIncome = totalIncome;
        this.perFarmerIncome = perFarmerIncome;
        this.villageOrgArea = villageOrgArea;
        this.farmerFitWeap = farmerFitWeap;
        this.farmerFitArea = farmerFitArea;
        this.elderRoomArea = elderRoomArea;
        this.healthRoomArea = healthRoomArea;
        this.healthRoomBeds = healthRoomBeds;
        this.eduPotTeachers = eduPotTeachers;
        this.eduPotStudents = eduPotStudents;
        this.kidTeachers = kidTeachers;
        this.kidStudents = kidStudents;
        this.vilTotalIncome = vilTotalIncome;
        this.vilTotalAssets = vilTotalAssets;
        this.vilTotalDebts = vilTotalDebts;
        this.twoCommitsCnt = twoCommitsCnt;
        this.vilRepresentCnt = vilRepresentCnt;
    }

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

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getVillagerTeam() {
        return villagerTeam;
    }

    public void setVillagerTeam(String villagerTeam) {
        this.villagerTeam = villagerTeam;
    }

    public String getHouseholds() {
        return households;
    }

    public void setHouseholds(String households) {
        this.households = households;
    }

    public String getTotalPepCnt() {
        return totalPepCnt;
    }

    public void setTotalPepCnt(String totalPepCnt) {
        this.totalPepCnt = totalPepCnt;
    }

    public String getTotalPepMan() {
        return totalPepMan;
    }

    public void setTotalPepMan(String totalPepMan) {
        this.totalPepMan = totalPepMan;
    }

    public String getTotalPepWoman() {
        return totalPepWoman;
    }

    public void setTotalPepWoman(String totalPepWoman) {
        this.totalPepWoman = totalPepWoman;
    }

    public String getBelowEighteen() {
        return belowEighteen;
    }

    public void setBelowEighteen(String belowEighteen) {
        this.belowEighteen = belowEighteen;
    }

    public String getBelowSixty() {
        return belowSixty;
    }

    public void setBelowSixty(String belowSixty) {
        this.belowSixty = belowSixty;
    }

    public String getAboveSixty() {
        return aboveSixty;
    }

    public void setAboveSixty(String aboveSixty) {
        this.aboveSixty = aboveSixty;
    }

    public String getMainNation() {
        return mainNation;
    }

    public void setMainNation(String mainNation) {
        this.mainNation = mainNation;
    }

    public String getNationPeps() {
        return nationPeps;
    }

    public void setNationPeps(String nationPeps) {
        this.nationPeps = nationPeps;
    }

    public String getOtherNation() {
        return otherNation;
    }

    public void setOtherNation(String otherNation) {
        this.otherNation = otherNation;
    }

    public String getOtherPeps() {
        return otherPeps;
    }

    public void setOtherPeps(String otherPeps) {
        this.otherPeps = otherPeps;
    }

    public String getPartyGroupCnt() {
        return partyGroupCnt;
    }

    public void setPartyGroupCnt(String partyGroupCnt) {
        this.partyGroupCnt = partyGroupCnt;
    }

    public String getPartyMemCnt() {
        return partyMemCnt;
    }

    public void setPartyMemCnt(String partyMemCnt) {
        this.partyMemCnt = partyMemCnt;
    }

    public String getHometownRemain() {
        return hometownRemain;
    }

    public void setHometownRemain(String hometownRemain) {
        this.hometownRemain = hometownRemain;
    }

    public String getFiveGuarantees() {
        return fiveGuarantees;
    }

    public void setFiveGuarantees(String fiveGuarantees) {
        this.fiveGuarantees = fiveGuarantees;
    }

    public String getLowIncome() {
        return lowIncome;
    }

    public void setLowIncome(String lowIncome) {
        this.lowIncome = lowIncome;
    }

    public String getPoorFamily() {
        return poorFamily;
    }

    public void setPoorFamily(String poorFamily) {
        this.poorFamily = poorFamily;
    }

    public String getOtherFamily() {
        return otherFamily;
    }

    public void setOtherFamily(String otherFamily) {
        this.otherFamily = otherFamily;
    }

    public String getTotalCultArea() {
        return totalCultArea;
    }

    public void setTotalCultArea(String totalCultArea) {
        this.totalCultArea = totalCultArea;
    }

    public String getPerCultArea() {
        return perCultArea;
    }

    public void setPerCultArea(String perCultArea) {
        this.perCultArea = perCultArea;
    }

    public String getForestArea() {
        return forestArea;
    }

    public void setForestArea(String forestArea) {
        this.forestArea = forestArea;
    }

    public String getFruitArea() {
        return fruitArea;
    }

    public void setFruitArea(String fruitArea) {
        this.fruitArea = fruitArea;
    }

    public String getBambooArea() {
        return bambooArea;
    }

    public void setBambooArea(String bambooArea) {
        this.bambooArea = bambooArea;
    }

    public String getLandCycleCond() {
        return landCycleCond;
    }

    public void setLandCycleCond(String landCycleCond) {
        this.landCycleCond = landCycleCond;
    }

    public String getPrimaryIndValue() {
        return primaryIndValue;
    }

    public void setPrimaryIndValue(String primaryIndValue) {
        this.primaryIndValue = primaryIndValue;
    }

    public String getPrimaryIntPepCnt() {
        return primaryIntPepCnt;
    }

    public void setPrimaryIntPepCnt(String primaryIntPepCnt) {
        this.primaryIntPepCnt = primaryIntPepCnt;
    }

    public String getSecondIndValue() {
        return secondIndValue;
    }

    public void setSecondIndValue(String secondIndValue) {
        this.secondIndValue = secondIndValue;
    }

    public String getSecondIndPepCnt() {
        return secondIndPepCnt;
    }

    public void setSecondIndPepCnt(String secondIndPepCnt) {
        this.secondIndPepCnt = secondIndPepCnt;
    }

    public String getThirdIndValue() {
        return thirdIndValue;
    }

    public void setThirdIndValue(String thirdIndValue) {
        this.thirdIndValue = thirdIndValue;
    }

    public String getThirdIndPepCnt() {
        return thirdIndPepCnt;
    }

    public void setThirdIndPepCnt(String thirdIndPepCnt) {
        this.thirdIndPepCnt = thirdIndPepCnt;
    }

    public String getSnackHouseholds() {
        return snackHouseholds;
    }

    public void setSnackHouseholds(String snackHouseholds) {
        this.snackHouseholds = snackHouseholds;
    }

    public String getSnackPepCnt() {
        return snackPepCnt;
    }

    public void setSnackPepCnt(String snackPepCnt) {
        this.snackPepCnt = snackPepCnt;
    }

    public String getSnackIncome() {
        return snackIncome;
    }

    public void setSnackIncome(String snackIncome) {
        this.snackIncome = snackIncome;
    }

    public String getCoopPlant() {
        return coopPlant;
    }

    public void setCoopPlant(String coopPlant) {
        this.coopPlant = coopPlant;
    }

    public String getCoopBreed() {
        return coopBreed;
    }

    public void setCoopBreed(String coopBreed) {
        this.coopBreed = coopBreed;
    }

    public String getCoopAgro() {
        return coopAgro;
    }

    public void setCoopAgro(String coopAgro) {
        this.coopAgro = coopAgro;
    }

    public String getCoopOther() {
        return coopOther;
    }

    public void setCoopOther(String coopOther) {
        this.coopOther = coopOther;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getPerFarmerIncome() {
        return perFarmerIncome;
    }

    public void setPerFarmerIncome(String perFarmerIncome) {
        this.perFarmerIncome = perFarmerIncome;
    }

    public String getVillageOrgArea() {
        return villageOrgArea;
    }

    public void setVillageOrgArea(String villageOrgArea) {
        this.villageOrgArea = villageOrgArea;
    }

    public String getFarmerFitWeap() {
        return farmerFitWeap;
    }

    public void setFarmerFitWeap(String farmerFitWeap) {
        this.farmerFitWeap = farmerFitWeap;
    }

    public String getFarmerFitArea() {
        return farmerFitArea;
    }

    public void setFarmerFitArea(String farmerFitArea) {
        this.farmerFitArea = farmerFitArea;
    }

    public String getElderRoomArea() {
        return elderRoomArea;
    }

    public void setElderRoomArea(String elderRoomArea) {
        this.elderRoomArea = elderRoomArea;
    }

    public String getHealthRoomArea() {
        return healthRoomArea;
    }

    public void setHealthRoomArea(String healthRoomArea) {
        this.healthRoomArea = healthRoomArea;
    }

    public String getHealthRoomBeds() {
        return healthRoomBeds;
    }

    public void setHealthRoomBeds(String healthRoomBeds) {
        this.healthRoomBeds = healthRoomBeds;
    }

    public String getEduPotTeachers() {
        return eduPotTeachers;
    }

    public void setEduPotTeachers(String eduPotTeachers) {
        this.eduPotTeachers = eduPotTeachers;
    }

    public String getEduPotStudents() {
        return eduPotStudents;
    }

    public void setEduPotStudents(String eduPotStudents) {
        this.eduPotStudents = eduPotStudents;
    }

    public String getKidTeachers() {
        return kidTeachers;
    }

    public void setKidTeachers(String kidTeachers) {
        this.kidTeachers = kidTeachers;
    }

    public String getKidStudents() {
        return kidStudents;
    }

    public void setKidStudents(String kidStudents) {
        this.kidStudents = kidStudents;
    }

    public String getVilTotalIncome() {
        return vilTotalIncome;
    }

    public void setVilTotalIncome(String vilTotalIncome) {
        this.vilTotalIncome = vilTotalIncome;
    }

    public String getVilTotalAssets() {
        return vilTotalAssets;
    }

    public void setVilTotalAssets(String vilTotalAssets) {
        this.vilTotalAssets = vilTotalAssets;
    }

    public String getVilTotalDebts() {
        return vilTotalDebts;
    }

    public void setVilTotalDebts(String vilTotalDebts) {
        this.vilTotalDebts = vilTotalDebts;
    }

    public String getTwoCommitsCnt() {
        return twoCommitsCnt;
    }

    public void setTwoCommitsCnt(String twoCommitsCnt) {
        this.twoCommitsCnt = twoCommitsCnt;
    }

    public String getVilRepresentCnt() {
        return vilRepresentCnt;
    }

    public void setVilRepresentCnt(String vilRepresentCnt) {
        this.vilRepresentCnt = vilRepresentCnt;
    }
}
