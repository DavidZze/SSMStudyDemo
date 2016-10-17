package com.excelib.domain.model.periphery;

import java.util.List;

import com.excelib.domain.model.Departments;
import com.excelib.domain.model.Employees;

/**
 * 描述：
 * 模拟复杂的对象。
 * 希望在使用的时候再确定对象的类型。
 * 策略：
 * 使用泛型。
 * @author zhouze
 *
 */
public class ComplexPOJO {
    
    private String str1 = "str1";
    private Integer int1 = 0;
    private Departments departments;
    private List<Departments> deptList;
    private Employees employees;
    private List<Employees> empList;
    private List<Object> lines;
    private Object lines2;
    
    /** 缺省构造器*/
    public ComplexPOJO() {
    }
    
    

    public Object getLines2() {
        return lines2;
    }



    public void setLines2(Object lines2) {
        this.lines2 = lines2;
    }



    public List<Object> getLines() {
        return lines;
    }



    public void setLines(List<Object> lines) {
        this.lines = lines;
    }



    public String getStr1() {
        return str1;
    }


    public void setStr1(String str1) {
        this.str1 = str1;
    }


    public Integer getInt1() {
        return int1;
    }


    public void setInt1(Integer int1) {
        this.int1 = int1;
    }


    public Departments getDepartments() {
        return departments;
    }


    public void setDepartments(Departments departments) {
        this.departments = departments;
    }


    public List<Departments> getDeptList() {
        return deptList;
    }


    public void setDeptList(List<Departments> deptList) {
        this.deptList = deptList;
    }


    public Employees getEmployees() {
        return employees;
    }


    public void setEmployees(Employees employees) {
        this.employees = employees;
    }


    public List<Employees> getEmpList() {
        return empList;
    }


    public void setEmpList(List<Employees> empList) {
        this.empList = empList;
    }

    
}
