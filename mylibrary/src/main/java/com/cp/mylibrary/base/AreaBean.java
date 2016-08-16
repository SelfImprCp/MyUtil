package com.cp.mylibrary.base;

import com.cp.mylibrary.bean.MyEntity;

/**
 * 省的实体
 * 
 * @author Administrator
 * 
 */

public class AreaBean extends MyEntity {

	public int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// 名称
	private String rname;
	// 爸爸级
	private int pid;

	
	// 此级的类型 
	private int rtype;
	
	
	public AreaBean() {
		super();
	}

	public AreaBean(String rname, int pid, int rtype) {
		super();
		this.rname = rname;
		this.pid = pid;
		this.rtype = rtype;
		
	}
	 

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getRtype() {
		return rtype;
	}

	public void setRtype(int rtype) {
		this.rtype = rtype;
	}
	
	

}
