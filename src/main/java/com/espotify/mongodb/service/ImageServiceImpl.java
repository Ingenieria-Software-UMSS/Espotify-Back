package com.espotify.mongodb.service;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.model.Image;
import com.espotify.mongodb.repository.ImageRepository;

@Service
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageRepository imageRepository;

	@Override
	public String addImage(MultipartFile multipartFile) throws IOException {
		Image image = new Image();

		image.setImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));

		image = imageRepository.insert(image);

		return image.getId();
	}

	@Override
	public Image getImage(String id) {
		return imageRepository.findById(id).get();
	}
}
