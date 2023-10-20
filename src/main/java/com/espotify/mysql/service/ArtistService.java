package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Artist;

public interface ArtistService {
	public Artist saveArtist(Artist artist);

	public Artist getArtistById(Integer artistId);

	public void deleteArtist(Artist artistId);

	public List<Artist> getAllArtists();
}
