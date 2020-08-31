package com.example.demo.model;

import java.util.Date;

/**
 * 
 * 
 * @author ZZF
 * @date 2020/8/31
 */

/**
    * 用户角色表
    */
public class SysRole {
    private Integer id;

    /**
    * 角色名称
    */
    private String name;

    /**
    * 父级ID
    */
    private Integer parentId;

    /**
    * 类型。 0：分类； 1：角色
    */
    private Integer type;

    /**
    * 备注
    */
    private String remarks;

    private Integer creator;

    private Date createrTime;

    private Integer modifier;

    private Date modifiedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreaterTime() {
        return createrTime;
    }

    public void setCreaterTime(Date createrTime) {
        this.createrTime = createrTime;
    }

    public Integer getModifier() {
        return modifier;
    }

    public void setModifier(Integer modifier) {
        this.modifier = modifier;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}