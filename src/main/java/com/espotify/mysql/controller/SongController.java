package com.espotify.mysql.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.espotify.dto.UserDto;
import com.espotify.mongodb.service.AudioService;
import com.espotify.mongodb.service.ImageService;
import com.espotify.mysql.model.Artist;
import com.espotify.mysql.model.Song;
import com.espotify.mysql.model.Thumbnail;
import com.espotify.mysql.model.User;
import com.espotify.mysql.service.ArtistService;
import com.espotify.mysql.service.SongService;
import com.espotify.mysql.service.ThumbnailService;
import com.espotify.mysql.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class SongController {
	@Autowired
	private SongService songService;
	@Autowired
	private ThumbnailService thumbnailService;
	@Autowired
	private ArtistService artistService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private AudioService audioService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private UserService userService;

	@PostMapping(value = "/song/add-all")
	@ResponseBody
	public Song addSong(
			Authentication authentication,
			@RequestParam("jsonSong") String jsonSong,
			@RequestParam("jsonArtist") String jsonArtist,
			@RequestParam("image") MultipartFile image,
			@RequestParam("audio") MultipartFile audio) throws IOException {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());

		Song song = objectMapper.readValue(jsonSong, Song.class);
		Thumbnail thumbnail = new Thumbnail();
		Artist artist = objectMapper.readValue(jsonArtist, Artist.class);

		String imageId = imageService.addImage(image);
		String audioId = audioService.addAudio(audio);
		String httpHost = "http://" + httpServletRequest.getHeader("host");

		thumbnail.setFilename(image.getOriginalFilename());
		thumbnail.setThumbnailUrl(httpHost + "/storage/image/" + imageId);

		thumbnail = thumbnailService.saveThumbnail(thumbnail);
		artist = artistService.saveArtist(artist);

		song.setArtist(artist);
		song.setThumbnail(thumbnail);
		song.setUploadDate(new Date());
		song.setSongUrl(httpHost + "/storage/audio/" + audioId);
		song.setNumberOfLikes(0);
		song.setNumberOfDislikes(0);
		song.setUser(user);

		return songService.saveSong(song);
	}

	@PostMapping(value = "/song")
	@ResponseBody
	public Song addSong(
			Authentication authentication,
			@RequestParam("jsonSong") String jsonSong,
			@RequestParam("jsonThumbnail") String jsonThumbnail,
			@RequestParam("jsonArtist") String jsonArtist,
			@RequestParam("audio") MultipartFile audio) throws IOException {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());
		Song song = objectMapper.readValue(jsonSong, Song.class);
		Thumbnail thumbnail = objectMapper.readValue(jsonThumbnail, Thumbnail.class);
		Artist artist = objectMapper.readValue(jsonArtist, Artist.class);
		String audioId = audioService.addAudio(audio);
		String httpHost = "http://" + httpServletRequest.getHeader("host");

		song.setArtist(artistService.getArtistById(artist.getArtistId()));
		song.setThumbnail(thumbnailService.getThumbnailById(thumbnail.getThumbnailId()));
		song.setUploadDate(new Date());
		song.setSongUrl(httpHost + "/storage/audio/" + audioId);
		song.setUser(user);

		return songService.saveSong(song);
	}

	@GetMapping(value = "/song/{songId}")
	@ResponseBody
	public Song getSong(@PathVariable Integer songId) {
		return songService.getSongById(songId);
	}

	@GetMapping(value = "/song")
	@ResponseBody
	public List<Song> getAllSongs() {
		return songService.getAllSongs();
	}

	@GetMapping(value = "/new-songs")
	@ResponseBody
	public List<Song> getAllNewSongs() {
		return songService.getAllNewSongs();
	}

	@PutMapping(value = "/song/{songId}")
	@ResponseBody
	public Song updateSong(Authentication authentication, @PathVariable Integer songId, @RequestBody Song song) {
		Song updatedSong = songService.getSongById(songId);

		if (updatedSong == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found");

		updatedSong.setSongTitle(song.getSongTitle());
		updatedSong.setSongAlbum(song.getSongAlbum());
		updatedSong.setSongDuration(song.getSongDuration());
		// updatedSong.setSongDescription(song.getSongDescription());
		updatedSong.setSongUrl(song.getSongUrl());

		if (song.getThumbnail() != null)
			updatedSong.setThumbnail(song.getThumbnail());

		if (song.getArtist() != null)
			updatedSong.setArtist(song.getArtist());

		return songService.saveSong(updatedSong);
	}

	@GetMapping("/user-song")
	@ResponseBody
	public List<Song> getSongByUser(Authentication authentication) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());
		return songService.getSongsByUserId(user.getId());
	}
}
