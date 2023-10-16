package com.espotify.mysql.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.service.AudioService;
import com.espotify.mongodb.service.ImageService;
import com.espotify.mysql.model.Artist;
import com.espotify.mysql.model.Song;
import com.espotify.mysql.model.Thumbnail;
import com.espotify.mysql.service.ArtistService;
import com.espotify.mysql.service.SongService;
import com.espotify.mysql.service.ThumbnailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/espotify")
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

	@PostMapping(value = "/song/add-all")
	@ResponseBody
	public Song addSong(
			@RequestParam("jsonSong") String jsonSong,
			@RequestParam("jsonThumbnail") String jsonThumbnail,
			@RequestParam("jsonArtist") String jsonArtist,
			@RequestParam("image") MultipartFile image,
			@RequestParam("audio") MultipartFile audio) throws IOException {
		Song song = objectMapper.readValue(jsonSong, Song.class);
		Thumbnail thumbnail = objectMapper.readValue(jsonThumbnail, Thumbnail.class);
		Artist artist = objectMapper.readValue(jsonArtist, Artist.class);
		String imageId = imageService.addImage(image);
		String audioId = audioService.addAudio(audio);
		String httpHost = "https://" + httpServletRequest.getHeader("host");

		thumbnail.setThumbnailUrl(httpHost + "/storage/image/" + imageId);

		thumbnail = thumbnailService.addThumbnail(thumbnail);
		artist = artistService.addArtist(artist);

		song.setArtist(artist);
		song.setThumbnail(thumbnail);
		song.setUploadDate(new Date());
		song.setSongUrl(httpHost + "/storage/audio/" + audioId);

		return songService.addSong(song);
	}

	@PostMapping(value = "/song/add")
	@ResponseBody
	public Song addSong(@RequestParam("jsonSong") String jsonSong,
			@RequestParam("jsonThumbnail") String jsonThumbnail,
			@RequestParam("jsonArtist") String jsonArtist,
			@RequestParam("audio") MultipartFile audio) throws IOException {
		Song song = objectMapper.readValue(jsonSong, Song.class);
		Thumbnail thumbnail = objectMapper.readValue(jsonThumbnail, Thumbnail.class);
		Artist artist = objectMapper.readValue(jsonArtist, Artist.class);
		String audioId = audioService.addAudio(audio);
		String httpHost = "https://" + httpServletRequest.getHeader("host");

		song.setArtist(artistService.getArtistById(artist.getArtistId()));
		song.setThumbnail(thumbnailService.getThumbnailById(thumbnail.getThumbnailId()));
		song.setUploadDate(new Date());
		song.setSongUrl(httpHost + "/storage/audio/" + audioId);

		return songService.addSong(song);
	}

	@GetMapping(value = "/song/{songId}")
	@ResponseBody
	public Song getSong(@PathVariable Integer songId) {
		return songService.getSongById(songId);
	}

	@GetMapping(value = "/songs")
	@ResponseBody
	public List<Song> getSongList() {
		return songService.getSongList();
	}
}
