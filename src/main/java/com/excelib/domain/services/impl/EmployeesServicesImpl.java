package com.excelib.domain.services.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.excelib.domain.model.Employees;
import com.excelib.domain.services.intf.EmployeesServices;
import com.excelib.infrastructure.dao.EmployeesMapper;


@Service("EemployeesService")
public class EmployeesServicesImpl implements EmployeesServices{

    @Resource
	public EmployeesMapper employeeMapper;
	
    
    private String unitTest1;
    private String unitTest2;
    private String unitTest3;
    
    /**
     * 缺省的构造器
     */
	public EmployeesServicesImpl() {
	}

	/**
	 * 方便单测使用的构造器。
	 * @param employeeMapper
	 */
	public EmployeesServicesImpl(EmployeesMapper employeeMapper) {
	    this.employeeMapper = employeeMapper;
    }
	
	
	@Override
	public Employees selectByEmpId(Integer employeeId) {
		System.out.println("----- do ServiceImpl ----");
		
		return employeeMapper.selectByPrimaryKey(employeeId);
	}

	/** 根据部门 id 与薪资进行查询，返回多条数据*/
	@Override
	public List<Employees> selectByDeptIdAndSalary(Integer deptId, BigDecimal salary) {
		return employeeMapper.selectManyRecords(deptId, salary);
	}

	/** 根据部门集合进行查询*/
	@Override
	public List<Employees> selectByDeptIdList(List<Integer> deptIdList) {
//		deptIdList = "(" + deptIdList + ")";
		return employeeMapper.inQuauseQuery(deptIdList);
	}
	
	/** 根据部门集合进行查询*/
	@Override
	public List<Employees> selectByDeptIdList2(String deptIdList) {
//		deptIdList = "(" + deptIdList + ")";
		return employeeMapper.inQuauseQuery2(deptIdList);
	}

	/** 根据部门集合进行查询*/
    @Override
    public List<Employees> selectByDeptIdList3(List<Integer> deptIdList, Integer salary) {
//      deptIdList = "(" + deptIdList + ")";
        Map<String,Object> paramMap = new HashMap<String,Object>();
        int size = deptIdList.size();
        paramMap.put("size", size);
        paramMap.put("deptIdList", deptIdList);
        paramMap.put("salary", salary);
        return employeeMapper.inQuauseQuery3(paramMap);
    }
	
	
	
	/** 插入一条员工记录*/
	@Override
	public void insertSelectiveTest(Employees employees) {
		System.out.println("---- 查询序列值：" + employeeMapper.queryEmpSeq());
		System.out.println("---- do insertSelectiveTest: " + employeeMapper.insertSelective(employees)); 
	}

	/** 插入多条员工记录*/
	@Override
//	@Transactional
	public void insertSelectiveTest_Batch(List<Employees> employeesList) {
		int size = employeesList.size();
		for(int i = 0; i < size; i++) {
			System.out.println("---- do insertSelectiveTest_Batch: " 
							   + employeeMapper.insertSelective(employeesList.get(i))); 
			
		}
//		throw new RuntimeException();
	}

    @Override
    public List<Integer> queryOrclComplex() {
         
        return employeeMapper.queryOrclComplex();
    }

    public String getUnitTest1() {
        return unitTest1;
    }

    public void setUnitTest1(String unitTest1) {
        this.unitTest1 = unitTest1;
    }

    public String getUnitTest2() {
        return unitTest2;
    }

    public void setUnitTest2(String unitTest2) {
        this.unitTest2 = unitTest2;
    }

    public String getUnitTest3() {
        return unitTest3;
    }

    public void setUnitTest3(String unitTest3) {
        this.unitTest3 = unitTest3;
    }
	
	
	

}
