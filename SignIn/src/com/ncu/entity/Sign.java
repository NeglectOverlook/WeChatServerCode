package com.ncu.entity;

import java.util.Date;

public class Sign {
	private int checkID;
	private int courseID;
	private String openID;
	private Date signTime;
	public Sign(int courseID, String openID, Date signTime) {
		this.courseID = courseID;
		this.openID = openID;
		this.signTime = signTime;
	}
	public int getCheckID() {
		return checkID;
	}
	public void setCheckID(int checkID) {
		this.checkID = checkID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	
	

}
