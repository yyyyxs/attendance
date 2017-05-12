package com.jmhz.device.sys.pageEntity;

/**
 * <p> .
 * <p> Created at 2014/4/26 14:32
 *
 * @author Charkey
 * @version 1.0
 */
public class UserInfo {

    private Long id; //编号
    private String username; //用户名
    private String roleNames; //拥有的角色列表
    private Long organizationId;
    private String orgName; //所属公司
    private String tel;
    private Boolean locked = Boolean.FALSE;
    private String city; // 县
    private String town; // 镇
    private int auth_level; // 授权级别

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

    public int getAuth_level() {
        return auth_level;
    }

    public void setAuth_level(int auth_level) {
        this.auth_level = auth_level;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roleNames='" + roleNames + '\'' +
                ", organizationId=" + organizationId +
                ", orgName='" + orgName + '\'' +
                ", tel='" + tel + '\'' +
                ", locked=" + locked +
                '}';
    }
}
