package com.example.GeoKidsBackend.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GeoKidsBackend.model.User;
import com.example.GeoKidsBackend.model.Tracking.Route;
import com.example.GeoKidsBackend.payload.GetRoutesRequest;
import com.example.GeoKidsBackend.payload.PostRouteRequest;
import com.example.GeoKidsBackend.repository.RouteRepository;
import com.example.GeoKidsBackend.repository.UserRepository;

@RestController
@RequestMapping("/api/tracking")
public class TrackingController {

	@Autowired
	RouteRepository routeRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/saveRoute")
	public ResponseEntity<?> registerRoute(@Valid @RequestBody PostRouteRequest postRouteRequest) {
		User user = userRepository.findById(postRouteRequest.getUserID())
    			.orElseThrow();
		
		Route route = new Route(user, postRouteRequest.getOrigin(), postRouteRequest.getDestination(), postRouteRequest.getDistance(), postRouteRequest.getDuration());
		routeRepository.save(route);
		return ResponseEntity.ok().body(route);
	}
	
	@PostMapping("/getRoutes")
	public ResponseEntity<?> getRoutes(@Valid @RequestBody GetRoutesRequest getRoutesRequest) {
		User user = userRepository.findById(getRoutesRequest.getUserID())
    			.orElseThrow();
		
		ArrayList<Route> routes = routeRepository.getByUserId(getRoutesRequest.getUserID());
		return ResponseEntity.ok().body(routes);
	}
}
