package com.espotify.mongodb.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.model.Image;

public interface IImageService {
	public String addImage(String title, MultipartFile multipartFile) throws IOException;

	public Image getImage(String id);
}
