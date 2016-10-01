package com.excelib.domain.model;

import java.io.Serializable;

/**
 * 
 * @author zhouze
 *
 */
public class Person implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int id;  
    private String name;  
    private boolean status;
    
    /**
     *  无参构造器
     */
    public Person() {
    	
    }
    
    /**
     * 含参数构造器
     * @param id
     * @param name
     * @param status
     */
    public Person(int id, String name, boolean status) {
    	this.id = id;
    	this.name = name;
    	this.status = status;
    }
    
    
    
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		String info = "id: " + id 
				 	  + " name: " + name 
				      + " status: " + status;
		return info;
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
