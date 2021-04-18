package com.example.GeoKidsBackend.model.Tracking;

public class Destination {

	private String designation;
	private String placeID = null;
	private float latitude;
	private float longitude;

	
	
	public Destination() {}
	public Destination(String designation, float latitude, float longitude, String placeID) {
		this.designation = designation;
		this.latitude = latitude;
		this.longitude = longitude;
		this.placeID = placeID;
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
	public String getPlaceID() {
		return placeID;
	}
	public void setPlaceID(String placeID) {
		this.placeID = placeID;
	}
	
	
}
