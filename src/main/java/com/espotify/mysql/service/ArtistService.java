package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Artist;

public interface ArtistService {
	public Artist addArtist(Artist artist);

	public Artist getArtistById(Integer artistId);

	public List<Artist> getArtistList();
}
