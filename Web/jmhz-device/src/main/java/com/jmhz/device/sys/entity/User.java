package com.jmhz.device.sys.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class User implements Serializable {
    private Long id; //编号
    private Boolean locked = Boolean.FALSE;
    //    private Long organizationId; //所属公司
    private String roleIds; //拥有的角色列表
    private String salt; //加密密码的盐
    private String city; // 县
    private String town; // 镇
    private int auth_level; // 授权级别
    private String tel;
    private String username; //用户名
    private String password; //密码
    private String studentName; //学生姓名
    private String teacherName;//教师姓名
    private String grade;//学生年级
    private int teacherId; //教师Id

    //    private int studentId; //学生Id
    public User() {
    }

    public User(Long id, String username, String password, String salt, String roleIds, Long organizationId, String tel, Boolean locked, String city, String town, int auth_level) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.roleIds = roleIds;
//        this.organizationId = organizationId;
        this.tel = tel;
        this.locked = locked;
        this.city = city;
        this.town = town;
        this.auth_level = auth_level;
    }

    public User(Long id, Boolean locked, Long organizationId, String roleIds, String salt, String city, String town, int auth_level, String tel, String username, String password, String studentName, String teacherName, String grade, int teacherId) {
        this.id = id;
        this.locked = locked;
//        this.organizationId = organizationId;
        this.roleIds = roleIds;
        this.salt = salt;
        this.city = city;
        this.town = town;
        this.auth_level = auth_level;
        this.tel = tel;
        this.username = username;
        this.password = password;
        this.studentName = studentName;
        this.teacherName = teacherName;
        this.grade = grade;
        this.teacherId = teacherId;
//        this.studentId = studentId;
    }

    @Id
    @Column(name = "id", nullable = false, length = 36)
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Column(name = "organization_id", nullable = false, length = 36)
//    public Long getOrganizationId() {
//        return organizationId;
//    }
//
//    public void setOrganizationId(Long organizationId) {
//        this.organizationId = organizationId;
//    }

    @Column(name = "username", nullable = false, length = 36)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false, length = 36)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "salt", nullable = false, length = 36)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Column(name = "role_ids", nullable = false, length = 36)
    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    @Column(name = "locked", nullable = false, length = 36)
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Column(name = "tel", nullable = false, length = 36)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
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

    @Column(name = "studentName", nullable = false, length = 36)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Column(name = "teacherName", nullable = false, length = 36)
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Column(name = "Grade", nullable = false, length = 36)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Column(name = "teacherId", nullable = false, length = 36)
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
//    @Column(name = "studentId", nullable = false, length = 36)
//    public int getStudentId() {
//        return studentId;
//    }
//
//    public void setStudentId(int studentId) {
//        this.studentId = studentId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != null ? !id.equals(user.id) : user.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", locked=" + locked +
//                ", organizationId=" + organizationId +
                ", roleIds='" + roleIds + '\'' +
                ", salt='" + salt + '\'' +
                ", city='" + city + '\'' +
                ", town='" + town + '\'' +
                ", auth_level=" + auth_level +
                ", tel='" + tel + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", studentName='" + studentName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", Grade='" + grade + '\'' +
                ", teacherId=" + teacherId +
//                ", studentId=" + studentId +
                '}';
    }
}
