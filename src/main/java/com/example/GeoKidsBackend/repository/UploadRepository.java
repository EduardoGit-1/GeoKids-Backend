package com.example.GeoKidsBackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.example.GeoKidsBackend.model.Upload.Upload;

public interface UploadRepository extends MongoRepository<Upload, String>{

	Upload findByUserIdAndDestination_placeID(String userID, String placeID);
}
