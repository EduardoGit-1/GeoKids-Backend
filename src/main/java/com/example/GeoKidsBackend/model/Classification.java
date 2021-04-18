package com.example.GeoKidsBackend.model;


import org.springframework.data.annotation.Id;

import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Classification {

	@Id
	private String id;
	
	@JsonIgnore
	private User user;
	
	private boolean isFavorite;
	
	private int stars;
	
	private Destination destination;
	
	private Object answers;
	
	public Classification() {}

	

	public Classification(User user, boolean isFavorite, int stars, Destination destination, Object answers) {
		this.user = user;
		this.isFavorite = isFavorite;
		this.stars = stars;
		this.destination = destination;
		this.answers = answers;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonProperty("isFavorite")
	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}



	public Object getAnswers() {
		return answers;
	}



	public void setAnswers(Object answers) {
		this.answers = answers;
	}


	
	
	
	

	
	
}
