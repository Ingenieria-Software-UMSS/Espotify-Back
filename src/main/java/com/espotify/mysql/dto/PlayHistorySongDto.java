package com.espotify.mysql.dto;

import java.util.Date;

import com.espotify.mysql.model.PlayHistory;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayHistorySongDto {
	private Long playHistorySongId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date playDate;
	private PlayHistory playHistory;
	private SongDto song;
}
