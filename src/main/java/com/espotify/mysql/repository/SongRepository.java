package com.espotify.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espotify.mysql.model.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {
	
}
