package com.example.GeoKidsBackend.payload;

import com.example.GeoKidsBackend.model.Tracking.Destination;

public class ClassificationTotalRatingsAgg {
	
	private int totalRatings;
	private Destination destination;
	
	public ClassificationTotalRatingsAgg() {}
	
	public ClassificationTotalRatingsAgg(int totalRatings, Destination destination) {
		this.totalRatings = totalRatings;
		this.destination = destination;
	}

	public int getTotalRatings() {
		return totalRatings;
	}

	public void setTotalRatings(int totalRatings) {
		this.totalRatings = totalRatings;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	
	
	

}
