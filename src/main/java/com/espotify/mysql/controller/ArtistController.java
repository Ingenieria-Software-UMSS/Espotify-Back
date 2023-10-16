package com.espotify.mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.espotify.mysql.model.Artist;
import com.espotify.mysql.service.ArtistService;

@RestController
@RequestMapping("/espotify")
public class ArtistController {
	@Autowired
	private ArtistService artistService;

	@PostMapping(value = "/artist/add")
	@ResponseBody
	public Artist addArtist(@RequestBody Artist artist) {
		return artistService.addArtist(artist);
	}

	@GetMapping(value = "/artist/{songId}")
	@ResponseBody
	public Artist getArtist(@PathVariable Integer songId) {
		return artistService.getArtistById(songId);
	}

	@GetMapping(value = "/artists")
	@ResponseBody
	public List<Artist> getArtistList() {
		return artistService.getArtistList();
	}
}
