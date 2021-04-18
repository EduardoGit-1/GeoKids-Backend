package com.example.GeoKidsBackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.GeoKidsBackend.model.Classification;


@Repository
public interface ClassificationRepository extends MongoRepository<Classification, String> {

	Classification findByUserIdAndDestination_placeID(String userID, String placeID);
}
