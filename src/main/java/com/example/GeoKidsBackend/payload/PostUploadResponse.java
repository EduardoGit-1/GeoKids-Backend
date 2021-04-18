package com.example.GeoKidsBackend.payload;

import java.util.ArrayList;

import com.example.GeoKidsBackend.model.Tracking.Destination;

public class PostUploadResponse {

	private ArrayList<String> videos = new ArrayList<>();
	private ArrayList<String> images = new ArrayList<>();
	private Destination destination;
	
	public PostUploadResponse() {}
	public PostUploadResponse(Destination destination) {
		this.destination = destination;
	}
	public ArrayList<String> getVideos() {
		return videos;
	}
	public void setVideos(ArrayList<String> videos) {
		this.videos = videos;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	
	
}
