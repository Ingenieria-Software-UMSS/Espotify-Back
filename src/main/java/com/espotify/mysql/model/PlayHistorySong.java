package com.espotify.mysql.model;

import java.util.Date;

import com.espotify.mysql.dto.PlayHistorySongDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayHistorySong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playHistorySongId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date playDate;
	@ManyToOne
	@JoinColumn(name = "play_history_id")
	@JsonIgnoreProperties("playHistorySongList")
	private PlayHistory playHistory;
	@ManyToOne
	@JoinColumn(name = "song_id")
	@JsonIgnoreProperties("playHistorySongList")
	private Song song;

	public PlayHistorySongDto toDto() {
		return new PlayHistorySongDto(
			playHistorySongId, 
			playDate, 
			playHistory, 
			song.toDto()
		);
	}
}
