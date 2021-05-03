package com.example.GeoKidsBackend.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.GeoKidsBackend.model.GeoKid;

@Repository
public interface GeoKidsRepository extends MongoRepository<GeoKid, String> {
	Optional<GeoKid> findById(String id);
	Boolean existsByNickname(String nickname);
}
