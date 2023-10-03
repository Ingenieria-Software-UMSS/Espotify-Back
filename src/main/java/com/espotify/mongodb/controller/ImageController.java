package com.espotify.mongodb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.model.Image;
import com.espotify.mongodb.service.IImageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/storage")
public class ImageController {
	@Autowired
	private IImageService imageService;

	@PostMapping(value = "/image/add")
	public String addImage(@RequestParam("title") String title, @RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
		String id = imageService.addImage(title, multipartFile);

		return "/storage/image/" + id;
	}

	@GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] getImage(@PathVariable String id, Model model) {
		Image image = imageService.getImage(id);

		return image.getImage().getData();
	}
}
