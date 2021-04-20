package com.example.GeoKidsBackend.payload;

public class UploadPayloadResponse2 {

	private String type;
	private String data;
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
	
	
}
