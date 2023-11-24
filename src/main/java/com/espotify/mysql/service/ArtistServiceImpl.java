package com.espotify.mysql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.Artist;
import com.espotify.mysql.model.Song;
import com.espotify.mysql.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {
	@Autowired
	private ArtistRepository artistRepository;

	@Override
	public Artist saveArtist(Artist artist) {
		Artist artistTmp = getArtistByName(artist.getArtistName());

		if (artistTmp == null)
			artist = artistRepository.save(artist);
		else
			artist = artistTmp;
		
		return artist;
	}

	@Override
	public Artist getArtistById(Integer artistId) {
		return artistRepository.findById(artistId).orElse(null);
	}

	@Override
	public List<Artist> getAllArtists() {
		return artistRepository.findAll();
	}

	@Override
	public void deleteArtist(Artist artist) {
		artistRepository.delete(artist);
	}

	@Override
	public Artist getArtistByName(String artistName) {
		return artistRepository.findByArtistName(artistName);
	}

	@Override
	public List<Song> getSongsByNewArtist() {
		List<Artist> artistList = artistRepository.getAllNewArtists();
		List<Song> songSet = new ArrayList<>();

		artistList.forEach(artist -> {
			songSet.addAll(artist.getSongList());
		});

		return songSet;
	}
	
}
