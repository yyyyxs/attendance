package com.jmhz.device.service.impl;

import com.google.common.collect.Maps;
import com.jmhz.device.dao.AppealDaoI;
import com.jmhz.device.model.Tappeal;
import com.jmhz.device.pageModel.FilterRule;
import com.jmhz.device.pageModel.Filters;
import com.jmhz.device.service.AppealDataExportServiceI;
import com.jmhz.device.sys.dao.IExportDataDao;
import com.jmhz.device.sys.entity.ExportData;
import com.jmhz.device.sys.entity.User;
import com.jmhz.device.util.LogUtils;
import com.jmhz.device.util.SearchUtil;
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
 * <p> 导出诉求列表为Excel
 * <p> Created at 2014/5/18 10:40
 *
 * @author Charkey
 * @version 1.0
 */
@Service
public class AppealDataExportServiceImpl implements AppealDataExportServiceI {

    @Autowired
    private AppealDaoI appealDao;

    @Autowired
    private IExportDataDao exportDataDao;

    private static final String storePath = "export/excel";
    private static final String EXPORT_FILENAME_PREFIX = "GaoQiao_Appeal";
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
                List<Tappeal> appealDataExportList;
                String hqlToCount = "select count(*) from Tappeal t";
                int totalElements = appealDao.count(hqlToCount).intValue();
                int totalPages = totalElements % pageSize == 0 ? totalElements / pageSize : totalElements / pageSize + 1;
                int currentPage = 1;
                Map<String, Object> params = new HashMap<String, Object>();

