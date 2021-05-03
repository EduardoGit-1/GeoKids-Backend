package com.example.GeoKidsBackend.services;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.GeoKidsBackend.model.GeoKid;
import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.model.Upload.Image;
import com.example.GeoKidsBackend.model.Upload.Upload;
import com.example.GeoKidsBackend.repository.UploadRepository;
import com.example.GeoKidsBackend.repository.GeoKidsRepository;

@Service
public class ImageService {

	@Autowired
	private UploadRepository uploadRepository;
	@Autowired
	private GeoKidsRepository geoKidsRepository;
	
    public Image addImage(Destination destination, String userID, MultipartFile file) throws IOException { 
        Image image = new Image(); 
        image.setImage(
          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        
        Upload upload = uploadRepository.findByUserIdAndDestination_placeID(userID, destination.getPlaceID());
        
        if(upload == null) {
        	GeoKid user = geoKidsRepository.findById(userID).orElseThrow();
            Upload newUpload = new Upload(destination, user);
            newUpload.getImages().add(image);
            uploadRepository.save(newUpload);
            return image;
        }
    	upload.getImages().add(image);
    	uploadRepository.save(upload);
    	return image;

    }
    
}
