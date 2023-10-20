package com.espotify.mysql.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.service.ImageService;
import com.espotify.mysql.model.Thumbnail;
import com.espotify.mysql.service.ThumbnailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class ThumbnailController {
	@Autowired
	private ThumbnailService thumbnailService;
	@Autowired
	private ImageService imageService;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private HttpServletRequest httpServletRequest;

	@PostMapping(value = "/thumbnail")
	@ResponseBody
	public Thumbnail addThumbnail(
			@RequestParam("jsonThumbnail") String jsonImage,
			@RequestParam("image") MultipartFile image) throws IOException {
		Thumbnail thumbnail = objectMapper.readValue(jsonImage, Thumbnail.class);
		String imageId = imageService.addImage(image);
		String httpHost = "http://" + httpServletRequest.getHeader("host");
		
		thumbnail.setFilename(thumbnail.getFilename());
		thumbnail.setThumbnailUrl(httpHost + "/storage/image/" + imageId);

		return thumbnailService.saveThumbnail(thumbnail);
	}

	@GetMapping(value = "/thumbnail/{thumbnailId}")
	@ResponseBody
	public Thumbnail getThumbnail(@PathVariable Integer thumbnailId) {
		return thumbnailService.getThumbnailById(thumbnailId);
	}

	@GetMapping(value = "/thumbnail")
	@ResponseBody
	public List<Thumbnail> getAllThumbnails() {
		return thumbnailService.getAllThumbnails();
	}
}