                String hql = "from Tappeal t where t.id>:maxId order by t.village asc, t.createdate desc";
                do {//都在这里面循环
                    params.put("maxId", maxId.intValue());
                    appealDataExportList = appealDao.find(hql, params, 0, pageSize);
                    for (Tappeal data : appealDataExportList) {
                        int dataCellId = 0;
                        Row row = sheet.createRow(totalRows);
                        Cell idCell = row.createCell(dataCellId);//id
                        idCell.setCellValue(data.getId());
                        Cell uuidCell = row.createCell(dataCellId++);//uuid
                        uuidCell.setCellValue(data.getUuid());
                        Cell appealIdCell = row.createCell(dataCellId++);//appealId
                        appealIdCell.setCellValue(data.getAppealid());
                        Cell appealNameCell = row.createCell(dataCellId++);//appealName
                        appealNameCell.setCellValue(data.getAppealname());
                        Cell appealCityCell = row.createCell(dataCellId++);//appealCity
                        appealCityCell.setCellValue(data.getCity());
                        Cell appealTownCell = row.createCell(dataCellId++);//appealTown
                        appealTownCell.setCellValue(data.getTown());
                        Cell appealVillageCell = row.createCell(dataCellId++);//appealVillage
                        appealVillageCell.setCellValue(data.getVillage());
                        Cell appealTelCell = row.createCell(dataCellId++);//appealTel
                        appealTelCell.setCellValue(data.getAppealtel());
                        Cell proposerCell = row.createCell(dataCellId++);//proposer
                        proposerCell.setCellValue(("0").equals(data.getProposer()) ? "个人提出" : "集体提出");
                        Cell appealTypeCell = row.createCell(dataCellId++);//appealType
                        String appealType = data.getAppealtype();
                        if (("0").equals(appealType)) {
                            appealTypeCell.setCellValue("发展生产");
                        } else if (("1").equals(appealType)) {
                            appealTypeCell.setCellValue("基础设施");
                        } else if (("2").equals(appealType)) {
                            appealTypeCell.setCellValue("矛盾纠纷");
                        } else if (("3").equals(appealType)) {
                            appealTypeCell.setCellValue("社会治安");
                        } else if (("4").equals(appealType)) {
                            appealTypeCell.setCellValue("生活救助");
                        } else if (("5").equals(appealType)) {
                            appealTypeCell.setCellValue("征地拆迁");
                        } else if (("6").equals(appealType)) {
                            appealTypeCell.setCellValue("其他");
                        }
                        Cell affairTypeCell = row.createCell(dataCellId++);//affairType
                        affairTypeCell.setCellValue(("0").equals(data.getAffairtype()) ? "个人事务" : "集体事务");
                        Cell statusCell = row.createCell(dataCellId++);//status
                        String status = data.getStatus();
                        if (("0").equals(status)) {
                            statusCell.setCellValue("未解决");
                        } else if (("1").equals(status)) {
                            statusCell.setCellValue("已上报上级协调解决");
                        } else if (("2").equals(status)) {
                            statusCell.setCellValue("延时解决");
                        } else if (("3").equals(status)) {
                            statusCell.setCellValue("正在解决");
                        } else if (("4").equals(status)) {
                            statusCell.setCellValue("已做好解释说明工作");
                        } else if (("5").equals(status)) {
                            statusCell.setCellValue("已解决");
                        }
                        Cell doingstatusCell = row.createCell(dataCellId++);//status
                        String doingstatus = data.getDoingstatus();
                        if (("0").equals(doingstatus)) {
                            doingstatusCell.setCellValue("立学立改项目");
                        } else if (("1").equals(doingstatus)) {
                            doingstatusCell.setCellValue("短期整改项目");
                        } else if (("2").equals(doingstatus)) {
                            doingstatusCell.setCellValue("中长期整改项目");
                        }
                        Cell hardshipAppealCell = row.createCell(dataCellId++);//hardshipAppeal
                        hardshipAppealCell.setCellValue(data.getHardshipappeal());
                        Cell dutyLeaderCell = row.createCell(dataCellId++);//dutyLeader
                        dutyLeaderCell.setCellValue(data.getDutyleader());
                        Cell dutyDeptCell = row.createCell(dataCellId++);//dutyDept
                        dutyDeptCell.setCellValue(data.getDutydept());
                        Cell dutyMemberCell = row.createCell(dataCellId++);//dutyMember
                        dutyMemberCell.setCellValue(data.getDutymenber());
                        Cell solutionCell = row.createCell(dataCellId++);//solution
                        solutionCell.setCellValue(data.getSolution());
                        Cell createdateCell = row.createCell(dataCellId++);//createdate
                        createdateCell.setCellValue(data.getCreatedate());
                        Cell timeLimitCell = row.createCell(dataCellId);//timeLimit
                        timeLimitCell.setCellValue(data.getTimelimit());

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
            exportData.setExportcontent("诉求信息");
            exportData.setExporttime(new Date());
            exportData.setSpendtime((endTime - beginTime) / 1000);
            exportData.setUrl("\\" + fileName.replace(contextRootPath, ""));
            exportData.setType("0");//诉求信息excel 0
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
                List<Tappeal> appealDataExportList;
                String hqlToCount = "select count(*) from Tappeal t";
                int totalElements = appealDao.count(hqlToCount).intValue();
                int totalPages = totalElements % pageSize == 0 ? totalElements / pageSize : totalElements / pageSize + 1;
                int currentPage = 1;
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("from Tappeal t where t.id>:maxId and ");
                //拼接HQL
                for (FilterRule filterRule : filtersClass.getRules()) {

                    //判断rules 中的操作 op
                    stringBuilder = SearchUtil.getHql(stringBuilder, filterRule, params);

                }
                hql = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 5);
                hql = hql + " order by t.village asc, t.createdate desc";
                //都在这里面循环
                //将数据保存到sheet中
                do {
                    params.put("maxId", maxId.intValue());
                    appealDataExportList = appealDao.find(hql, params, 0, pageSize);
                    //循环操作数据
                    for (Tappeal data : appealDataExportList) {
                        int dataCellId = 0;
                        Row row = sheet.createRow(totalRows);
                        //创建数据行，并设置每列的数据
                        Cell idCell = row.createCell(dataCellId);//id
                        idCell.setCellValue(data.getId());
                        Cell uuidCell = row.createCell(dataCellId++);//uuid
                        uuidCell.setCellValue(data.getUuid());
                        Cell appealIdCell = row.createCell(dataCellId++);//appealId
                        appealIdCell.setCellValue(data.getAppealid());
                        Cell appealNameCell = row.createCell(dataCellId++);//appealName
                        appealNameCell.setCellValue(data.getAppealname());
                        Cell appealCityCell = row.createCell(dataCellId++);//appealCity
                        appealCityCell.setCellValue(data.getCity());
                        Cell appealTownCell = row.createCell(dataCellId++);//appealTown
                        appealTownCell.setCellValue(data.getTown());
                        Cell appealVillageCell = row.createCell(dataCellId++);//appealVillage
                        appealVillageCell.setCellValue(data.getVillage());
                        Cell appealTelCell = row.createCell(dataCellId++);//appealTel
                        appealTelCell.setCellValue(data.getAppealtel());
                        Cell proposerCell = row.createCell(dataCellId++);//proposer
                        proposerCell.setCellValue(("0").equals(data.getProposer()) ? "个人提出" : "集体提出");
                        Cell appealTypeCell = row.createCell(dataCellId++);//appealType
                        //格式化数据（原本数据库中保存的可能是0，1，2等数值，需要保存为具体的类型）
                        String appealType = data.getAppealtype();
                        if (("0").equals(appealType)) {
                            appealTypeCell.setCellValue("发展生产");
                        } else if (("1").equals(appealType)) {
                            appealTypeCell.setCellValue("基础设施");
                        } else if (("2").equals(appealType)) {
                            appealTypeCell.setCellValue("矛盾纠纷");
                        } else if (("3").equals(appealType)) {
                            appealTypeCell.setCellValue("社会治安");
                        } else if (("4").equals(appealType)) {
                            appealTypeCell.setCellValue("生活救助");
                        } else if (("5").equals(appealType)) {
                            appealTypeCell.setCellValue("征地拆迁");
                        } else if (("6").equals(appealType)) {
                            appealTypeCell.setCellValue("其他");
                        }
                        Cell affairTypeCell = row.createCell(dataCellId++);//affairType
                        affairTypeCell.setCellValue(("0").equals(data.getAffairtype()) ? "个人事务" : "集体事务");
                        Cell statusCell = row.createCell(dataCellId++);//status
                        String status = data.getStatus();
                        if (("0").equals(status)) {
                            statusCell.setCellValue("未解决");
                        } else if (("1").equals(status)) {
                            statusCell.setCellValue("已上报上级协调解决");
                        } else if (("2").equals(status)) {
                            statusCell.setCellValue("延时解决");
                        } else if (("3").equals(status)) {
                            statusCell.setCellValue("正在解决");
                        } else if (("4").equals(status)) {
                            statusCell.setCellValue("已做好解释说明工作");
                        } else if (("5").equals(status)) {
                            statusCell.setCellValue("已解决");
                        }
                        Cell doingstatusCell = row.createCell(dataCellId++);//status
                        String doingstatus = data.getDoingstatus();
                        if (("0").equals(doingstatus)) {
                            doingstatusCell.setCellValue("立学立改项目");
                        } else if (("1").equals(doingstatus)) {
                            doingstatusCell.setCellValue("短期整改项目");
                        } else if (("2").equals(doingstatus)) {
                            doingstatusCell.setCellValue("中长期整改项目");
                        }
                        Cell hardshipAppealCell = row.createCell(dataCellId++);//hardshipAppeal
                        hardshipAppealCell.setCellValue(data.getHardshipappeal());
                        Cell dutyLeaderCell = row.createCell(dataCellId++);//dutyLeader
                        dutyLeaderCell.setCellValue(data.getDutyleader());
                        Cell dutyDeptCell = row.createCell(dataCellId++);//dutyDept
                        dutyDeptCell.setCellValue(data.getDutydept());
                        Cell dutyMemberCell = row.createCell(dataCellId++);//dutyMember
                        dutyMemberCell.setCellValue(data.getDutymenber());
                        Cell solutionCell = row.createCell(dataCellId++);//solution
                        solutionCell.setCellValue(data.getSolution());
                        Cell createdateCell = row.createCell(dataCellId++);//createdate
                        createdateCell.setCellValue(data.getCreatedate());
                        Cell timeLimitCell = row.createCell(dataCellId);//timeLimit
                        timeLimitCell.setCellValue(data.getTimelimit());

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
            exportData.setExportcontent("诉求信息");
            exportData.setExporttime(new Date());
            exportData.setSpendtime((endTime - beginTime) / 1000);
            exportData.setUrl("\\" + fileName.replace(contextRootPath, ""));
            exportData.setType("0");//诉求信息excel 0
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
    public void exportExcel2007(String contextRootPath, String starttime, String endtime, User user) {

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
                List<Tappeal> appealDataExportList;
                String hqlToCount = "select count(*) from Tappeal t";
                int totalElements = appealDao.count(hqlToCount).intValue();
                int totalPages = totalElements % pageSize == 0 ? totalElements / pageSize : totalElements / pageSize + 1;
                int currentPage = 1;
                Map<String, Object> params = new HashMap<String, Object>();

                String hql = "from Tappeal t where t.createdate >= :starttime and t.createdate <= :endtime order by t.village asc, t.createdate desc";
                do {//都在这里面循环
                    params.put("starttime", starttime);
                    params.put("endtime", endtime);
                    appealDataExportList = appealDao.find(hql, params, 0, pageSize);
                    for (Tappeal data : appealDataExportList) {
                        int dataCellId = 0;
                        Row row = sheet.createRow(totalRows);
                        Cell idCell = row.createCell(dataCellId);//id
                        idCell.setCellValue(data.getId());
                        Cell uuidCell = row.createCell(dataCellId++);//uuid
                        uuidCell.setCellValue(data.getUuid());
                        Cell appealIdCell = row.createCell(dataCellId++);//appealId
                        appealIdCell.setCellValue(data.getAppealid());
                        Cell appealNameCell = row.createCell(dataCellId++);//appealName
                        appealNameCell.setCellValue(data.getAppealname());
                        Cell appealCityCell = row.createCell(dataCellId++);//appealCity
                        appealCityCell.setCellValue(data.getCity());
                        Cell appealTownCell = row.createCell(dataCellId++);//appealTown
                        appealTownCell.setCellValue(data.getTown());
                        Cell appealVillageCell = row.createCell(dataCellId++);//appealVillage
                        appealVillageCell.setCellValue(data.getVillage());
                        Cell appealTelCell = row.createCell(dataCellId++);//appealTel
                        appealTelCell.setCellValue(data.getAppealtel());
                        Cell proposerCell = row.createCell(dataCellId++);//proposer
                        proposerCell.setCellValue(("0").equals(data.getProposer()) ? "个人提出" : "集体提出");
                        Cell appealTypeCell = row.createCell(dataCellId++);//appealType
                        String appealType = data.getAppealtype();
                        if (("0").equals(appealType)) {
                            appealTypeCell.setCellValue("发展生产");
                        } else if (("1").equals(appealType)) {
                            appealTypeCell.setCellValue("基础设施");
                        } else if (("2").equals(appealType)) {
                            appealTypeCell.setCellValue("矛盾纠纷");
                        } else if (("3").equals(appealType)) {
                            appealTypeCell.setCellValue("社会治安");
                        } else if (("4").equals(appealType)) {
                            appealTypeCell.setCellValue("生活救助");
                        } else if (("5").equals(appealType)) {
                            appealTypeCell.setCellValue("征地拆迁");
                        } else if (("6").equals(appealType)) {
                            appealTypeCell.setCellValue("其他");
                        }
                        Cell affairTypeCell = row.createCell(dataCellId++);//affairType
                        affairTypeCell.setCellValue(("0").equals(data.getAffairtype()) ? "个人事务" : "集体事务");
                        Cell statusCell = row.createCell(dataCellId++);//status
                        String status = data.getStatus();
                        if (("0").equals(status)) {
                            statusCell.setCellValue("未解决");
                        } else if (("1").equals(status)) {
                            statusCell.setCellValue("已上报上级协调解决");
                        } else if (("2").equals(status)) {
                            statusCell.setCellValue("延时解决");
                        } else if (("3").equals(status)) {
                            statusCell.setCellValue("正在解决");
                        } else if (("4").equals(status)) {
                            statusCell.setCellValue("已做好解释说明工作");
                        } else if (("5").equals(status)) {
                            statusCell.setCellValue("已解决");
                        }
                        Cell doingstatusCell = row.createCell(dataCellId++);//status
                        String doingstatus = data.getDoingstatus();
                        if (("0").equals(doingstatus)) {
                            doingstatusCell.setCellValue("立学立改项目");
                        } else if (("1").equals(doingstatus)) {
                            doingstatusCell.setCellValue("短期整改项目");
                        } else if (("2").equals(doingstatus)) {
                            doingstatusCell.setCellValue("中长期整改项目");
                        }
                        Cell hardshipAppealCell = row.createCell(dataCellId++);//hardshipAppeal
                        hardshipAppealCell.setCellValue(data.getHardshipappeal());
                        Cell dutyLeaderCell = row.createCell(dataCellId++);//dutyLeader
                        dutyLeaderCell.setCellValue(data.getDutyleader());
                        Cell dutyDeptCell = row.createCell(dataCellId++);//dutyDept
                        dutyDeptCell.setCellValue(data.getDutydept());
                        Cell dutyMemberCell = row.createCell(dataCellId++);//dutyMember
                        dutyMemberCell.setCellValue(data.getDutymenber());
                        Cell solutionCell = row.createCell(dataCellId++);//solution
                        solutionCell.setCellValue(data.getSolution());
                        Cell createdateCell = row.createCell(dataCellId++);//createdate
                        createdateCell.setCellValue(data.getCreatedate());
                        Cell timeLimitCell = row.createCell(dataCellId);//timeLimit
                        timeLimitCell.setCellValue(data.getTimelimit());

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
            exportData.setExportcontent("诉求信息");
            exportData.setExporttime(new Date());
            exportData.setSpendtime((endTime - beginTime) / 1000);
            exportData.setUrl("\\" + fileName.replace(contextRootPath, ""));
            exportData.setType("0");//诉求信息excel 0
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
     * @param extension - 扩展名为xlsx（excel2007的格式）
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
     * @param sheet - 当前sheet
     * @return - headerRow 首行：标题行
     */
    private Row createHeaderRow(Sheet sheet) {
        //第一列起始序号为0
        int headerCellId = 0;//起始数据行ID
        //创建标题行
        Row headerRow = sheet.createRow(0);
        //创建对应的列标题（第一列headerCellId要记得++）

        Cell uuidHeaderCell = headerRow.createCell(headerCellId++);
        uuidHeaderCell.setCellValue("诉求编号");//uuid
        Cell appealIdHeaderCell = headerRow.createCell(headerCellId++);
        appealIdHeaderCell.setCellValue("诉求方ID");//appealId
        Cell appealNameHeaderCell = headerRow.createCell(headerCellId++);
        appealNameHeaderCell.setCellValue("诉求方名");//appealName
        Cell appealCityHeaderCell = headerRow.createCell(headerCellId++);
        appealCityHeaderCell.setCellValue("诉求方所属市/县");//appealCity
        Cell appealTownHeaderCell = headerRow.createCell(headerCellId++);
        appealTownHeaderCell.setCellValue("诉求方所属镇");//appealTown
        Cell appealVillageHeaderCell = headerRow.createCell(headerCellId++);
        appealVillageHeaderCell.setCellValue("诉求方所属村");//appealName
        Cell appealTelHeaderCell = headerRow.createCell(headerCellId++);
        appealTelHeaderCell.setCellValue("联系号码");//appealTel
        Cell proposerHeaderCell = headerRow.createCell(headerCellId++);
        proposerHeaderCell.setCellValue("个人/集体提出");//proposer
        Cell appealTypeHeaderCell = headerRow.createCell(headerCellId++);
        appealTypeHeaderCell.setCellValue("诉求类别");//appealType
        Cell affairTypeHeaderCell = headerRow.createCell(headerCellId++);
        affairTypeHeaderCell.setCellValue("事务类别");//affairType
        Cell statusHeaderCell = headerRow.createCell(headerCellId++);
        statusHeaderCell.setCellValue("诉求状态");//status
        Cell doingstatusHeaderCell = headerRow.createCell(headerCellId++);
        doingstatusHeaderCell.setCellValue("整改类型");//doingstatus
        Cell hardshipAppealHeaderCell = headerRow.createCell(headerCellId++);
        hardshipAppealHeaderCell.setCellValue("诉求内容");//hardshipAppeal
        Cell dutyLeaderHeaderCell = headerRow.createCell(headerCellId++);
        dutyLeaderHeaderCell.setCellValue("责任领导");//dutyLeader
        Cell dutyDeptHeaderCell = headerRow.createCell(headerCellId++);
        dutyDeptHeaderCell.setCellValue("责任部门");//dutyDept
        Cell dutyMemberHeaderCell = headerRow.createCell(headerCellId++);
        dutyMemberHeaderCell.setCellValue("责任人");//dutyMember
        Cell solutionHeaderCell = headerRow.createCell(headerCellId++);
        solutionHeaderCell.setCellValue("解决措施");//solution
        Cell createdateHeaderCell = headerRow.createCell(headerCellId++);
        createdateHeaderCell.setCellValue("创建时间");//createdate
        Cell timeLimitHeaderCell = headerRow.createCell(headerCellId);
        timeLimitHeaderCell.setCellValue("完成时限");//timeLimit
        //返回headerRow
        return headerRow;
    }

}
