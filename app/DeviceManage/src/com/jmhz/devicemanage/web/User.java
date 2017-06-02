package com.jmhz.devicemanage.web;





import java.io.Serializable;


public class User implements Serializable {
    private Long id; //���
    private Boolean locked = Boolean.FALSE;
    //    private Long organizationId; //������˾
    private String roleIds; //ӵ�еĽ�ɫ�б�
    private String salt; //�����������
    private String city; // ��
    private String town; // ��
    private int auth_level; // ��Ȩ����
    private String tel;
    private String username; //�û���
    private String password; //����
    private String studentName; //ѧ������
    private String teacherName;//��ʦ����
    private String grade;//ѧ���꼶
    private int teacherId; //��ʦId

    //    private int studentId; //ѧ��Id
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

  
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

 
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

 
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

  
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

  
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

  
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

   
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

 
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
