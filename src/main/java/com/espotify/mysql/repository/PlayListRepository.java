package com.espotify.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espotify.mysql.model.PlayList;

public interface PlayListRepository extends JpaRepository<PlayList, Integer> {
	
}
