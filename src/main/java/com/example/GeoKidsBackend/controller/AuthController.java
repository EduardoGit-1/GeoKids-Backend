package com.example.GeoKidsBackend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.GeoKidsBackend.model.User;
import com.example.GeoKidsBackend.payload.ApiResponse;
import com.example.GeoKidsBackend.payload.SignUpRequest;
import com.example.GeoKidsBackend.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByNickname(signUpRequest.getNickname())) {
            return  ResponseEntity.badRequest().body(new ApiResponse(false, "Nickname is already taken!"));
        }
		User user = new User(signUpRequest.getNickname(), signUpRequest.getCharacterID());
		System.out.println(user);
		userRepository.save(user);
		return ResponseEntity.ok().body(user);
	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
}
