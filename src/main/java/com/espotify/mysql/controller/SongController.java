package com.espotify.mysql.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.service.IAudioService;
import com.espotify.mysql.model.Artist;
import com.espotify.mysql.model.Song;
import com.espotify.mysql.model.Thumbnail;
import com.espotify.mysql.service.IArtistService;
import com.espotify.mysql.service.ISongService;
import com.espotify.mysql.service.IThumbnailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/espotify")
public class SongController {
	@Autowired
	private ISongService songService;
	@Autowired
	private IArtistService artistService;
	@Autowired
	private IThumbnailService thumbnailService;
	@Autowired
	private IAudioService audioService;

	@PostMapping(value = "/audio/add")
	public Song addSong(@RequestParam("title") String title, @RequestParam("album") String album, @RequestParam("artist_id") String artistId, @RequestParam("thumbnail_id") String thumbnailId, @RequestParam("file") MultipartFile multipartFile) throws IOException {
		Artist artist = artistService.getArtistById(Integer.valueOf(artistId));
		Thumbnail thumbnail = thumbnailService.getThumbnailById(Integer.valueOf(thumbnailId));
		String songId = audioService.addAudio(title, multipartFile);
		String songUrl = "http://localhost:8080/storage/audio/" + songId;
		Song song = new Song(null, title, album, 0, new Date(), songUrl, artist, thumbnail);
		return songService.addSong(song);
	}

	@GetMapping(value = "/audio/{id}")
	public Song getSong(@PathVariable String songId) {
		return songService.getSongById(Integer.valueOf(songId));
	}

	@GetMapping(value = "/audios")
	public List<Song> getSongList() {
		return songService.getSongList();
	}
}
