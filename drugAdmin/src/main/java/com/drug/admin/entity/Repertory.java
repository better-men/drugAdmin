package com.drug.admin.entity;

public class Repertory {
    private String repertoryId;
    private String repertoryName;
    private String repertoryClass;
    private Integer repertoryNum;
    private String repertoryDesc;
    private String createdBy;
    private String createdTime;
    private Integer isDeleted;

    public String getRepertoryId() {
        return repertoryId;
    }

    public void setRepertoryId(String repertoryId) {
        this.repertoryId = repertoryId;
    }

    public String getRepertoryName() {
        return repertoryName;
    }

    public void setRepertoryName(String repertoryName) {
        this.repertoryName = repertoryName;
    }

    public String getRepertoryClass() {
        return repertoryClass;
    }

    public void setRepertoryClass(String repertoryClass) {
        this.repertoryClass = repertoryClass;
    }

    public Integer getRepertoryNum() {
        return repertoryNum;
    }

    public void setRepertoryNum(Integer repertoryNum) {
        this.repertoryNum = repertoryNum;
    }

    public String getRepertoryDesc() {
        return repertoryDesc;
    }

    public void setRepertoryDesc(String repertoryDesc) {
        this.repertoryDesc = repertoryDesc;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
