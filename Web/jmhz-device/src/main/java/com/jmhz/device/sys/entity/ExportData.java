package com.jmhz.device.sys.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p> .
 * <p> Created at 2014/5/18 10:35
 *
 * @author Charkey
 * @version 1.0
 */
@Entity
@Table(name = "sys_export")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ExportData implements Serializable {

    private int id;

    private String city;

    private String town;

    private Date exporttime;

    private Long spendtime;

    private String exportcontent;

    private String filename;

    private String filepath;

    private String filesize;

    private String url;

    private String type;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "town")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "exporttime")
    public Date getExporttime() {
        return exporttime;
    }

    public void setExporttime(Date exporttime) {
        this.exporttime = exporttime;
    }

    @Column(name = "spendtime")
    public Long getSpendtime() {
        return spendtime;
    }

    public void setSpendtime(Long spendtime) {
        this.spendtime = spendtime;
    }

    @Column(name = "exportcontent")
    public String getExportcontent() {
        return exportcontent;
    }

    public void setExportcontent(String exportcontent) {
        this.exportcontent = exportcontent;
    }

    @Column(name = "filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Column(name = "filepath")
    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Column(name = "filesize")
    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ExportData{" +
                "id=" + id +
                ", exporttime=" + exporttime +
                ", spendtime=" + spendtime +
                ", exportcontent='" + exportcontent + '\'' +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", filesize='" + filesize + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
