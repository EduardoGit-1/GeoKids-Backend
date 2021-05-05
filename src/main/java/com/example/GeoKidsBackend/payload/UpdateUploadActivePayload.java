package com.example.GeoKidsBackend.payload;

public class UpdateUploadActivePayload {

	private String id;
	private Boolean active;
	public UpdateUploadActivePayload() {}
	public UpdateUploadActivePayload(String id, Boolean active) {

		this.id = id;
		this.active = active;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	
}
