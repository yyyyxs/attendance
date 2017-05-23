package com.jmhz.device.sys.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p> .
 * <p> Created at 2014/5/18 10:35
 *
 * @author Charkey
 * @version 1.0
 */
@Entity
@Table(name = "showcase_excel_data")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ExcelData implements Serializable {

    private int id;

    private String content;

    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
