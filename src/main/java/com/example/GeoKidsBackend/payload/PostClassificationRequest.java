package com.example.GeoKidsBackend.payload;


import com.example.GeoKidsBackend.model.Tracking.Destination;

public class PostClassificationRequest {

	private String userID;
	private Destination destination;
	private int stars;
	private Boolean isFavorite;
	private Object answers;

	public PostClassificationRequest() {}

	public PostClassificationRequest(String userID, Destination destination, int stars, Boolean isFavorite, Object answers) {
		this.userID = userID;
		this.destination = destination;
		this.stars = stars;
		this.isFavorite = isFavorite;
		this.answers = answers;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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

	public Boolean getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(Boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public Object getAnswers() {
		return answers;
	}

	public void setAnswers(Object answers) {
		this.answers = answers;
	}


	
	

	
	
}
