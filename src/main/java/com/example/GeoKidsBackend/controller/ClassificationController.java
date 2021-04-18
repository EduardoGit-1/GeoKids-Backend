package com.example.GeoKidsBackend.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GeoKidsBackend.model.Classification;
import com.example.GeoKidsBackend.model.User;
import com.example.GeoKidsBackend.payload.PostClassificationRequest;
import com.example.GeoKidsBackend.repository.ClassificationRepository;
import com.example.GeoKidsBackend.repository.UserRepository;

@RestController
@RequestMapping("/api/classification")
public class ClassificationController {
	@Autowired
	ClassificationRepository classificationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/saveClassification")
	public ResponseEntity<?> setAsFavorite(@Valid @RequestBody PostClassificationRequest postFavoriteLocationRequest){
		Classification c = classificationRepository.findByUserIdAndDestination_placeID(postFavoriteLocationRequest.getUserID(), postFavoriteLocationRequest.getDestination().getPlaceID());
		if(c == null) {
			User user = userRepository.findById(postFavoriteLocationRequest.getUserID()).orElseThrow();;
			Classification newC = new Classification(user, postFavoriteLocationRequest.getIsFavorite(), postFavoriteLocationRequest.getStars(), postFavoriteLocationRequest.getDestination(), postFavoriteLocationRequest.getAnswers());
			classificationRepository.save(newC);
			return ResponseEntity.ok().body(newC);
		}
		c.setFavorite(postFavoriteLocationRequest.getIsFavorite());
		c.setStars(postFavoriteLocationRequest.getStars());
		classificationRepository.save(c);
		return ResponseEntity.ok().body(c);
	}
	
	@PostMapping("/saveOpinion")
	public ResponseEntity<?> saveOpinion(@Valid @RequestBody PostClassificationRequest postFavoriteLocationRequest){
		Classification c = classificationRepository.findByUserIdAndDestination_placeID(postFavoriteLocationRequest.getUserID(), postFavoriteLocationRequest.getDestination().getPlaceID());
		if(c == null) {
			User user = userRepository.findById(postFavoriteLocationRequest.getUserID()).orElseThrow();;
			Classification newC = new Classification(user, postFavoriteLocationRequest.getIsFavorite(), postFavoriteLocationRequest.getStars(), postFavoriteLocationRequest.getDestination(), postFavoriteLocationRequest.getAnswers());
			classificationRepository.save(newC);
			return ResponseEntity.ok().body(newC);
		}
		c.setFavorite(postFavoriteLocationRequest.getIsFavorite());
		c.setStars(postFavoriteLocationRequest.getStars());
		c.setAnswers(postFavoriteLocationRequest.getAnswers());
		classificationRepository.save(c);
		return ResponseEntity.ok().body(c);
	}
}
