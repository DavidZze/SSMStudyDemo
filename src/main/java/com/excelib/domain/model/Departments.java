package com.excelib.domain.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Departments {
    private Short departmentId;

    private String departmentName;

    private Integer managerId;

    private Short locationId;

    private Short fauxColumn;
    
    private List<Integer> listNum;
    
    @JsonProperty("employeesList")
    private List<Employees> employeesList;
    
    /** 构造器*/
    public Departments(){
    	
    }
    
     public Departments(Employees emp){
    	 
     }
    
    
    
    
    
    public List<Integer> getListNum() {
		return listNum;
	}

	public void setListNum(List<Integer> listNum) {
		this.listNum = listNum;
	}

	
    
     

    public List<Employees> getEmployeesList() {
		return employeesList;
	}

	public void setEmployeesList(List<Employees> employeesList) {
		this.employeesList = employeesList;
	}

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