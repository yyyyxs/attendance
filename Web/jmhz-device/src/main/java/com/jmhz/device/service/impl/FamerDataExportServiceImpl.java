package com.jmhz.device.service.impl;

import com.google.common.collect.Maps;
import com.jmhz.device.dao.FarmerDaoI;
import com.jmhz.device.model.Tfarmer;
import com.jmhz.device.service.FamerDataExportServiceI;
import com.jmhz.device.sys.dao.IExportDataDao;
import com.jmhz.device.sys.entity.ExportData;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.util.LogUtils;
import com.jmhz.device.util.SearchUtil;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
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

/**
 * Created by ChenXin on 2014/6/16.
 */
@Service
public class FamerDataExportServiceImpl implements FamerDataExportServiceI {

    @Autowired
    private FarmerDaoI farmerDao;

    @Autowired
    private IExportDataDao exportDataDao;

    private static final String storePath = "export/excel";
    private static final String EXPORT_FILENAME_PREFIX = "GaoQiao_Farmer";
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
    public void exportExcel2007(final String contextRootPath, User user) {
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
                List<Tfarmer> tfarmerDataExportList;
                String hqlToCount = "select count(*) from Tfarmer t";
                int totalElements = farmerDao.count(hqlToCount).intValue();
                int totalPages = totalElements % pageSize == 0 ? totalElements / pageSize : totalElements / pageSize + 1;
                int currentPage = 1;
                Map<String, Object> params = new HashMap<String, Object>();

                String hql = "from Tfarmer t where t.id>:maxId order by t.householdername asc";
                do {//都在这里面循环
                    params.put("maxId", maxId.intValue());
                    tfarmerDataExportList = farmerDao.find(hql, params, 0, pageSize);
                    for (Tfarmer data : tfarmerDataExportList) {
                        int dataCellId = 0;
                        Row row = sheet.createRow(totalRows);
                        Cell idCell = row.createCell(dataCellId++);//id
                        idCell.setCellValue(data.getId());
                        Cell cityCell = row.createCell(dataCellId++);//city
                        cityCell.setCellValue(data.getCity());
                        Cell townCell = row.createCell(dataCellId++);//town
                        townCell.setCellValue(data.getTown());
                        Cell villageCell = row.createCell(dataCellId++);//village
                        villageCell.setCellValue(data.getVillage());
                        Cell gridCell = row.createCell(dataCellId++);//grid
                        gridCell.setCellValue(data.getGrid());
                        Cell gridchargerCell = row.createCell(dataCellId++);//gridcharger
                        gridchargerCell.setCellValue(data.getGridcharger());
                        Cell familyCell = row.createCell(dataCellId++);//family
                        familyCell.setCellValue(data.getFamily());
//                        Cell visittimeCell = row.createCell(dataCellId++);//visittime
//                        visittimeCell.setCellValue(data.getVisittime());
                        Cell householdernameCell = row.createCell(dataCellId++);//householdername
                        householdernameCell.setCellValue(data.getHouseholdername());
                        Cell genderCell = row.createCell(dataCellId++);//gender
                        //genderCell.setCellValue(data.getGender());
                        String gender = data.getGender();
                        if (("0").equals(gender)) {
                            genderCell.setCellValue("男");
                        } else if (("1").equals(gender)) {
                            genderCell.setCellValue("女");
                        }
                        Cell representationCell = row.createCell(dataCellId++);//representation
                        if (data.getRepresentation() != null) {
                            String[] strings = data.getRepresentation().split(",");
                            StringBuilder representation = new StringBuilder("");
                            for (String s : strings) {
                                if (("0").equals(s)) {
                                    representation.append("村民代表,");
                                }
                                if (("1").equals(s)) {
                                    representation.append("村民小组长,");
                                }
                                if (("2").equals(s)) {
                                    representation.append("现任村“两委”干部（");
                                }
                                if (("3").equals(s)) {
                                    representation.append("村主干）,");
                                }
                                if (("4").equals(s)) {
                                    representation.append("离任村“两委”干部（");
                                }
                                if (("5").equals(s)) {
                                    representation.append("村主干）,");
                                }
                            }
                            representationCell.setCellValue(representation.toString().substring(0, representation.toString().length() - 1));
                        } else {
                            representationCell.setCellValue("无");
                        }

                        Cell birthdayCell = row.createCell(dataCellId++);//birthday
                        birthdayCell.setCellValue(data.getBirthday());
                        Cell politicalstatusCell = row.createCell(dataCellId++);//politicalstatus
                        // politicalstatusCell.setCellValue(data.getPoliticalstatus());
                        String politicalstatus = data.getPoliticalstatus();
                        if (("0").equals(politicalstatus)) {
                            politicalstatusCell.setCellValue("群众");
                        } else if (("1").equals(politicalstatus)) {
                            politicalstatusCell.setCellValue("共青团员");
                        } else if (("2").equals(politicalstatus)) {
                            politicalstatusCell.setCellValue("中共党员");
                        }
                        Cell familypopulationCell = row.createCell(dataCellId++);//familypopulation
                        familypopulationCell.setCellValue(data.getFamilypopulation());
                        Cell contactnumberCell = row.createCell(dataCellId++);//contactnumber
                        contactnumberCell.setCellValue(data.getContactnumber());
                        Cell plantingprojectCell = row.createCell(dataCellId++);//plantingproject
                        plantingprojectCell.setCellValue(data.getPlantingproject());
                        Cell plantingscaleCell = row.createCell(dataCellId++);//plantingscale
                        plantingscaleCell.setCellValue(data.getPlantingscale());
                        Cell farmingprojectCell = row.createCell(dataCellId++);//farmingproject
                        farmingprojectCell.setCellValue(data.getFarmingproject());
                        Cell farmingscaleCell = row.createCell(dataCellId++);//farmingscale
                        farmingscaleCell.setCellValue(data.getFarmingscale());
                        Cell scaleunitCell = row.createCell(dataCellId++);//scaleunit
                        scaleunitCell.setCellValue(data.getScaleunit());
                        Cell snackprovinceCell = row.createCell(dataCellId++);//snackprovince
                        snackprovinceCell.setCellValue(data.getSnackprovince());
                        Cell snackcityCell = row.createCell(dataCellId++);//snackcity
                        snackcityCell.setCellValue(data.getSnackcity());
                        Cell snackareaCell = row.createCell(dataCellId++);//snackarea
                        snackareaCell.setCellValue(data.getSnackarea());
                        Cell snackscaleCell = row.createCell(dataCellId++);//snackscale
                        snackscaleCell.setCellValue(data.getSnackscale());
                        Cell workprofessionCell = row.createCell(dataCellId++);//workprofession
                        workprofessionCell.setCellValue(data.getWorkprofession());
                        Cell workprovinceCell = row.createCell(dataCellId++);//workprovince
                        workprovinceCell.setCellValue(data.getWorkprovince());
                        Cell workcityCell = row.createCell(dataCellId++);//workcity
                        workcityCell.setCellValue(data.getWorkcity());
                        Cell workareaCell = row.createCell(dataCellId++);//workarea
                        workareaCell.setCellValue(data.getWorkarea());
                        Cell foundednameCell = row.createCell(dataCellId++);//foundedname
                        foundednameCell.setCellValue(data.getFoundedname());
                        Cell foundedvalueCell = row.createCell(dataCellId++);//foundedvalue
                        foundedvalueCell.setCellValue(data.getFoundedvalue());
                        Cell othersofproductionCell = row.createCell(dataCellId++);//othersofproduction
                        othersofproductionCell.setCellValue(data.getOthersofproduction());
                        Cell housingsituationCell = row.createCell(dataCellId++);//housingsituation
                        if (data.getHousingsituation() != null) {
                            String[] housingsituations = data.getHousingsituation().split(",");
                            StringBuilder housingsituation = new StringBuilder("");
                            for (String s : housingsituations) {
                                if (("0").equals(s)) {
                                    housingsituation.append("钢混,");
                                }
                                if (("1").equals(s)) {
                                    housingsituation.append("砖混,");
                                }
                                if (("2").equals(s)) {
                                    housingsituation.append("砖木,");
                                }
                                if (("3").equals(s)) {
                                    housingsituation.append("木质,");
                                }
                                if (("4").equals(s)) {
                                    housingsituation.append("其他,");
                                }
                            }
                            housingsituationCell.setCellValue(housingsituation.toString().substring(0, housingsituation.toString().length() - 1));
                        } else {
                            housingsituationCell.setCellValue("无");
                        }
                        Cell otherhousingsituationCell = row.createCell(dataCellId++);//otherhousingsituation
                        otherhousingsituationCell.setCellValue(data.getOtherhousingsituation());
                        Cell specialfamilyCell = row.createCell(dataCellId++);//specialfamily
                        if (data.getHousingsituation() != null) {
                            String[] specialfamilys = data.getHousingsituation().split(",");
                            StringBuilder specialfamily = new StringBuilder("");
                            for (String s : specialfamilys) {
                                if (("0").equals(s)) {
                                    specialfamily.append("留守家庭,");
                                }
                                if (("1").equals(s)) {
                                    specialfamily.append("五保户,");
                                }
                                if (("2").equals(s)) {
                                    specialfamily.append("低保户,");
                                }
                                if (("3").equals(s)) {
                                    specialfamily.append("困难家庭,");
                                }
                                if (("4").equals(s)) {
                                    specialfamily.append("其他,");
                                }
                            }
                            specialfamilyCell.setCellValue(specialfamily.toString().substring(0, specialfamily.toString().length() - 1));
                        } else {
                            specialfamilyCell.setCellValue("无");
                        }
                        Cell otherspecialfamilyCell = row.createCell(dataCellId);//otherspecialfamily
                        otherspecialfamilyCell.setCellValue(data.getOtherspecialfamily());

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
            exportData.setCity(user.getCity());
            exportData.setTown(user.getTown());
            //File.separator 不同系统的文件路径分隔符不一样
            exportData.setFilename(fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.length()));
            exportData.setFilepath(fileName);
            exportData.setExportcontent("农户信息");
            exportData.setExporttime(new Date());
            exportData.setSpendtime((endTime - beginTime) / 1000);
            exportData.setUrl("\\" + fileName.replace(contextRootPath, ""));
            exportData.setType("1");//农户信息excel 0
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
    public void exportExcel2007(String contextRootPath, Filters filtersClass, User user) {

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
                List<Tfarmer> tfarmerDataExportList;
                String hqlToCount = "select count(*) from Tfarmer t";
                int totalElements = farmerDao.count(hqlToCount).intValue();
                int totalPages = totalElements % pageSize == 0 ? totalElements / pageSize : totalElements / pageSize + 1;
                int currentPage = 1;
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("from Tfarmer t where t.id>:maxId and ");
                //拼接HQL
                for (FilterRule filterRule : filtersClass.getRules()) {

                    //判断rules 中的操作 op
                    stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

                }
                hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
                hql = hql + " order by t.householdername asc";
                //都在这里面循环
                //将数据保存到sheet中
                do {
                    params.put("maxId", maxId.intValue());
                    tfarmerDataExportList = farmerDao.find(hql, params, 0, pageSize);
                    //循环操作数据
                    for (Tfarmer data : tfarmerDataExportList) {
                        int dataCellId = 0;
                        Row row = sheet.createRow(totalRows);
                        //创建数据行，并设置每列的数据
                        Cell idCell = row.createCell(dataCellId++);//id
                        idCell.setCellValue(data.getId());
                        Cell cityCell = row.createCell(dataCellId++);//city
                        cityCell.setCellValue(data.getCity());
                        Cell townCell = row.createCell(dataCellId++);//town
                        townCell.setCellValue(data.getTown());
                        Cell villageCell = row.createCell(dataCellId++);//village
                        villageCell.setCellValue(data.getVillage());
                        Cell gridCell = row.createCell(dataCellId++);//grid
                        gridCell.setCellValue(data.getGrid());
                        Cell gridchargerCell = row.createCell(dataCellId++);//gridcharger
                        gridchargerCell.setCellValue(data.getGridcharger());
                        Cell familyCell = row.createCell(dataCellId++);//family
                        familyCell.setCellValue(data.getFamily());
//                        Cell visittimeCell = row.createCell(dataCellId++);//visittime
//                        visittimeCell.setCellValue(data.getVisittime());
                        Cell householdernameCell = row.createCell(dataCellId++);//householdername
                        householdernameCell.setCellValue(data.getHouseholdername());
                        Cell genderCell = row.createCell(dataCellId++);//gender
                        //genderCell.setCellValue(data.getGender());
                        String gender = data.getGender();
                        if (("0").equals(gender)) {
                            genderCell.setCellValue("男");
                        } else if (("1").equals(gender)) {
                            genderCell.setCellValue("女");
                        }
                        Cell representationCell = row.createCell(dataCellId++);//representation
                        if (data.getRepresentation() != null) {
                            String[] strings = data.getRepresentation().split(",");
                            StringBuilder representation = new StringBuilder("");
                            for (String s : strings) {
                                if (("0").equals(s)) {
                                    representation.append("村民代表,");
                                }
                                if (("1").equals(s)) {
                                    representation.append("村民小组长,");
                                }
                                if (("2").equals(s)) {
                                    representation.append("现任村“两委”干部（");
                                }
                                if (("3").equals(s)) {
                                    representation.append("村主干）,");
                                }
                                if (("4").equals(s)) {
                                    representation.append("离任村“两委”干部（");
                                }
                                if (("5").equals(s)) {
                                    representation.append("村主干）,");
                                }
                            }
                            representationCell.setCellValue(representation.toString().substring(0, representation.toString().length() - 1));
                        } else {
                            representationCell.setCellValue("无");
                        }
                        Cell birthdayCell = row.createCell(dataCellId++);//birthday
                        birthdayCell.setCellValue(data.getBirthday());
                        Cell politicalstatusCell = row.createCell(dataCellId++);//politicalstatus
                        //politicalstatusCell.setCellValue(data.getPoliticalstatus());
                        String politicalstatus = data.getPoliticalstatus();
                        if (("0").equals(politicalstatus)) {
                            politicalstatusCell.setCellValue("群众");
                        } else if (("1").equals(politicalstatus)) {
                            politicalstatusCell.setCellValue("共青团员");
                        } else if (("2").equals(politicalstatus)) {
                            politicalstatusCell.setCellValue("中共党员");
                        }

                        Cell familypopulationCell = row.createCell(dataCellId++);//familypopulation
                        familypopulationCell.setCellValue(data.getFamilypopulation());
                        Cell contactnumberCell = row.createCell(dataCellId++);//contactnumber
                        contactnumberCell.setCellValue(data.getContactnumber());
                        Cell plantingprojectCell = row.createCell(dataCellId++);//plantingproject
                        plantingprojectCell.setCellValue(data.getPlantingproject());
                        Cell plantingscaleCell = row.createCell(dataCellId++);//plantingscale
                        plantingscaleCell.setCellValue(data.getPlantingscale());
                        Cell farmingprojectCell = row.createCell(dataCellId++);//farmingproject
                        farmingprojectCell.setCellValue(data.getFarmingproject());
                        Cell farmingscaleCell = row.createCell(dataCellId++);//farmingscale
                        farmingscaleCell.setCellValue(data.getFarmingscale());
                        Cell scaleunitCell = row.createCell(dataCellId++);//scaleunit
                        scaleunitCell.setCellValue(data.getScaleunit());
                        Cell snackprovinceCell = row.createCell(dataCellId++);//snackprovince
                        snackprovinceCell.setCellValue(data.getSnackprovince());
                        Cell snackcityCell = row.createCell(dataCellId++);//snackcity
                        snackcityCell.setCellValue(data.getSnackcity());
                        Cell snackareaCell = row.createCell(dataCellId++);//snackarea
                        snackareaCell.setCellValue(data.getSnackarea());
                        Cell snackscaleCell = row.createCell(dataCellId++);//snackscale
                        snackscaleCell.setCellValue(data.getSnackscale());
                        Cell workprofessionCell = row.createCell(dataCellId++);//workprofession
                        workprofessionCell.setCellValue(data.getWorkprofession());
                        Cell workprovinceCell = row.createCell(dataCellId++);//workprovince
                        workprovinceCell.setCellValue(data.getWorkprovince());
                        Cell workcityCell = row.createCell(dataCellId++);//workcity
                        workcityCell.setCellValue(data.getWorkcity());
                        Cell workareaCell = row.createCell(dataCellId++);//workarea
                        workareaCell.setCellValue(data.getWorkarea());
                        Cell foundednameCell = row.createCell(dataCellId++);//foundedname
                        foundednameCell.setCellValue(data.getFoundedname());
                        Cell foundedvalueCell = row.createCell(dataCellId++);//foundedvalue
                        foundedvalueCell.setCellValue(data.getFoundedvalue());
                        Cell othersofproductionCell = row.createCell(dataCellId++);//othersofproduction
                        othersofproductionCell.setCellValue(data.getOthersofproduction());
                        Cell housingsituationCell = row.createCell(dataCellId++);//housingsituation
                        if (data.getHousingsituation() != null) {
                            String[] housingsituations = data.getHousingsituation().split(",");
                            StringBuilder housingsituation = new StringBuilder("");
                            for (String s : housingsituations) {
                                if (("0").equals(s)) {
                                    housingsituation.append("钢混,");
                                }
                                if (("1").equals(s)) {
                                    housingsituation.append("砖混,");
                                }
                                if (("2").equals(s)) {
                                    housingsituation.append("砖木,");
                                }
                                if (("3").equals(s)) {
                                    housingsituation.append("木质,");
                                }
                                if (("4").equals(s)) {
                                    housingsituation.append("其他,");
                                }
                            }
                            housingsituationCell.setCellValue(housingsituation.toString().substring(0, housingsituation.toString().length() - 1));
                        } else {
                            housingsituationCell.setCellValue("无");
                        }
                        Cell otherhousingsituationCell = row.createCell(dataCellId++);//otherhousingsituation
                        otherhousingsituationCell.setCellValue(data.getOtherhousingsituation());
                        Cell specialfamilyCell = row.createCell(dataCellId++);//specialfamily
                        if (data.getHousingsituation() != null) {
                            String[] specialfamilys = data.getHousingsituation().split(",");
                            StringBuilder specialfamily = new StringBuilder("");
                            for (String s : specialfamilys) {
                                if (("0").equals(s)) {
                                    specialfamily.append("留守家庭,");
                                }
                                if (("1").equals(s)) {
                                    specialfamily.append("五保户,");
                                }
                                if (("2").equals(s)) {
                                    specialfamily.append("低保户,");
                                }
                                if (("3").equals(s)) {
                                    specialfamily.append("困难家庭,");
                                }
                                if (("4").equals(s)) {
                                    specialfamily.append("其他,");
                                }
                            }
                            specialfamilyCell.setCellValue(specialfamily.toString().substring(0, specialfamily.toString().length() - 1));
                        } else {
                            specialfamilyCell.setCellValue("无");
                        }
                        Cell otherspecialfamilyCell = row.createCell(dataCellId++);//otherspecialfamily
                        otherspecialfamilyCell.setCellValue(data.getOtherspecialfamily());


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
            exportData.setCity(user.getCity());
            exportData.setTown(user.getTown());
            //注：File.separator 不同系统的文件路径分隔符不一样
            exportData.setFilename(fileName.substring(fileName.lastIndexOf(File.separator) + 1, fileName.length()));
            exportData.setFilepath(fileName);
            exportData.setExportcontent("农户信息");
            exportData.setExporttime(new Date());
            exportData.setSpendtime((endTime - beginTime) / 1000);
            exportData.setUrl("\\" + fileName.replace(contextRootPath, ""));
            exportData.setType("1");//农户信息excel 0
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
        Cell cityHeaderCell = headerRow.createCell(headerCellId++);
        cityHeaderCell.setCellValue("市/县");//city
        Cell townHeaderCell = headerRow.createCell(headerCellId++);
        townHeaderCell.setCellValue("镇");//town
        Cell villageHeaderCell = headerRow.createCell(headerCellId++);
        villageHeaderCell.setCellValue("村");//village
        Cell gridHeaderCell = headerRow.createCell(headerCellId++);
        gridHeaderCell.setCellValue("网格");//grid
        Cell gridchargerHeaderCell = headerRow.createCell(headerCellId++);
        gridchargerHeaderCell.setCellValue("网格责任人");//gridcharger
        Cell familyHeaderCell = headerRow.createCell(headerCellId++);
        familyHeaderCell.setCellValue("户");//family
//        Cell visittimeHeaderCell = headerRow.createCell(headerCellId++);
//        visittimeHeaderCell.setCellValue("走访时间");//visittime
        Cell householdernameHeaderCell = headerRow.createCell(headerCellId++);
        householdernameHeaderCell.setCellValue("户主姓名");//householdername
        Cell genderHeaderCell = headerRow.createCell(headerCellId++);
        genderHeaderCell.setCellValue("性 别");//gender
        Cell representationHeaderCell = headerRow.createCell(headerCellId++);
        representationHeaderCell.setCellValue("任职情况");//representation
        Cell birthdayHeaderCell = headerRow.createCell(headerCellId++);
        birthdayHeaderCell.setCellValue("出生年月");//birthday
        Cell politicalstatusHeaderCell = headerRow.createCell(headerCellId++);
        politicalstatusHeaderCell.setCellValue("政治面貌");//politicalstatus
        Cell familypopulationHeaderCell = headerRow.createCell(headerCellId++);
        familypopulationHeaderCell.setCellValue("家庭人口");//familypopulation
        Cell contactnumberHeaderCell = headerRow.createCell(headerCellId++);
        contactnumberHeaderCell.setCellValue("联系电话");//contactnumber
        Cell plantingprojectHeaderCell = headerRow.createCell(headerCellId++);
        plantingprojectHeaderCell.setCellValue("种植业项目");//plantingproject
        Cell plantingscaleHeaderCell = headerRow.createCell(headerCellId++);
        plantingscaleHeaderCell.setCellValue("种植业规模");//plantingscale
        Cell farmingprojectHeaderCell = headerRow.createCell(headerCellId++);
        farmingprojectHeaderCell.setCellValue("养殖业项目");//farmingproject
        Cell farmingscaleHeaderCell = headerRow.createCell(headerCellId++);
        farmingscaleHeaderCell.setCellValue("养殖业规模");//farmingscale
        Cell scaleunitHeaderCell = headerRow.createCell(headerCellId++);
        scaleunitHeaderCell.setCellValue("养殖业 规模单位");//scaleunit
        Cell snackprovinceHeaderCell = headerRow.createCell(headerCellId++);
        snackprovinceHeaderCell.setCellValue("小吃业省份");//snackprovince
        Cell snackcityHeaderCell = headerRow.createCell(headerCellId++);
        snackcityHeaderCell.setCellValue("小吃业市");//snackcity
        Cell snackareaHeaderCell = headerRow.createCell(headerCellId++);
        snackareaHeaderCell.setCellValue("小吃业地区");//snackarea
        Cell snackscaleHeaderCell = headerRow.createCell(headerCellId++);
        snackscaleHeaderCell.setCellValue("小吃业月营业收入约");//snackscale
        Cell workprofessionHeaderCell = headerRow.createCell(headerCellId++);
        workprofessionHeaderCell.setCellValue("务工职业");//workprofession
        Cell workprovinceHeaderCell = headerRow.createCell(headerCellId++);
        workprovinceHeaderCell.setCellValue("务工所在省份");//workprovince
        Cell workcityHeaderCell = headerRow.createCell(headerCellId++);
        workcityHeaderCell.setCellValue("务工所在市");//workcity
        Cell workareaHeaderCell = headerRow.createCell(headerCellId++);
        workareaHeaderCell.setCellValue("务工所在地区");//workarea
        Cell foundednameHeaderCell = headerRow.createCell(headerCellId++);
        foundednameHeaderCell.setCellValue("创办实体实体名称");//foundedname
        Cell foundedvalueHeaderCell = headerRow.createCell(headerCellId++);
        foundedvalueHeaderCell.setCellValue("创办实体年产值");//foundedvalue
        Cell othersofproductionHeaderCell = headerRow.createCell(headerCellId++);
        othersofproductionHeaderCell.setCellValue("其他");//othersofproduction
        Cell housingsituationHeaderCell = headerRow.createCell(headerCellId++);
        housingsituationHeaderCell.setCellValue("住房情况");//housingsituation
        Cell otherhousingsituationHeaderCell = headerRow.createCell(headerCellId++);
        otherhousingsituationHeaderCell.setCellValue("住房其他");//otherhousingsituation
        Cell specialfamilyHeaderCell = headerRow.createCell(headerCellId++);
        specialfamilyHeaderCell.setCellValue("特殊家庭");//specialfamily
        Cell otherspecialfamilyHeaderCell = headerRow.createCell(headerCellId);
        otherspecialfamilyHeaderCell.setCellValue("特殊家庭其他");//otherspecialfamily


        //返回headerRow
        return headerRow;
    }

}
