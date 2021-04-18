package com.example.GeoKidsBackend.payload;

import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.model.Tracking.Origin;

public class GetRoutesRequest {
	private String userID;
	
	public GetRoutesRequest() {}
	
	public GetRoutesRequest(String userID) {
		this.userID = userID;
	}


	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

}
