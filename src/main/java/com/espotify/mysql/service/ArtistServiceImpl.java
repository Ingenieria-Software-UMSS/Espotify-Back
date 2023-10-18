package com.espotify.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.Artist;
import com.espotify.mysql.repository.ArtistRepository;

@Service
public class ArtistServiceImpl implements ArtistService {
	@Autowired
	private ArtistRepository artistRepository;

	@Override
	public Artist addArtist(Artist artist) {
		return artistRepository.save(artist);
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
	public Artist updateArtist(Artist artist) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateArtist'");
	}

	@Override
	public Artist deleteArtist(Integer artistId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deleteArtist'");
	}
	
}
