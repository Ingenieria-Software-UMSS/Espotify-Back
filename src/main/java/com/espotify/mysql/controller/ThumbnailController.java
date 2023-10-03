package com.espotify.mysql.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.service.IImageService;
import com.espotify.mysql.model.Thumbnail;
import com.espotify.mysql.service.IThumbnailService;

@RestController
@RequestMapping("/espotify")
public class ThumbnailController {
	@Autowired
	private IThumbnailService thumbnailService;
	@Autowired
	private IImageService imageService;

	@PostMapping(value = "/thumbnail/add")
	public ResponseEntity<Thumbnail> addThumbnail(@RequestParam("filename") String filename, @RequestParam("file") MultipartFile multipartFile) throws IOException {
		System.out.println(multipartFile.getContentType());
		
		String imageId = imageService.addImage(filename, multipartFile);
		String imageUrl = "http://localhost:8080/storage/image/" + imageId;
		Thumbnail thumbnail = thumbnailService.addThumbnail(new Thumbnail(null, filename, imageUrl));

		return ResponseEntity.ok().body(thumbnail);
	}

	@GetMapping(value = "/thumbnail/{thumbnailId}")
	public ResponseEntity<Thumbnail> getThumbnail(@PathVariable String thumbnailId) {
		return ResponseEntity.ok().body(thumbnailService.getThumbnailById(Integer.valueOf(thumbnailId)));
	}
}
