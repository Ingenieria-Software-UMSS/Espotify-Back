package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Artist;

public interface ArtistService {
	public Artist addArtist(Artist artist);

	public Artist getArtistById(Integer artistId);

	public Artist updateArtist(Artist artist);

	public Artist deleteArtist(Integer artistId);

	public List<Artist> getAllArtists();
}
