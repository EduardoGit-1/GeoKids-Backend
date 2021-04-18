package com.example.GeoKidsBackend.repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.GeoKidsBackend.model.Tracking.Route;

@Repository
public interface RouteRepository extends MongoRepository<Route, String>{

	ArrayList<Route> getByUserId(String userID);
	
}
