package com.espotify.mysql.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.espotify.dto.UserDto;
import com.espotify.mysql.dto.PlayHistorySongDto;
import com.espotify.mysql.model.PlayHistorySong;
import com.espotify.mysql.model.Song;
import com.espotify.mysql.model.User;
import com.espotify.mysql.service.PlayHistorySongService;
import com.espotify.mysql.service.SongService;
import com.espotify.mysql.service.UserService;

@RestController
public class PlayHistorySongController {
	@Autowired
	private PlayHistorySongService playHistorySongService;
	@Autowired
	private SongService songService;
	@Autowired
	private UserService userService;

	// POST
	@PostMapping("/play-history")
	@ResponseBody
	public PlayHistorySongDto savePlayHistorySong(Authentication authentication, 
			@RequestBody Song song) {
			// @RequestBody Map<String, Object> jsonData) {
		// Song song = songService.getSongById((Integer) jsonData.get("songId"));
		song = songService.getSongById(song.getSongId());

		if (song == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found");

		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());
		PlayHistorySong playHistorySong = new PlayHistorySong();

		playHistorySong.setPlayDate(new Date());
		playHistorySong.setPlayHistory(user.getPlayHistory());
		playHistorySong.setSong(song);

		return playHistorySongService.savePlayHistorySong(playHistorySong).toDto();
	}

	// PUT
	@PutMapping("/play-history/{playHistorySongId}")
	@ResponseBody
	public PlayHistorySongDto updatePlayHistorySong(Authentication authentication,
			@PathVariable Long playHistorySongId) {
		PlayHistorySong playHistorySong = playHistorySongService.getPlayHistorySongById(playHistorySongId);

		if (playHistorySong == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");

		playHistorySong.setPlayDate(new Date());

		return playHistorySongService.savePlayHistorySong(playHistorySong).toDto();
	}

	// DELETE
	@DeleteMapping("/play-history/{playHistorySongId}")
	public ResponseEntity<Map<String, Boolean>> deletePlayHistorySong(Authentication authentication,
			@PathVariable Long playHistorySongId) {
		PlayHistorySong playHistorySong = playHistorySongService.getPlayHistorySongById(playHistorySongId);

		if (playHistorySong == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
		
		playHistorySongService.deletePlayHistorySong(playHistorySong);

		Map<String, Boolean> response = new HashMap<>();

		response.put("deleted", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}

	// GET
	@GetMapping("/play-history")
	@ResponseBody
	public List<PlayHistorySongDto> getPlayHistorySongList(Authentication authentication) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());
		List<PlayHistorySongDto> playHistorySongDtos = new ArrayList<>();
		playHistorySongService.getPlayHistorySongList(user.getPlayHistory()
				.getPlayHistoryId()).forEach(song -> playHistorySongDtos.add(song.toDto()));

		return playHistorySongDtos;
	}
}
