package com.system.web.bean;

import java.io.Serializable;

public class Teller_Information implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String teller_No;
	private String emplyee_No;
	private String branch_Code;
	private String license_No;
	private String branch_Name;
	private String teller_Type;
	private String real_Teller_Flag;
	private String job_No;
	private String teller_Auth_Level;
	private String teller_Active_Date;
	private String teller_Status;
	private String remark;

	private String maintainUser;
	private String maintainDate;
	private String maintainTime;

	public String getMaintainUser() {
		return maintainUser;
	}

	public void setMaintainUser(String maintainUser) {
		this.maintainUser = maintainUser;
	}

	public String getMaintainDate() {
		return maintainDate;
	}

	public void setMaintainDate(String maintainDate) {
		this.maintainDate = maintainDate;
	}

	public String getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(String maintainTime) {
		this.maintainTime = maintainTime;
	}

	public String getTeller_No() {
		return teller_No;
	}

	public void setTeller_No(String teller_No) {
		this.teller_No = teller_No;
	}

	public String getEmplyee_No() {
		return emplyee_No;
	}

	public void setEmplyee_No(String emplyee_No) {
		this.emplyee_No = emplyee_No;
	}

	public String getBranch_Code() {
		return branch_Code;
	}

	public void setBranch_Code(String branch_Code) {
		this.branch_Code = branch_Code;
	}

	public String getLicense_No() {
		return license_No;
	}

	public void setLicense_No(String license_No) {
		this.license_No = license_No;
	}

	public String getBranch_Name() {
		return branch_Name;
	}

	public void setBranch_Name(String branch_Name) {
		this.branch_Name = branch_Name;
	}

	public String getTeller_Type() {
		return teller_Type;
	}

	public void setTeller_Type(String teller_Type) {
		this.teller_Type = teller_Type;
	}

	public String getReal_Teller_Flag() {
		return real_Teller_Flag;
	}

	public void setReal_Teller_Flag(String real_Teller_Flag) {
		this.real_Teller_Flag = real_Teller_Flag;
	}

	public String getJob_No() {
		return job_No;
	}

	public void setJob_No(String job_No) {
		this.job_No = job_No;
	}

	public String getTeller_Auth_Level() {
		return teller_Auth_Level;
	}

	public void setTeller_Auth_Level(String teller_Auth_Level) {
		this.teller_Auth_Level = teller_Auth_Level;
	}

	public String getTeller_Active_Date() {
		return teller_Active_Date;
	}

	public void setTeller_Active_Date(String teller_Active_Date) {
		this.teller_Active_Date = teller_Active_Date;
	}

	public String getTeller_Status() {
		return teller_Status;
	}

	public void setTeller_Status(String teller_Status) {
		this.teller_Status = teller_Status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Teller_Information [teller_No=" + teller_No + ", emplyee_No=" + emplyee_No + ", branch_Code="
				+ branch_Code + ", license_No=" + license_No + ", branch_Name=" + branch_Name + ", teller_Type="
				+ teller_Type + ", real_Teller_Flag=" + real_Teller_Flag + ", job_No=" + job_No + ", teller_Auth_Level="
				+ teller_Auth_Level + ", teller_Active_Date=" + teller_Active_Date + ", teller_Status=" + teller_Status
				+ ", remark=" + remark + ", maintainUser=" + maintainUser + ", maintainDate=" + maintainDate
				+ ", maintainTime=" + maintainTime + "]";
	}

}
