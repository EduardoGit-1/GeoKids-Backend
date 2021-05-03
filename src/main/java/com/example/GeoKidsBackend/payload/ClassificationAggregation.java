package com.example.GeoKidsBackend.payload;

import com.example.GeoKidsBackend.model.Tracking.Destination;

public class ClassificationAggregation {

	private Destination destination;
	private float avgRating;
	private int favouriteCount;
	private int totalRatings;
	
	public ClassificationAggregation() {}
	

	public ClassificationAggregation(Destination destination, int totalRatings, int favouriteCount, float avgRating) {

		this.destination = destination;
		this.avgRating = avgRating;
		this.totalRatings = totalRatings;
		this.favouriteCount = favouriteCount;
	}


	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}


	public float getAvgRating() {
		return this.avgRating;
	}


	public void setAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}


	public int getTotalRatings() {
		return totalRatings;
	}


	public void setTotalRatings(int totalRatings) {
		this.totalRatings = totalRatings;
	}


	public int getFavouriteCount() {
		return favouriteCount;
	}


	public void setFavouriteCount(int favouriteCount) {
		this.favouriteCount = favouriteCount;
	}


	
	


	
	
}
