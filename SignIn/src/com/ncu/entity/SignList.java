package com.ncu.entity;


public class SignList {
	private String signTime;
	private String studentName;
	private String studentNum;
	public SignList(String signTime, String studentName, String studentNum) {
		this.signTime = signTime;
		this.studentName = studentName;
		this.studentNum = studentNum;
	}
	public SignList() {
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	

}
