package com.drug.admin.entity;

public class Bound {
    private String boundId;
    private Double price;
    private Integer boundNum;
    private String repertoryId;
    private String address;
    private String boundDesc;
    private String createdBy;
    private String createdTime;
    private String isDeleted;
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

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
