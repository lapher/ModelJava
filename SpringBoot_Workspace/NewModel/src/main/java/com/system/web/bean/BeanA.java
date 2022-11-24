package com.system.web.bean;

import java.io.Serializable;

public class BeanA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String col;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) {
		this.col = col;
	}
	@Override
	public String toString() {
		return "Teller_Information [id=" + id + ", name=" + name + ", col=" + col + "]";
	}

	
	

	

}
