package com.espotify.mysql.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongDto {
	private Integer songId;
	private String songTitle;
	private String songAlbum;
	private String songDuration;
	private Integer numberOfLikes;
	private Integer numberOfDislikes;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadDate;
	private String songUrl;
	private String artistName;
	private String thumbnailUrl;
}
