package com.jmhz.device.sys.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_role")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Role implements Serializable {
    private Long id; //编号
    private String role; //角色标识 程序中判断使用,如"admin"
    private String description; //角色描述,UI界面显示使用
    private String resourceIds; //拥有的资源
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public Role() {
    }

    public Role(Long id, String role, String description, String resourceIds, Boolean available) {
        this.id = id;
        this.role = role;
        this.description = description;
        this.resourceIds = resourceIds;
        this.available = available;
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

    @Column(name = "role", nullable = false, length = 36)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "description", nullable = false, length = 36)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "resource_ids", nullable = false, length = 36)
    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Column(name = "available", nullable = false, length = 36)
    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", resourceIds=" + resourceIds +
                ", available=" + available +
                '}';
    }
}
