package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Thumbnail;

public interface ThumbnailService {
	public Thumbnail getThumbnailById(Integer thumbnailId);

	public List<Thumbnail> getThumbnailList();

	public Thumbnail addThumbnail(Thumbnail thumbnail);
}
