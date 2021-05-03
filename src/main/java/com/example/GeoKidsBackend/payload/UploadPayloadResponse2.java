package com.example.GeoKidsBackend.payload;

import java.util.Date;

public class UploadPayloadResponse2 {

	private String type;
	private String data;
	private Date date = new Date();
	private Boolean isActive = true;
	public UploadPayloadResponse2() {}
	public UploadPayloadResponse2(String type, String data) {
		this.type = type;
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
