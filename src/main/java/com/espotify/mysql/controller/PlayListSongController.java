package com.espotify.mysql.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.espotify.mysql.model.PlayListSong;
import com.espotify.mysql.service.PlayListSongService;

@RestController
public class PlayListSongController {

	@Autowired
	private PlayListSongService playListSongService;

	@PostMapping(value = "/play-list-song")
	@ResponseBody
	public PlayListSong addPlayList(@RequestBody PlayListSong playListSong) {
		playListSong.setRegistrationDate(new Date());

		return playListSongService.savePlayListSong(playListSong);
	}

	@GetMapping(value = "/play-list-song/{playListSongId}")
	@ResponseBody
	public PlayListSong getPlayList(@PathVariable Integer playListSongId) {
		return playListSongService.getPlayListSongById(playListSongId);
	}

	@GetMapping(value = "/play-list-song")
	@ResponseBody
	public List<PlayListSong> getAllPlayLists() {
		return playListSongService.getAllPlayListSongs();
	}

	@DeleteMapping(value = "play-list-song/{playListSongId}")
	public ResponseEntity<Map<String, Boolean>> deletePlayListSong(@PathVariable Integer playListSongId) {
		PlayListSong playListSong = playListSongService.getPlayListSongById(null);

		playListSongService.deletePlayListSong(playListSong);

		Map<String, Boolean> response = new HashMap<>();

		response.put("deleted", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}
}
