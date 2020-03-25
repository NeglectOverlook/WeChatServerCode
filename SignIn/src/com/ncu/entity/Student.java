package com.ncu.entity;

import java.util.Date;

public class Student {
	private String openID;
	private String studentName;
	private String studentNum;
	private Date joinTime;
	
	public Student(String openID, String studentName, String studentNum, Date joinTime) {
		super();
		this.openID = openID;
		this.studentName = studentName;
		this.studentNum = studentNum;
		this.joinTime = joinTime;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
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
