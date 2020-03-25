package com.ncu.entity;

import java.util.Date;

public class CheckKey {
	private int checkID;
	private int courseID;
	private String checkKey;
	private int state;
	private Date checkTime;
	private String latitude;
	private String longitude;
	public CheckKey(int courseID, String checkKey, int state, Date checkTime,String latitude,String longitude) {
		this.courseID = courseID;
		this.checkKey = checkKey;
		this.state = state;
		this.checkTime = checkTime;
		this.latitude=latitude;
		this.longitude=longitude;
	}
	public CheckKey() {
	}
    
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
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
	public String getCheckKey() {
		return checkKey;
	}
	public void setCheckKey(String checkKey) {
		this.checkKey = checkKey;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	
	
	

}
