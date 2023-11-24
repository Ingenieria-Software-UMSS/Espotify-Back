package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Artist;
import com.espotify.mysql.model.Song;

public interface ArtistService {
	public Artist saveArtist(Artist artist);

	public Artist getArtistById(Integer artistId);

	public void deleteArtist(Artist artistId);

	public Artist getArtistByName(String artistName);

	public List<Artist> getAllArtists();

	public List<Song> getSongsByNewArtist();
}
