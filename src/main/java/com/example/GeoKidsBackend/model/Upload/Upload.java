package com.example.GeoKidsBackend.model.Upload;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import com.example.GeoKidsBackend.model.GeoKid;
import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Upload {
	
	@Id
	private String id;
	
	private Destination destination;
	
	@JsonIgnore
	private GeoKid user;
	
	private ArrayList<UploadData> uploads = new ArrayList<>();
	
	private Upload() {}
	
	public Upload(Destination destination, GeoKid user) {
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
	public GeoKid getUser() {
		return user;
	}
	public void setUser(GeoKid user) {
		this.user = user;
	}

	public ArrayList<UploadData> getUploads() {
		return uploads;
	}

	public void setUploads(ArrayList<UploadData> uploads) {
		this.uploads = uploads;
	}

	
	/*public ArrayList<Image> getImages() {
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

	public ArrayList<String> getAudios() {
		return audios;
	}

	public void setAudios(ArrayList<String> audios) {
		this.audios = audios;
	}
		public ArrayList<String> getTexts() {
		return texts;
	}

	public void setTexts(ArrayList<String> texts) {
		this.texts = texts;
	}
	*/




	
	
		

}
