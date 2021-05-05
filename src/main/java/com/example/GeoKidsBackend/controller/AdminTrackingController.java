package com.example.GeoKidsBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.GeoKidsBackend.model.Tracking.Route;
import com.example.GeoKidsBackend.repository.RouteRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/web/tracking")
public class AdminTrackingController {

	@Autowired
	RouteRepository routeRepository;

	
	@GetMapping("/getRoutes")
	@PreAuthorize("hasRole('ADMIN')")
	public List<?> getRoutes(@RequestParam String city) {
		List<Route> routes = routeRepository.findAll();
		return routes;
	}


}