package com.example.GeoKidsBackend.payload;

import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.model.Tracking.Origin;

public class PostRouteRequest {
	private String userID;
	private Origin origin;
	private Destination destination;
	private float distance;
	private float duration;
	
	public PostRouteRequest() {}
	
	public PostRouteRequest(String userID, Origin origin, Destination destination, float distance, float duration) {
		this.userID = userID;
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
		this.duration = duration;
	}


	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public Origin getOrigin() {
		return origin;
	}
	public void setOrigin(Origin origin) {
		this.origin = origin;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	
	
}
