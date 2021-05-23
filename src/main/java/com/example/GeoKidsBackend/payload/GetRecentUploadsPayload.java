package com.example.GeoKidsBackend.payload;


import java.util.ArrayList;

import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.model.Upload.UploadData;

public class GetRecentUploadsPayload {

	private Destination destination;
	private UploadData upload;
	
	
	public GetRecentUploadsPayload() {}
	public GetRecentUploadsPayload(Destination destination, UploadData upload) {
		this.destination = destination;
		this.upload = upload;
	}
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public UploadData getUpload() {
		return upload;
	}
	public void setUpload(UploadData upload) {
		this.upload = upload;
	}
	

	
	
	
	
}
