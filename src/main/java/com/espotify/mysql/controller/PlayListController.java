package com.espotify.mysql.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.espotify.dto.UserDto;
import com.espotify.mongodb.service.ImageService;
import com.espotify.mysql.model.PlayList;
import com.espotify.mysql.model.Thumbnail;
import com.espotify.mysql.model.User;
import com.espotify.mysql.service.PlayListService;
import com.espotify.mysql.service.ThumbnailService;
import com.espotify.mysql.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class PlayListController {
	
	@Autowired
	private PlayListService playListService;
	@Autowired
	private UserService userService;
	@Autowired
	private ThumbnailService thumbnailService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private HttpServletRequest httpServletRequest;
	@Autowired
	private ObjectMapper objectMapper;

	@PostMapping(value = "/play-list/add-all")
	@ResponseBody
	public PlayList addPlayList(Authentication authentication, 
			@RequestParam("jsonPlayList") String jsonPlayList, 
			@RequestParam("image") MultipartFile image) throws IOException {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());
		String imageId = imageService.addImage(image);
		String httpHost = "http://" + httpServletRequest.getHeader("host");
		Thumbnail thumbnail = new Thumbnail(null, 
				image.getOriginalFilename(), 
				httpHost + "/storage/image/" + imageId, null, null);
		thumbnail = thumbnailService.saveThumbnail(thumbnail);
		PlayList playList = objectMapper.readValue(jsonPlayList, PlayList.class);

		if (playList.getPlayListName() == null || playList.getPlayListDescription() == null
				|| playList.getPlayListName().equals("") || playList.getPlayListDescription().equals(""))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algunos campo estan vacios o son nulos");

		playList.setThumbnail(thumbnail);
		playList.setUser(user);
		playList.setCreationDate(new Date());

		return playListService.savePlayList(playList);
	}

	@PostMapping(value = "/play-list")
	@ResponseBody
	public PlayList addPlayList(Authentication authentication, @RequestBody PlayList playList) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());

		playList.setCreationDate(new Date());
		playList.setUser(user);
		
		return playListService.savePlayList(playList);
	}

	@GetMapping(value = "/play-list/{playListId}")
	@ResponseBody
	public PlayList getPlayList(Authentication authentication, @PathVariable Integer playListId) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());

		return playListService.getPlayListById(user, playListId);
	}

	@GetMapping(value = "/play-list")
	@ResponseBody
	public List<PlayList> getAllPlayLists(Authentication authentication) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());

		return playListService.getAllPlayLists(user);
	}

	@PutMapping(value = "play-list/{playListId}")
	@ResponseBody
	public PlayList updatePlayList(Authentication authentication, @PathVariable Integer playListId, @RequestBody PlayList playList) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());
		PlayList updatedPlayList = playListService.getPlayListById(user, playListId);

		updatedPlayList.setPlayListName(playList.getPlayListName());
		updatedPlayList.setPlayListDescription(playList.getPlayListDescription());

		if (playList.getThumbnail() != null)
			updatedPlayList.setThumbnail(playList.getThumbnail());

		return playListService.savePlayList(updatedPlayList);
	}

	@DeleteMapping(value = "play-list/{playListId}")
	public ResponseEntity<Map<String, Boolean>> deletePlayList(Authentication authentication, @PathVariable Integer playListId) {
		UserDto userDto = (UserDto) authentication.getPrincipal();
		User user = userService.findUserByEmail(userDto.getEmail());
		PlayList playList = playListService.getPlayListById(user, playListId);
		
		playListService.deletePlayList(user, playList);

		Map<String, Boolean> response = new HashMap<>();

		response.put("deleted", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}
}
