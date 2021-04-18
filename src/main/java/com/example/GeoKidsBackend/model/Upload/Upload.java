package com.example.GeoKidsBackend.model.Upload;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import com.example.GeoKidsBackend.model.User;
import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Upload {
	
	@Id
	private String id;
	
	private Destination destination;
	
	@JsonIgnore
	private User user;
	
	private ArrayList<Image> images = new ArrayList<>();
	private ArrayList<String> videos = new ArrayList<>();
	
	private Upload() {}
	
	public Upload(Destination destination, User user) {
		this.destination = destination;
		this.user = user;

	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ArrayList<Image> getImages() {
		return images;
	}
	public void setImages(ArrayList<Image> images) {
		this.images = images;
	}
	public ArrayList<String> getVideos() {
		return videos;
	}
	public void setVideos(ArrayList<String> videos) {
		this.videos = videos;
	}
		

}
