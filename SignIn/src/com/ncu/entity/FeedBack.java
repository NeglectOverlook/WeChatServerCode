package com.ncu.entity;

import java.util.Date;

public class FeedBack {
	private String openID;
	private String suggestCon;
	private Date backTime;
	public FeedBack(String openID, String suggestCon, Date backTime) {
		super();
		this.openID = openID;
		this.suggestCon = suggestCon;
		this.backTime = backTime;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public String getSuggestCon() {
		return suggestCon;
	}
	public void setSuggestCon(String suggestCon) {
		this.suggestCon = suggestCon;
	}
	public Date getBackTime() {
		return backTime;
	}
	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}
	

}
