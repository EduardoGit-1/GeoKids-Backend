package com.example.GeoKidsBackend.payload;

import com.example.GeoKidsBackend.model.Tracking.Destination;

public class ClassificationFavouriteAgg {

	private Destination destination;
	private int favouriteCount;
	
	public ClassificationFavouriteAgg() {}
	public ClassificationFavouriteAgg(Destination destination, int favouriteCount) {

		this.destination = destination;
		this.favouriteCount = favouriteCount;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public int getFavouriteCount() {
		return favouriteCount;
	}
	public void setFavouriteCount(int favouriteCount) {
		this.favouriteCount = favouriteCount;
	}
	
	
}
