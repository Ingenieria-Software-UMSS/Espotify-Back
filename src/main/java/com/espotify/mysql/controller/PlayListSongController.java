package com.espotify.mysql.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

		return playListSongService.addPlayListSong(playListSong);
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
}
