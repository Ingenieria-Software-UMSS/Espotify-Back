package com.espotify.mongodb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.model.Image;
import com.espotify.mongodb.service.ImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/storage")
public class ImageController {
	@Autowired
	private ImageService imageService;

	@PostMapping(value = "/image")
	@ResponseBody
	public String addImage(@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String id = imageService.addImage(multipartFile);

		return "/storage/image/" + id;
	}

	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] getImage(@PathVariable String id) {
		Image image = imageService.getImage(id);

		return image.getImage().getData();
	}
}
