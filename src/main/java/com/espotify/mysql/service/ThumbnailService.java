package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Thumbnail;

public interface ThumbnailService {
	public Thumbnail getThumbnailById(Integer thumbnailId);

	public List<Thumbnail> getAllThumbnails();

	public Thumbnail addThumbnail(Thumbnail thumbnail);

	public Thumbnail updateThumbnail(Thumbnail thumbnail);

	public Thumbnail deleteThumbnail(Integer thumbnailId);
}
