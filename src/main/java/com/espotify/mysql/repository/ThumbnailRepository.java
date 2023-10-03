package com.espotify.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espotify.mysql.model.Thumbnail;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Integer> {
	
}
