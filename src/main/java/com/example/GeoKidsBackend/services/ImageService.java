package com.example.GeoKidsBackend.services;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.GeoKidsBackend.model.GeoKid;
import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.model.Upload.Image;
import com.example.GeoKidsBackend.model.Upload.Upload;
import com.example.GeoKidsBackend.model.Upload.UploadData;
import com.example.GeoKidsBackend.repository.UploadRepository;
import com.example.GeoKidsBackend.repository.GeoKidsRepository;

@Service
public class ImageService {

	@Autowired
	private UploadRepository uploadRepository;
	@Autowired
	private GeoKidsRepository geoKidsRepository;
	
    public String addImage(Destination destination, String userID, MultipartFile file) throws IOException { 
        Image image = new Image(); 
        image.setImage(
          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        
		String base64 = "data:image/png;base64,";
		String imageString = base64 + Base64.getEncoder().encodeToString(image.getImage().getData());
        Upload upload = uploadRepository.findByUserIdAndDestination_placeID(userID, destination.getPlaceID());
        String uploadID = UUID.randomUUID().toString();
        UploadData uploadData = new UploadData(uploadID, imageString, "image");
        if(upload == null) {
        	GeoKid user = geoKidsRepository.findById(userID).orElseThrow();
            Upload newUpload = new Upload(destination, user);
            newUpload.getUploads().add(uploadData);
            uploadRepository.save(newUpload);
            return imageString;
        }
    	upload.getUploads().add(uploadData);
    	uploadRepository.save(upload);
    	return imageString;

    }
    
}
