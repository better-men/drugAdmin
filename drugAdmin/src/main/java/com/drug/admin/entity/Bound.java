package com.drug.admin.entity;

public class Bound {
    private String boundId;
    private Double price;
    private Integer boundNum;
    private String repertoryId;
    private String repertoryName;
    private String repertoryClass;
    private String repertoryDesc;
    private String address;
    private String boundDesc;
    private String createdBy;
    private String createdTime;
    private Integer isDeleted;
    private Integer type;

    public String getBoundId() {
        return boundId;
    }

    public void setBoundId(String boundId) {
        this.boundId = boundId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBoundNum() {
        return boundNum;
    }

    public void setBoundNum(Integer boundNum) {
        this.boundNum = boundNum;
    }

    public String getRepertoryId() {
        return repertoryId;
    }

    public void setRepertoryId(String repertoryId) {
        this.repertoryId = repertoryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBoundDesc() {
        return boundDesc;
    }

    public void setBoundDesc(String boundDesc) {
        this.boundDesc = boundDesc;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getRepertoryDesc() {
        return repertoryDesc;
    }

    public void setRepertoryDesc(String repertoryDesc) {
        this.repertoryDesc = repertoryDesc;
    }
}
