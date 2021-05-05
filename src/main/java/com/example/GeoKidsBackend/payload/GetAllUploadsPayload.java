package com.example.GeoKidsBackend.payload;

import java.util.ArrayList;

import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.model.Upload.UploadData;

public class GetAllUploadsPayload {

	private Destination destination;
	private ArrayList<UploadData> uploads;
	
	public GetAllUploadsPayload() {}
	public GetAllUploadsPayload(Destination destination, ArrayList<UploadData> uploads) {

		this.destination = destination;
		this.uploads = uploads;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public ArrayList<UploadData> getUploads() {
		return uploads;
	}
	public void setUploads(ArrayList<UploadData> uploads) {
		this.uploads = uploads;
	}
	
	
	
	
}
