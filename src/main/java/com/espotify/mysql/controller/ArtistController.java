package com.espotify.mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.espotify.mysql.model.Artist;
import com.espotify.mysql.service.ArtistService;

@RestController
public class ArtistController {
	@Autowired
	private ArtistService artistService;

	@PostMapping(value = "/artist")
	@ResponseBody
	public Artist addArtist(Authentication authentication, @RequestBody Artist artist) {
		return artistService.saveArtist(artist);
	}

	@GetMapping(value = "/artist/{songId}")
	@ResponseBody
	public Artist getArtist(Authentication authentication, @PathVariable Integer songId) {
		return artistService.getArtistById(songId);
	}

	@GetMapping(value = "/artist")
	@ResponseBody
	public List<Artist> getAllArtists(Authentication authentication) {
		return artistService.getAllArtists();
	}
}
