package com.excelib.domain.model;

public class Departments {
    private Short departmentId;

    private String departmentName;

    private Integer managerId;

    private Short locationId;

    private Short fauxColumn;
    
     

    public Short getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Short departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Short getLocationId() {
        return locationId;
    }

    public void setLocationId(Short locationId) {
        this.locationId = locationId;
    }

    public Short getFauxColumn() {
        return fauxColumn;
    }

    public void setFauxColumn(Short fauxColumn) {
        this.fauxColumn = fauxColumn;
    }
}