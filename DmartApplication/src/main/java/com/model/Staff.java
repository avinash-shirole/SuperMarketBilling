package com.model;

public class Staff { 
	private int sid;
	private String sname;
	private String spassword;
	private String semailid;
	private double ssalary;
	public String getSemailid() {
		return semailid;
	}
	public void setSemailid(String semailid) {
		this.semailid = semailid;
	}
	public double getSsalary() {
		return ssalary;
	}
	public void setSsalary(double ssalary) {
		this.ssalary = ssalary;
	}
	@Override
	public String toString() {
		return "Staff [sid=" + sid + ", sname=" + sname + ", spassword=" + spassword + ", semailid=" + semailid
				+ ", ssalary=" + ssalary + "]";
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpassword() {
		return spassword;
	}
	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}

}
