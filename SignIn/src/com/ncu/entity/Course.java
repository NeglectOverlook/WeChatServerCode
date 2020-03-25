package com.ncu.entity;

import java.util.Date;

public class Course {
	private int courseID;
	private String openID;
	private String courseName;
	private String  ofClass;
	private Date buildTime;
	public Course(String openID, String courseName, String ofClass, Date buildTime) {
		this.openID = openID;
		this.courseName = courseName;
		this.ofClass = ofClass;
		this.buildTime = buildTime;
	}
	public Course() {
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getOfClass() {
		return ofClass;
	}
	public void setOfClass(String ofClass) {
		this.ofClass = ofClass;
	}
	public Date getBuildTime() {
		return buildTime;
	}
	public void setBuildTime(Date buildTime) {
		this.buildTime = buildTime;
	}
	
	

}
