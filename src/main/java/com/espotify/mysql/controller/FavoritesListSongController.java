package com.espotify.mysql.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.espotify.dto.UserDto;
import com.espotify.mysql.dto.FavoritesListSongDto;
import com.espotify.mysql.model.FavoritesListSong;
import com.espotify.mysql.model.Song;
import com.espotify.mysql.model.User;
import com.espotify.mysql.service.FavoritesListSongService;
import com.espotify.mysql.service.SongService;
import com.espotify.mysql.service.UserService;

@RestController
public class FavoritesListSongController {
	@Autowired
	private FavoritesListSongService favoritesListSongService;
	@Autowired
	private SongService songService;
	@Autowired
	private UserService userService;

	@PostMapping("/favorites-list")
	@ResponseBody
	public FavoritesListSongDto saveFavoritesListSong(Authentication authentication, @RequestBody Song song) {
		song = songService.getSongById(song.getSongId());

		if (song == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Song not found");
		
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());
		FavoritesListSong favoritesListSong = new FavoritesListSong();

		favoritesListSong.setFavoritesList(user.getFavoritesList());
		favoritesListSong.setSong(song);

		return favoritesListSongService.saveFavoritesListSong(favoritesListSong).toDto();
	}

	@DeleteMapping("/favorites-list/{favoriteListSongId}")
	public ResponseEntity<Map<String, Boolean>> deleteFavoriteListSong(Authentication authentication, @PathVariable Long favoriteListSongId) {
		FavoritesListSong favoritesListSong = favoritesListSongService.getFavoritesListSongById(favoriteListSongId);

		if (favoritesListSong == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
		
		favoritesListSongService.deleteFavoritesListSong(favoritesListSong);
		
		Map<String, Boolean> response = new HashMap<>();

		response.put("deleted", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/favorites-list")
	@ResponseBody
	public List<FavoritesListSongDto> getAllFavoritesListSong(Authentication authentication) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());
		List<FavoritesListSongDto> favoritesListSongDtos = new ArrayList<>();

		favoritesListSongService.getAllFavoritesListSong(user.getFavoritesList().getFavoritesListId()).forEach(song -> favoritesListSongDtos.add(song.toDto()));

		return favoritesListSongDtos;
	}
}
