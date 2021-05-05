package com.example.GeoKidsBackend.model.Upload;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadData {
	
	private String uploadID;
	
	private String data;
	private String type;
	private Boolean isActive = true;
	private Date date = new Date();
	public UploadData() {}
	public UploadData(String uploadID, String data, String type) {
		this.uploadID = uploadID;
		this.data = data;
		this.type = type;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@JsonProperty("isActive")
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getUploadID() {
		return uploadID;
	}
	public void setUploadID(String uploadID) {
		this.uploadID = uploadID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	
}
