package com.system.web.bean;

import java.io.Serializable;

public class ColOptions implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String table_Name;	// 資料庫表個名稱
	private String col_Name;	// 欄位名稱
	private String option_Value;// 選項 資料庫名稱
	private String option_Text;	// 選項 呈現名稱
	private int display_Order;
	private String remark;
	private String permission;

//	private String maintainUser;
//	private String maintainDate;
//	private String maintainTime;	

//	public String getMaintainUser() {
//		return maintainUser;
//	}
//	public void setMaintainUser(String maintainUser) {
//		this.maintainUser = maintainUser;
//	}
//	public String getMaintainDate() {
//		return maintainDate;
//	}
//	public void setMaintainDate(String maintainDate) {
//		this.maintainDate = maintainDate;
//	}
//	public String getMaintainTime() {
//		return maintainTime;
//	}
//	public void setMaintainTime(String maintainTime) {
//		this.maintainTime = maintainTime;
//	}

	public String getTable_Name() {
		return table_Name;
	}

	public void setTable_Name(String table_Name) {
		this.table_Name = table_Name;
	}

	public String getCol_Name() {
		return col_Name;
	}

	public void setCol_Name(String col_Name) {
		this.col_Name = col_Name;
	}

	public String getOption_Value() {
		return option_Value;
	}

	public void setOption_Value(String option_Value) {
		this.option_Value = option_Value;
	}

	public String getOption_Text() {
		return option_Text;
	}

	public void setOption_Text(String option_Text) {
		this.option_Text = option_Text;
	}

	public int getDisplay_Order() {
		return display_Order;
	}

	public void setDisplay_Order(int display_Order) {
		this.display_Order = display_Order;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Override
	public String toString() {
		return "ColOptions [table_Name=" + table_Name + ", col_Name=" + col_Name + ", option_Value=" + option_Value
				+ ", option_Text=" + option_Text + ", display_Order=" + display_Order + ", remark=" + remark
				+ ", permission=" + permission
//				+ ", maintainUser=" + maintainUser + ", maintainDate=" + maintainDate + ", maintainTime=" + maintainTime 
				+ "]";
	}

}
