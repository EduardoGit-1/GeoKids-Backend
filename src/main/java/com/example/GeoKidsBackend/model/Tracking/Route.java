package com.example.GeoKidsBackend.model.Tracking;

import org.springframework.data.annotation.Id;

import com.example.GeoKidsBackend.model.GeoKid;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Route {
	
	@Id
	private String id;
	
	@JsonIgnore()
	private GeoKid user;
	
	private Origin origin;
	private Destination destination;
	private float distance;
	private float duration;
	
	public Route() {}
	
	public Route(GeoKid user, Origin origin, Destination destination, float distance, float duration) {
		this.user = user;
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
		this.duration = duration;
	}

	
	public GeoKid getUser() {
		return user;
	}

	public void setUser(GeoKid user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
