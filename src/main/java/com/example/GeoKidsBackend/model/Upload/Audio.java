package com.example.GeoKidsBackend.model.Upload;

import java.io.InputStream;

import org.springframework.data.annotation.Id;

public class Audio {
	@Id
	private String id;
	
	private InputStream stream;

	public Audio() {}
	
	public Audio(InputStream stream) {
		this.stream = stream;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public InputStream getStream() {
		return stream;
	}

	public void setStream(InputStream stream) {
		this.stream = stream;
	}
}
