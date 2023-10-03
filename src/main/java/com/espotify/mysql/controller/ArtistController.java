package com.espotify.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espotify.mysql.model.Artist;
import com.espotify.mysql.service.IArtistService;

@RestController
@RequestMapping("/espotify")
public class ArtistController {
	@Autowired
	private IArtistService artistService;

	@PostMapping(value = "/artist/add")
	public Artist addArtist(@RequestBody Artist artist) {
		return artistService.addArtist(artist);
	}

	@GetMapping(value = "/artist/{id}")
	public Artist getArtist(@PathVariable String id) {
		return artistService.getArtistById(Integer.valueOf(id));
	}
}
