package com.espotify.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espotify.mysql.model.PlayHistorySong;

public interface PlayHistorySongRepository extends JpaRepository<PlayHistorySong, Long> {
	
}
