package com.jmhz.device.service.impl;

import com.google.common.collect.Maps;
import com.jmhz.device.util.LogUtils;
import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.dao.VillageDaoI;
import com.jmhz.device.model.Tvillage;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.VillageDataExportServiceI;
import com.jmhz.device.sys.dao.IExportDataDao;
import com.jmhz.device.sys.entity.ExportData;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VillageDataExportServiceImpl implements VillageDataExportServiceI {

    @Autowired
    private VillageDaoI villageDao;

    @Autowired
    private IExportDataDao exportDataDao;

    private static final String storePath = "export/excel";
    private static final String EXPORT_FILENAME_PREFIX = "GaoQiao_Village";
    private static final String EXPORT_FILENAME_SUFFIX = "xlsx";
    private static final int pageSize = 1000;//查询时每页大小
    private static final int rowAccessWindowSize = 1000; //内存中保留的行数，超出后会写到磁盘
    private static final int perSheetRows = 100000; //每个sheet 10w条

    /**
     * 支持大数据量导出
     * excel 2007 每个sheet最多1048576行
     *
     * @param contextRootPath
     */
    @Async
    public void exportExcel2007(final String contextRootPath) {
        int totalRows = 0; //统计总行数
        Long maxId = 0L;//当前查询的数据中最大的id 优化分页的

        String fileName = generateFilename(contextRootPath, EXPORT_FILENAME_SUFFIX);
        File file = new File(fileName);
        BufferedOutputStream out = null;
        SXSSFWorkbook wb = null;

        try {
            long beginTime = System.currentTimeMillis();
            wb = new SXSSFWorkbook(rowAccessWindowSize);
            wb.setCompressTempFiles(true);//生成的临时文件将进行gzip压缩

            while (true) {
                Sheet sheet = wb.createSheet();
                createHeaderRow(sheet);

                totalRows = 1;
                List<Tvillage> tvillageDataExportList;
                String hqlToCount = "select count(*) from Tvillage";
                int totalElements = villageDao.count(hqlToCount).intValue();
                int totalPages = totalElements % pageSize == 0 ? totalElements / pageSize : totalElements / pageSize + 1;
                int currentPage = 1;
                Map<String, Object> params = new HashMap<String, Object>();

                String hql = "from Tvillage t where t.id>:maxId order by t.villageName asc";
                do {//都在这里面循环
                    params.put("maxId", maxId.intValue());
                    tvillageDataExportList = villageDao.find(hql, params, 0, pageSize);
                    for (Tvillage data : tvillageDataExportList) {
                        int dataCellId = 0;
                        Row row = sheet.createRow(totalRows);
                        Cell idCell = row.createCell(dataCellId++);//id
                        idCell.setCellValue(data.getId());
                        Cell villageNameCell = row.createCell(dataCellId++);//villageName
                        villageNameCell.setCellValue(data.getVillage());
                        Cell villagerTeamCell = row.createCell(dataCellId++);//villagerTeam
                        villagerTeamCell.setCellValue(data.getVillagerTeam());
                        Cell householdsCell = row.createCell(dataCellId++);//households
                        householdsCell.setCellValue(data.getHouseholds());
                        Cell totalPepCntCell = row.createCell(dataCellId++);//totalPepCnt
                        totalPepCntCell.setCellValue(data.getTotalPepCnt());
                        Cell totalPepManCell = row.createCell(dataCellId++);//totalPepMan
                        totalPepManCell.setCellValue(data.getTotalPepMan());
                        Cell totalPepWomanCell = row.createCell(dataCellId++);//totalPepWoman
                        totalPepWomanCell.setCellValue(data.getTotalPepWoman());
                        Cell belowEighteenCell = row.createCell(dataCellId++);//belowEighteen
                        belowEighteenCell.setCellValue(data.getBelowEighteen());
                        Cell belowSixtyCell = row.createCell(dataCellId++);//belowSixty
                        belowSixtyCell.setCellValue(data.getBelowSixty());
                        Cell aboveSixtyCell = row.createCell(dataCellId++);//aboveSixty
                        aboveSixtyCell.setCellValue(data.getAboveSixty());
                        Cell mainNationCell = row.createCell(dataCellId++);//mainNation
                        mainNationCell.setCellValue(data.getMainNation());
                        Cell nationPepsCell = row.createCell(dataCellId++);//nationPeps
                        nationPepsCell.setCellValue(data.getNationPeps());
                        Cell otherNationCell = row.createCell(dataCellId++);//otherNation
                        otherNationCell.setCellValue(data. getOtherNation());
                        Cell otherPepsCell = row.createCell(dataCellId++);//otherPeps
                        otherPepsCell.setCellValue(data.getOtherPeps());
                        Cell partyGroupCntCell = row.createCell(dataCellId++);//partyGroupCnt
                        partyGroupCntCell.setCellValue(data.getPartyGroupCnt());
                        Cell partyMemCntCell = row.createCell(dataCellId++);//partyMemCnt
                        partyMemCntCell.setCellValue(data.getPartyMemCnt());
                        Cell hometownRemainCell = row.createCell(dataCellId++);//hometownRemain
                        hometownRemainCell.setCellValue(data. getHometownRemain());
                        Cell fiveGuaranteesCell = row.createCell(dataCellId++);//fiveGuarantees
                        fiveGuaranteesCell.setCellValue(data.getFiveGuarantees());
                        Cell lowIncomeCell = row.createCell(dataCellId++);//lowIncome
                        lowIncomeCell.setCellValue(data. getLowIncome());
                        Cell poorFamilyCell = row.createCell(dataCellId++);//poorFamily
                        poorFamilyCell.setCellValue(data. getPoorFamily());
                        Cell otherFamilyCell = row.createCell(dataCellId++);//otherFamily
                        otherFamilyCell.setCellValue(data.getOtherFamily());
                        Cell totalCultAreaCell = row.createCell(dataCellId++);//totalCultArea
                        totalCultAreaCell.setCellValue(data.getTotalCultArea());
                        Cell perCultAreaCell = row.createCell(dataCellId++);//perCultArea
                        perCultAreaCell.setCellValue(data.getPerCultArea());
                        Cell forestAreaCell = row.createCell(dataCellId++);//forestArea
                        forestAreaCell.setCellValue(data.getForestArea());
                        Cell fruitAreaCell = row.createCell(dataCellId++);//fruitArea
                        fruitAreaCell.setCellValue(data.getFruitArea());
                        Cell bambooAreaCell = row.createCell(dataCellId++);//bambooArea
                        bambooAreaCell.setCellValue(data.getBambooArea());
                        Cell landCycleCondCell = row.createCell(dataCellId);//landCycleCond
                        landCycleCondCell.setCellValue(data.getLandCycleCond());
                        Cell primaryIndValueCell = row.createCell(dataCellId);//primaryIndValue
                        primaryIndValueCell.setCellValue(data.getPrimaryIndValue());
                        Cell primaryIntPepCntCell = row.createCell(dataCellId);//primaryIntPepCnt
                        primaryIntPepCntCell.setCellValue(data.getPrimaryIntPepCnt());
                        Cell secondIndValueCell = row.createCell(dataCellId);//secondIndValue
                        secondIndValueCell.setCellValue(data.getSecondIndValue());
                        Cell secondIndPepCntCell = row.createCell(dataCellId);//secondIndPepCnt
                        secondIndPepCntCell.setCellValue(data.getSecondIndPepCnt());
                        Cell thirdIndValueCell = row.createCell(dataCellId);//thirdIndValue
                        thirdIndValueCell.setCellValue(data.getThirdIndValue());
                        Cell thirdIndPepCntCell = row.createCell(dataCellId);//thirdIndPepCnt
                        thirdIndPepCntCell.setCellValue(data.getThirdIndPepCnt());
                        Cell snackHouseholdsCell = row.createCell(dataCellId);//snackHouseholds
                        snackHouseholdsCell.setCellValue(data.getSnackHouseholds());
                        Cell snackPepCntCell = row.createCell(dataCellId);//snackPepCnt
                        snackPepCntCell.setCellValue(data.getSnackPepCnt());
                        Cell snackIncomeCell = row.createCell(dataCellId);//snackIncome
                        snackIncomeCell.setCellValue(data.getSnackIncome());
                        Cell coopPlantCell = row.createCell(dataCellId);//coopPlant
                        coopPlantCell.setCellValue(data.getCoopPlant());
                        Cell coopBreedCell = row.createCell(dataCellId);//coopBreed
                        coopBreedCell.setCellValue(data.getCoopBreed());
                        Cell coopAgroCell = row.createCell(dataCellId);//coopAgro
                        coopAgroCell.setCellValue(data.getCoopAgro());
                        Cell coopOtherCell = row.createCell(dataCellId);//coopOther
                        coopOtherCell.setCellValue(data.getCoopOther());
                        Cell totalIncomeCell = row.createCell(dataCellId);//totalIncome
                        totalIncomeCell.setCellValue(data.getTotalIncome());
                        Cell perFarmerIncomeCell = row.createCell(dataCellId);//perFarmerIncome
                        perFarmerIncomeCell.setCellValue(data.getPerFarmerIncome());
                        Cell villageOrgAreaCell = row.createCell(dataCellId);//villageOrgArea
                        villageOrgAreaCell.setCellValue(data.getVillageOrgArea());
                        Cell farmerFitWeapCell = row.createCell(dataCellId);//farmerFitWeap
                        farmerFitWeapCell.setCellValue(data.getFarmerFitWeap());
                        Cell farmerFitAreaCell = row.createCell(dataCellId);//farmerFitArea
                        farmerFitAreaCell.setCellValue(data.getFarmerFitArea());
                        Cell elderRoomAreaCell = row.createCell(dataCellId);//elderRoomArea
                        elderRoomAreaCell.setCellValue(data.getElderRoomArea());
                        Cell healthRoomAreaCell = row.createCell(dataCellId);//healthRoomArea
                        healthRoomAreaCell.setCellValue(data.getHealthRoomArea());
                        Cell healthRoomBedsCell = row.createCell(dataCellId);//healthRoomBeds
                        healthRoomBedsCell.setCellValue(data.getHealthRoomBeds());
                        Cell eduPotTeachersCell = row.createCell(dataCellId);//eduPotTeachers
                        eduPotTeachersCell.setCellValue(data.getEduPotTeachers());
                        Cell eduPotStudentsCell = row.createCell(dataCellId);//eduPotStudents
                        eduPotStudentsCell.setCellValue(data.getEduPotStudents());
                        Cell kidTeachersCell = row.createCell(dataCellId);//kidTeachers
                        kidTeachersCell.setCellValue(data.getKidTeachers());
                        Cell kidStudentsCell = row.createCell(dataCellId);//kidStudents
                        kidStudentsCell.setCellValue(data.getKidStudents());
                        Cell vilTotalIncomeCell = row.createCell(dataCellId);//vilTotalIncome
                        vilTotalIncomeCell.setCellValue(data.getVilTotalIncome());
                        Cell vilTotalAssetsCell = row.createCell(dataCellId);//vilTotalAssets
                        vilTotalAssetsCell.setCellValue(data.getVilTotalAssets());
                        Cell vilTotalDebtsCell = row.createCell(dataCellId);//vilTotalDebts
                        vilTotalDebtsCell.setCellValue(data.getVilTotalDebts());
                        Cell twoCommitsCntCell = row.createCell(dataCellId);//twoCommitsCnt
                        twoCommitsCntCell.setCellValue(data.getTwoCommitsCnt());
                        Cell vilRepresentCntCell = row.createCell(dataCellId);//vilRepresentCnt
                        vilRepresentCntCell.setCellValue(data.getVilRepresentCnt());
                        maxId = Math.max(maxId, data.getId());
                        totalRows++;
                    }
                    currentPage++;
                } while (currentPage <= totalPages && totalRows <= perSheetRows);
                if (currentPage > totalPages) {
                    break;
                }
            }
            out = new BufferedOutputStream(new FileOutputStream(file));
            wb.write(out);

            IOUtils.closeQuietly(out);
            long endTime = System.currentTimeMillis();
            Map<String, Object> context = Maps.newHashMap();
            context.put("seconds", (endTime - beginTime) / 1000);
            context.put("url", fileName.replace(contextRootPath, ""));
            ExportData exportData = new ExportData();
            //File.separator 不同系统的文件路径分隔符不一样
            exportData.setFilename(fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.length()));
            exportData.setFilepath(fileName);
            exportData.setExportcontent("村情信息");
            exportData.setExporttime(new Date());
            exportData.setSpendtime((endTime - beginTime) / 1000);
            exportData.setUrl("\\" + fileName.replace(contextRootPath, ""));
            exportData.setType("2");//村情信息excel 0
            Long fileLengthKB = file.length() / 1024;
            exportData.setFilesize(fileLengthKB > 1024 ? (fileLengthKB / 1024 + "MB") : (fileLengthKB + "KB"));
            exportDataDao.save(exportData);
        } catch (FileNotFoundException e) {
            LogUtils.ERROR_LOG.error(e.getMessage());
            IOUtils.closeQuietly(out);
        } catch (IOException e) {
            LogUtils.ERROR_LOG.error(e.getMessage());
        } finally {
            // 清除本工作簿备份在磁盘上的临时文件
            if (!(wb == null)) {
                wb.dispose();
            }
        }
    }

    @Override
    @Async
    public void exportExcel2007(String contextRootPath, Filters filtersClass) {

        int totalRows = 0; //统计总行数
        Long maxId = 0L;//当前查询的数据中最大的id 优化分页的
        //生成将要导出的excel文件名
        String fileName = generateFilename(contextRootPath, EXPORT_FILENAME_SUFFIX);
        //新建文件
        File file = new File(fileName);
        BufferedOutputStream out = null;
        SXSSFWorkbook wb = null;
        try {
            //记录导出起始时间
            long beginTime = System.currentTimeMillis();
            //新建SXSSFWorkbook（支持大数据量导出）
            wb = new SXSSFWorkbook(rowAccessWindowSize);
            //生成的临时文件将进行gzip压缩
            wb.setCompressTempFiles(true);
            while (true) {
                //创建sheet
                Sheet sheet = wb.createSheet();
                //创建标题行
                createHeaderRow(sheet);
                totalRows = 1;
                //---获取数据开始---
                List<Tvillage> tvillageDataExportList;
                String hqlToCount = "select count(*) from Tviilage";
                int totalElements = villageDao.count(hqlToCount).intValue();
                int totalPages = totalElements % pageSize == 0 ? totalElements / pageSize : totalElements / pageSize + 1;
                int currentPage = 1;
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("from Tvillage t where t.id>:maxId and ");
                //拼接HQL
                for (FilterRule filterRule : filtersClass.getRules()) {

                    //判断rules 中的操作 op
                    stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

                }
                hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
                hql = hql + " order by t.villageName asc";
                //都在这里面循环
                //将数据保存到sheet中
                do {
                    params.put("maxId", maxId.intValue());
                    tvillageDataExportList = villageDao.find(hql, params, 0, pageSize);
                    //循环操作数据
                    for (Tvillage data :   tvillageDataExportList) {
                        int dataCellId = 0;
                        Row row = sheet.createRow(totalRows);
                        //创建数据行，并设置每列的数据
                        Cell idCell = row.createCell(dataCellId++);//id
                        idCell.setCellValue(data.getId());
                        Cell villageNameCell = row.createCell(dataCellId++);//villageName
                        villageNameCell.setCellValue(data.getVillage());
                        Cell villagerTeamCell = row.createCell(dataCellId++);//villagerTeam
                        villagerTeamCell.setCellValue(data.getVillagerTeam());
                        Cell householdsCell = row.createCell(dataCellId++);//households
                        householdsCell.setCellValue(data.getHouseholds());
                        Cell totalPepCntCell = row.createCell(dataCellId++);//totalPepCnt
                        totalPepCntCell.setCellValue(data.getTotalPepCnt());
                        Cell totalPepManCell = row.createCell(dataCellId++);//totalPepMan
                        totalPepManCell.setCellValue(data.getTotalPepMan());
                        Cell totalPepWomanCell = row.createCell(dataCellId++);//totalPepWoman
                        totalPepWomanCell.setCellValue(data.getTotalPepWoman());
                        Cell belowEighteenCell = row.createCell(dataCellId++);//belowEighteen
                        belowEighteenCell.setCellValue(data.getBelowEighteen());
                        Cell belowSixtyCell = row.createCell(dataCellId++);//belowSixty
                        belowSixtyCell.setCellValue(data.getBelowSixty());
                        Cell aboveSixtyCell = row.createCell(dataCellId++);//aboveSixty
                        aboveSixtyCell.setCellValue(data.getAboveSixty());
                        Cell mainNationCell = row.createCell(dataCellId++);//mainNation
                        mainNationCell.setCellValue(data.getMainNation());
                        Cell nationPepsCell = row.createCell(dataCellId++);//nationPeps
                        nationPepsCell.setCellValue(data.getNationPeps());
                        Cell otherNationCell = row.createCell(dataCellId++);//otherNation
                        otherNationCell.setCellValue(data. getOtherNation());
                        Cell otherPepsCell = row.createCell(dataCellId++);//otherPeps
                        otherPepsCell.setCellValue(data.getOtherPeps());
                        Cell partyGroupCntCell = row.createCell(dataCellId++);//partyGroupCnt
                        partyGroupCntCell.setCellValue(data.getPartyGroupCnt());
                        Cell partyMemCntCell = row.createCell(dataCellId++);//partyMemCnt
                        partyMemCntCell.setCellValue(data.getPartyMemCnt());
                        Cell hometownRemainCell = row.createCell(dataCellId++);//hometownRemain
                        hometownRemainCell.setCellValue(data. getHometownRemain());
                        Cell fiveGuaranteesCell = row.createCell(dataCellId++);//fiveGuarantees
                        fiveGuaranteesCell.setCellValue(data.getFiveGuarantees());
                        Cell lowIncomeCell = row.createCell(dataCellId++);//lowIncome
                        lowIncomeCell.setCellValue(data. getLowIncome());
                        Cell poorFamilyCell = row.createCell(dataCellId++);//poorFamily
                        poorFamilyCell.setCellValue(data. getPoorFamily());
                        Cell otherFamilyCell = row.createCell(dataCellId++);//otherFamily
                        otherFamilyCell.setCellValue(data.getOtherFamily());
                        Cell totalCultAreaCell = row.createCell(dataCellId++);//totalCultArea
                        totalCultAreaCell.setCellValue(data.getTotalCultArea());
                        Cell perCultAreaCell = row.createCell(dataCellId++);//perCultArea
                        perCultAreaCell.setCellValue(data.getPerCultArea());
                        Cell forestAreaCell = row.createCell(dataCellId++);//forestArea
                        forestAreaCell.setCellValue(data.getForestArea());
                        Cell fruitAreaCell = row.createCell(dataCellId++);//fruitArea
                        fruitAreaCell.setCellValue(data.getFruitArea());
                        Cell bambooAreaCell = row.createCell(dataCellId++);//bambooArea
                        bambooAreaCell.setCellValue(data.getBambooArea());
                        Cell landCycleCondCell = row.createCell(dataCellId);//landCycleCond
                        landCycleCondCell.setCellValue(data.getLandCycleCond());
                        Cell primaryIndValueCell = row.createCell(dataCellId);//primaryIndValue
                        primaryIndValueCell.setCellValue(data.getPrimaryIndValue());
                        Cell primaryIntPepCntCell = row.createCell(dataCellId);//primaryIntPepCnt
                        primaryIntPepCntCell.setCellValue(data.getPrimaryIntPepCnt());
                        Cell secondIndValueCell = row.createCell(dataCellId);//secondIndValue
                        secondIndValueCell.setCellValue(data.getSecondIndValue());
                        Cell secondIndPepCntCell = row.createCell(dataCellId);//secondIndPepCnt
                        secondIndPepCntCell.setCellValue(data.getSecondIndPepCnt());
                        Cell thirdIndValueCell = row.createCell(dataCellId);//thirdIndValue
                        thirdIndValueCell.setCellValue(data.getThirdIndValue());
                        Cell thirdIndPepCntCell = row.createCell(dataCellId);//thirdIndPepCnt
                        thirdIndPepCntCell.setCellValue(data.getThirdIndPepCnt());
                        Cell snackHouseholdsCell = row.createCell(dataCellId);//snackHouseholds
                        snackHouseholdsCell.setCellValue(data.getSnackHouseholds());
                        Cell snackPepCntCell = row.createCell(dataCellId);//snackPepCnt
                        snackPepCntCell.setCellValue(data.getSnackPepCnt());
                        Cell snackIncomeCell = row.createCell(dataCellId);//snackIncome
                        snackIncomeCell.setCellValue(data.getSnackIncome());
                        Cell coopPlantCell = row.createCell(dataCellId);//coopPlant
                        coopPlantCell.setCellValue(data.getCoopPlant());
                        Cell coopBreedCell = row.createCell(dataCellId);//coopBreed
                        coopBreedCell.setCellValue(data.getCoopBreed());
                        Cell coopAgroCell = row.createCell(dataCellId);//coopAgro
                        coopAgroCell.setCellValue(data.getCoopAgro());
                        Cell coopOtherCell = row.createCell(dataCellId);//coopOther
                        coopOtherCell.setCellValue(data.getCoopOther());
                        Cell totalIncomeCell = row.createCell(dataCellId);//totalIncome
                        totalIncomeCell.setCellValue(data.getTotalIncome());
                        Cell perFarmerIncomeCell = row.createCell(dataCellId);//perFarmerIncome
                        perFarmerIncomeCell.setCellValue(data.getPerFarmerIncome());
                        Cell villageOrgAreaCell = row.createCell(dataCellId);//villageOrgArea
                        villageOrgAreaCell.setCellValue(data.getVillageOrgArea());
                        Cell farmerFitWeapCell = row.createCell(dataCellId);//farmerFitWeap
                        farmerFitWeapCell.setCellValue(data.getFarmerFitWeap());
                        Cell farmerFitAreaCell = row.createCell(dataCellId);//farmerFitArea
                        farmerFitAreaCell.setCellValue(data.getFarmerFitArea());
                        Cell elderRoomAreaCell = row.createCell(dataCellId);//elderRoomArea
                        elderRoomAreaCell.setCellValue(data.getElderRoomArea());
                        Cell healthRoomAreaCell = row.createCell(dataCellId);//healthRoomArea
                        healthRoomAreaCell.setCellValue(data.getHealthRoomArea());
                        Cell healthRoomBedsCell = row.createCell(dataCellId);//healthRoomBeds
                        healthRoomBedsCell.setCellValue(data.getHealthRoomBeds());
                        Cell eduPotTeachersCell = row.createCell(dataCellId);//eduPotTeachers
                        eduPotTeachersCell.setCellValue(data.getEduPotTeachers());
                        Cell eduPotStudentsCell = row.createCell(dataCellId);//eduPotStudents
                        eduPotStudentsCell.setCellValue(data.getEduPotStudents());
                        Cell kidTeachersCell = row.createCell(dataCellId);//kidTeachers
                        kidTeachersCell.setCellValue(data.getKidTeachers());
                        Cell kidStudentsCell = row.createCell(dataCellId);//kidStudents
                        kidStudentsCell.setCellValue(data.getKidStudents());
                        Cell vilTotalIncomeCell = row.createCell(dataCellId);//vilTotalIncome
                        vilTotalIncomeCell.setCellValue(data.getVilTotalIncome());
                        Cell vilTotalAssetsCell = row.createCell(dataCellId);//vilTotalAssets
                        vilTotalAssetsCell.setCellValue(data.getVilTotalAssets());
                        Cell vilTotalDebtsCell = row.createCell(dataCellId);//vilTotalDebts
                        vilTotalDebtsCell.setCellValue(data.getVilTotalDebts());
                        Cell twoCommitsCntCell = row.createCell(dataCellId);//twoCommitsCnt
                        twoCommitsCntCell.setCellValue(data.getTwoCommitsCnt());
                        Cell vilRepresentCntCell = row.createCell(dataCellId);//vilRepresentCnt
                        vilRepresentCntCell.setCellValue(data.getVilRepresentCnt());
                         maxId = Math.max(maxId, data.getId());
                        totalRows++;
                    }
                    currentPage++;
                } while (currentPage <= totalPages && totalRows <= perSheetRows);
                if (currentPage > totalPages) {
                    break;
                }
            }
            out = new BufferedOutputStream(new FileOutputStream(file));
            wb.write(out);

            IOUtils.closeQuietly(out);
            //记录导出结束时间
            long endTime = System.currentTimeMillis();
            //保存导出的excel文件相关信息到数据库
            Map<String, Object> context = Maps.newHashMap();
            context.put("seconds", (endTime - beginTime) / 1000);
            context.put("url", fileName.replace(contextRootPath, ""));
            ExportData exportData = new ExportData();
            //注：File.separator 不同系统的文件路径分隔符不一样
            exportData.setFilename(fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.length()));
            exportData.setFilepath(fileName);
            exportData.setExportcontent("村情信息");
            exportData.setExporttime(new Date());
            exportData.setSpendtime((endTime - beginTime) / 1000);
            exportData.setUrl("\\" + fileName.replace(contextRootPath, ""));
            exportData.setType("2");//农户信息excel 0
            Long fileLengthKB = file.length() / 1024;
            exportData.setFilesize(fileLengthKB > 1024 ? (fileLengthKB / 1024 + "MB") : (fileLengthKB + "KB"));
            exportDataDao.save(exportData);
        } catch (FileNotFoundException e) {
            LogUtils.ERROR_LOG.error(e.getMessage());
            IOUtils.closeQuietly(out);
        } catch (IOException e) {
            LogUtils.ERROR_LOG.error(e.getMessage());
        } finally {
            // 清除本工作簿备份在磁盘上的临时文件
            if (!(wb == null)) {
                wb.dispose();
            }
        }
    }

    /**
     * 生成要导出的文件名
     *
     * @param contextRootPath - 上下文根目录
     * @param extension       - 扩展名为xlsx（excel2007的格式）
     * @return - 文件名
     */
    private String generateFilename(final String contextRootPath, final String extension) {
        return generateFilename(contextRootPath, null, extension);
    }

    private String generateFilename(final String contextRootPath, Integer index, final String extension) {
        String path = FilenameUtils.concat(contextRootPath, storePath);
        path = FilenameUtils.concat(
                path,
                EXPORT_FILENAME_PREFIX + "_" +
                        DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS") + (index != null ? ("_" + index) : "") + "." + extension
        );
        File file = new File(path);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                boolean dirExisted = parentFile.mkdirs();
                LogUtils.logInfo("parentFile.mkdirs()?" + dirExisted);
            }
            return path;
        }
        return generateFilename(contextRootPath, extension);
    }

    /**
     * 新建首行：标题行
     *
     * @param sheet - 当前sheet
     * @return - headerRow 首行：标题行
     */
    private Row createHeaderRow(Sheet sheet) {
        //第一列起始序号为0
        int headerCellId = 0;//起始数据行ID
        //创建标题行
        Row headerRow = sheet.createRow(0);
        //创建对应的列标题（第一列headerCellId要记得++）
        Cell idHeaderCell = headerRow.createCell(headerCellId++);
        idHeaderCell.setCellValue("ID");//id
        Cell villageNameHeaderCell = headerRow.createCell(headerCellId++);
        villageNameHeaderCell.setCellValue("村名");//villageName
        Cell villagerTeamHeaderCell = headerRow.createCell(headerCellId++);
        villagerTeamHeaderCell.setCellValue("村民小组");//villagerTeam
        Cell householdsHeaderCell = headerRow.createCell(headerCellId++);
        householdsHeaderCell.setCellValue("户数");//households
        Cell totalPepCntHeaderCell = headerRow.createCell(headerCellId++);
        totalPepCntHeaderCell.setCellValue("总人口");//totalPepCnt
        Cell totalPepManHeaderCell = headerRow.createCell(headerCellId++);
        totalPepManHeaderCell.setCellValue("男的数量");//totalPepMan
        Cell totalPepWomanHeaderCell = headerRow.createCell(headerCellId++);
        totalPepWomanHeaderCell.setCellValue("女的数量");//totalPepWoman
        Cell belowEighteenHeaderCell = headerRow.createCell(headerCellId++);
        belowEighteenHeaderCell.setCellValue("18岁以下人数");//belowEighteen
        Cell belowSixtyHeaderCell = headerRow.createCell(headerCellId++);
        belowSixtyHeaderCell.setCellValue("19-59岁人数");//belowSixty
        Cell aboveSixtyHeaderCell = headerRow.createCell(headerCellId++);
        aboveSixtyHeaderCell.setCellValue("60以上人数");//aboveSixty
        Cell mainNationHeaderCell = headerRow.createCell(headerCellId++);
        mainNationHeaderCell.setCellValue("主要民族");//mainNation
        Cell nationPepsHeaderCell = headerRow.createCell(headerCellId++);
        nationPepsHeaderCell.setCellValue("主要民族人口");//nationPeps
        Cell otherNationHeaderCell = headerRow.createCell(headerCellId++);
        otherNationHeaderCell.setCellValue("其他民族");// otherNation
        Cell otherPepsHeaderCell = headerRow.createCell(headerCellId++);
        otherPepsHeaderCell.setCellValue("其他民族人口");//otherPeps
        Cell partyGroupCntHeaderCell = headerRow.createCell(headerCellId++);
        partyGroupCntHeaderCell.setCellValue("党小组数");//partyGroupCnt
        Cell partyMemCntHeaderCell = headerRow.createCell(headerCellId++);
        partyMemCntHeaderCell.setCellValue("党员人数");//partyMemCnt
        Cell hometownRemainHeaderCell = headerRow.createCell(headerCellId++);
        hometownRemainHeaderCell.setCellValue("留守家庭人数");//hometownRemain
        Cell fiveGuaranteesHeaderCell = headerRow.createCell(headerCellId++);
        fiveGuaranteesHeaderCell.setCellValue("五保户人数");//fiveGuarantees
        Cell lowIncomeHeaderCell = headerRow.createCell(headerCellId++);
        lowIncomeHeaderCell.setCellValue("低保户人数");//lowIncome
        Cell poorFamilyHeaderCell = headerRow.createCell(headerCellId++);
        poorFamilyHeaderCell.setCellValue("困难家庭人数");//poorFamily
        Cell otherFamilyHeaderCell = headerRow.createCell(headerCellId++);
        otherFamilyHeaderCell.setCellValue("其他人数");//otherFamily
        Cell totalCultAreaHeaderCell = headerRow.createCell(headerCellId++);
        totalCultAreaHeaderCell.setCellValue("耕地面积");//totalCultArea
        Cell perCultAreaHeaderCell = headerRow.createCell(headerCellId++);
        perCultAreaHeaderCell.setCellValue("人均耕地面积");//perCultArea
        Cell forestAreaHeaderCell = headerRow.createCell(headerCellId++);
        forestAreaHeaderCell.setCellValue("林地面积");//forestArea
        Cell fruitAreaHeaderCell = headerRow.createCell(headerCellId++);
        fruitAreaHeaderCell.setCellValue("茶果面积");//fruitArea
        Cell bambooAreaHeaderCell = headerRow.createCell(headerCellId++);
        bambooAreaHeaderCell.setCellValue("毛竹面积");//bambooArea
        Cell landCycleCondHeaderCell = headerRow.createCell(headerCellId++);
        landCycleCondHeaderCell.setCellValue("土地流转情况面积");//landCycleCond
        Cell primaryIndValueHeaderCell = headerRow.createCell(headerCellId++);
        primaryIndValueHeaderCell.setCellValue("第一产业产值");//primaryIndValue
        Cell primaryIntPepCntHeaderCell = headerRow.createCell(headerCellId++);
        primaryIntPepCntHeaderCell.setCellValue("第一产业从业人员");//primaryIntPepCnt
        Cell secondIndValueHeaderCell = headerRow.createCell(headerCellId++);
        secondIndValueHeaderCell.setCellValue("第二产业产值");//secondIndValue
        Cell secondIndPepCntHeaderCell = headerRow.createCell(headerCellId++);
        secondIndPepCntHeaderCell.setCellValue("第二产业从业人员");//secondIndPepCnt
        Cell thirdIndValueHeaderCell = headerRow.createCell(headerCellId);
        thirdIndValueHeaderCell.setCellValue("第三产业产值");//thirdIndValue
        Cell thirdIndPepCntHeaderCell = headerRow.createCell(headerCellId);
        thirdIndPepCntHeaderCell.setCellValue("第三产业从业人员");//thirdIndPepCnt
        Cell snackHouseholdsHeaderCell = headerRow.createCell(headerCellId);
        snackHouseholdsHeaderCell.setCellValue("外出经营小吃户数");//snackHouseholds
        Cell snackPepCntHeaderCell = headerRow.createCell(headerCellId);
        snackPepCntHeaderCell.setCellValue("外出经营小吃人数");//snackPepCnt
        Cell snackIncomeHeaderCell = headerRow.createCell(headerCellId);
        snackIncomeHeaderCell.setCellValue("外出经营小吃总收入");//snackIncome
        Cell coopPlantHeaderCell = headerRow.createCell(headerCellId);
        coopPlantHeaderCell.setCellValue("合作社种植个数");//coopPlant
        Cell coopBreedHeaderCell = headerRow.createCell(headerCellId);
        coopBreedHeaderCell.setCellValue("合作社养殖个数");//coopBreed
        Cell coopAgroHeaderCell = headerRow.createCell(headerCellId);
        coopAgroHeaderCell.setCellValue("合作社农机个数");//coopAgro
        Cell coopOtherHeaderCell = headerRow.createCell(headerCellId);
        coopOtherHeaderCell.setCellValue("合作社其他个数");//coopOther
        Cell totalIncomeHeaderCell = headerRow.createCell(headerCellId);
        totalIncomeHeaderCell.setCellValue("总收入");//totalIncome
        Cell perFarmerIncomeHeaderCell = headerRow.createCell(headerCellId);
        perFarmerIncomeHeaderCell.setCellValue("农民人均纯收入");//perFarmerIncome
        Cell villageOrgAreaHeaderCell = headerRow.createCell(headerCellId);
        villageOrgAreaHeaderCell.setCellValue("村级组织活动场所面积");//villageOrgArea
        Cell farmerFitWeapHeaderCell = headerRow.createCell(headerCellId);
        farmerFitWeapHeaderCell.setCellValue("农民户外健身场地器械台数");//farmerFitWeap
        Cell farmerFitAreaHeaderCell = headerRow.createCell(headerCellId);
        farmerFitAreaHeaderCell.setCellValue("农民户外健身场地占地面积");//farmerFitArea
        Cell elderRoomAreaHeaderCell = headerRow.createCell(headerCellId);
        elderRoomAreaHeaderCell.setCellValue("老年人活动室建筑面积");//elderRoomArea
        Cell healthRoomAreaHeaderCell = headerRow.createCell(headerCellId);
        healthRoomAreaHeaderCell.setCellValue("村卫生室建筑面积");//healthRoomArea
        Cell healthRoomBedsHeaderCell = headerRow.createCell(headerCellId);
        healthRoomBedsHeaderCell.setCellValue("村卫生室床位数");//healthRoomBeds
        Cell eduPotTeachersHeaderCell = headerRow.createCell(headerCellId);
        eduPotTeachersHeaderCell.setCellValue("完小教师数");//eduPotTeachers
        Cell eduPotStudentsHeaderCell = headerRow.createCell(headerCellId);
        eduPotStudentsHeaderCell.setCellValue("完小学生数");//eduPotStudents
        Cell kidTeachersHeaderCell = headerRow.createCell(headerCellId);
        kidTeachersHeaderCell.setCellValue("幼儿园教师数");//kidTeachers
        Cell kidStudentsHeaderCell = headerRow.createCell(headerCellId);
        kidStudentsHeaderCell.setCellValue("幼儿园学生数");//kidStudents
        Cell vilTotalIncomeHeaderCell = headerRow.createCell(headerCellId);
        vilTotalIncomeHeaderCell.setCellValue("村集体收入");//vilTotalIncome
        Cell vilTotalAssetsHeaderCell = headerRow.createCell(headerCellId);
        vilTotalAssetsHeaderCell.setCellValue("年末村集体资产总额");//vilTotalAssets
        Cell vilTotalDebtsHeaderCell = headerRow.createCell(headerCellId);
        vilTotalDebtsHeaderCell.setCellValue("年末村集体负债总额");//vilTotalDebts
        Cell twoCommitsCntHeaderCell = headerRow.createCell(headerCellId);
        twoCommitsCntHeaderCell.setCellValue("村两委干部人数");//twoCommitsCnt
        Cell vilRepresentCntHeaderCell = headerRow.createCell(headerCellId);
        vilRepresentCntHeaderCell.setCellValue("村民代表数");//vilRepresentCnt
        //返回headerRow
        return headerRow;
    }

}
