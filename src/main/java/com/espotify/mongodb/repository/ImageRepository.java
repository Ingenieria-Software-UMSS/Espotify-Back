package com.espotify.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.espotify.mongodb.model.Image;

public interface ImageRepository extends MongoRepository<Image, String> {
	
}
