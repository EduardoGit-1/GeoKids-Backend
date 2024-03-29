package com.example.GeoKidsBackend.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.GeoKidsBackend.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	  Optional<User> findByEmail(String email);

	  Boolean existsByEmail(String email);
}