package com.jmhz.device.sys.service;

import com.google.common.collect.Maps;
import com.jmhz.device.util.LogUtils;
import com.jmhz.device.sys.dao.IExcelDataDao;
import com.jmhz.device.sys.entity.ExcelData;
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
import java.util.*;

/**
 * <p> .
 * <p> Created at 2014/5/18 10:40
 *
 * @author Charkey
 * @version 1.0
 */
@Service
public class ExcelDataService {

    @Autowired
    private IExcelDataDao excelDataDao;

    private final static String storePath = "export/excel";
    private final static String EXPORT_FILENAME_PREFIX = "excel";
    private int pageSize = 1000;//查询时每页大小

    /**
     * 支持大数据量导出
     * excel 2007 每个sheet最多1048576行
     *
     * @param contextRootPath
     */
    @Async
    public void exportExcel2007(final String contextRootPath) {

        int rowAccessWindowSize = 1000; //内存中保留的行数，超出后会写到磁盘
        int perSheetRows = 100000; //每个sheet 10w条
        int totalRows = 0; //统计总行数
        Long maxId = 0L;//当前查询的数据中最大的id 优化分页的

        String fileName = generateFilename(contextRootPath, "xlsx");
        File file = new File(fileName);
        BufferedOutputStream out = null;
        SXSSFWorkbook wb = null;
        try {
            long beginTime = System.currentTimeMillis();
            wb = new SXSSFWorkbook(rowAccessWindowSize);
            wb.setCompressTempFiles(true);//生成的临时文件将进行gzip压缩

            while (true) {
                Sheet sheet = wb.createSheet();
                Row headerRow = sheet.createRow(0);
                Cell idHeaderCell = headerRow.createCell(0);
                idHeaderCell.setCellValue("编号");
                Cell contentHeaderCell = headerRow.createCell(1);
                contentHeaderCell.setCellValue("内容");
                Cell nameHeaderCell = headerRow.createCell(2);
                nameHeaderCell.setCellValue("姓名");
                totalRows = 1;
                List<ExcelData> excelDataList;
                String hqlToCount = "select count(*) from ExcelData";
                int totalElements = excelDataDao.count(hqlToCount).intValue();
                int totalPages = totalElements % pageSize == 0 ? totalElements / pageSize : totalElements / pageSize + 1;
                int currentPage = 1;
                Map<String, Object> params = new HashMap<String, Object>();
                String hql = "from ExcelData t where t.id>:maxId";
                do {//都在这里面循环
                    params.put("maxId", maxId.intValue());
                    excelDataList = excelDataDao.find(hql, params, 0, pageSize);
                    for (ExcelData data : excelDataList) {
                        Row row = sheet.createRow(totalRows);
                        Cell idCell = row.createCell(0);
                        idCell.setCellValue(data.getId());
                        Cell contentCell = row.createCell(1);
                        contentCell.setCellValue(data.getContent());
                        Cell nameCell = row.createCell(2);
                        nameCell.setCellValue(data.getName());
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
     * @param contextRootPath
     * @param extension
     * @return
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
            boolean mkdirTrue = false;
            if (!parentFile.exists()) {
                mkdirTrue = parentFile.mkdirs();
            }
            if (mkdirTrue) {
                return path;
            }
        }
        return generateFilename(contextRootPath, extension);
    }

}
