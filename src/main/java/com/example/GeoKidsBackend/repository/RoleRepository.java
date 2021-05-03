package com.example.GeoKidsBackend.repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.GeoKidsBackend.model.ERole;
import com.example.GeoKidsBackend.model.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	  Optional<Role> findByName(ERole name);
}