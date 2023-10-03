package com.espotify.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espotify.mysql.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
	
}
