package com.example.GeoKidsBackend.model.Upload;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;

public class Image {

	@Id
	private String id;
	
	private Binary image;

	
	public Image() {}
	public Image(Binary image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Binary getImage() {
		return image;
	}

	public void setImage(Binary image) {
		this.image = image;
	}

		
	
}
