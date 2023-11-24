package com.espotify.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.espotify.mysql.model.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

	@Query("SELECT a FROM Artist a WHERE a.artistName = ?1")
	public Artist findByArtistName(String artistName);

	@Query("SELECT a FROM Artist a WHERE datediff(date(now()), date(a.updateDate)) <= 3")
	public List<Artist> getAllNewArtists();
}
