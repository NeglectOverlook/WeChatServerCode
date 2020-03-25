package com.ncu.entity;

import java.util.Date;

public class AddCourse {
	private String openID;
	private int courseID;
	private Date addTime;
	public AddCourse(String openID, int courseID, Date addTime) {
		this.openID = openID;
		this.courseID = courseID;
		this.addTime = addTime;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	

}
