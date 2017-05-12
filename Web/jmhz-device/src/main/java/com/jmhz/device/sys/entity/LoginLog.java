package com.jmhz.device.sys.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p> sys_loginlog
 * <p> Created at 2014/5/18 10:35
 *
 * @author Charkey
 * @version 1.0
 */
@Entity
@Table(name = "sys_loginlog")
@DynamicInsert(true)
@DynamicUpdate(true)
public class LoginLog implements Serializable {

    private int id;

    private String ip;

    private String user;

    private String ipinfo;

    private String useragent;

    private String browser;

    private String osinfo;

    private String logintime;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpinfo() {
        return ipinfo;
    }

    public void setIpinfo(String ipinfo) {
        this.ipinfo = ipinfo;
    }

    public String getUseragent() {
        return useragent;
    }

    public void setUseragent(String useragent) {
        this.useragent = useragent;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOsinfo() {
        return osinfo;
    }

    public void setOsinfo(String osinfo) {
        this.osinfo = osinfo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", user='" + user + '\'' +
                ", ipinfo='" + ipinfo + '\'' +
                ", useragent='" + useragent + '\'' +
                ", browser='" + browser + '\'' +
                ", osinfo='" + osinfo + '\'' +
                ", logintime=" + logintime +
                '}';
    }
}
