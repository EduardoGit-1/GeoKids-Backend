package com.example.GeoKidsBackend.model.Tracking;

public class Origin {

	private String designation;
	private float latitude;
	private float longitude;
	public Origin() {}
	public Origin(String designation, float latitude, float longitude) {
		this.designation = designation;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	
}
