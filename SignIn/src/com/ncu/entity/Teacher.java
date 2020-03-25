package com.ncu.entity;

import java.util.Date;

public class Teacher {
	private String openID;
	private String teacherID;
	private String teacherName;
	private Date joinTime;
	
	public Teacher(String openID, String teacherID, String teacherName, Date joinTime) {
		super();
		this.openID = openID;
		this.teacherID = teacherID;
		this.teacherName = teacherName;
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
	public String getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	

}
