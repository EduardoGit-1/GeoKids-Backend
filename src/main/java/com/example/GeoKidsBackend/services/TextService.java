package com.example.GeoKidsBackend.services;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GeoKidsBackend.model.GeoKid;
import com.example.GeoKidsBackend.model.Tracking.Destination;
import com.example.GeoKidsBackend.model.Upload.Upload;
import com.example.GeoKidsBackend.model.Upload.UploadData;
import com.example.GeoKidsBackend.repository.UploadRepository;
import com.example.GeoKidsBackend.repository.GeoKidsRepository;

@Service
public class TextService {

	@Autowired
	private UploadRepository uploadRepository;
	@Autowired
	private GeoKidsRepository geoKidsRepository;
	
    public String addText(Destination destination, String userID, String text) throws IOException {         
        Upload upload = uploadRepository.findByUserIdAndDestination_placeID(userID, destination.getPlaceID());
        String uploadID = UUID.randomUUID().toString();
        UploadData uploadData = new UploadData(uploadID, text, "text");
        if(upload == null) {
        	GeoKid user = geoKidsRepository.findById(userID).orElseThrow();
            Upload newUpload = new Upload(destination, user);
            newUpload.getUploads().add(uploadData);
            uploadRepository.save(newUpload);
            return text;
        }
    	upload.getUploads().add(uploadData);
    	uploadRepository.save(upload);
    	return text;

    }
}
