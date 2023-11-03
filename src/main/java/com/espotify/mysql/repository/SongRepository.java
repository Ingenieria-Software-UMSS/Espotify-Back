package com.espotify.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.espotify.mysql.model.Song;

public interface SongRepository extends JpaRepository<Song, Integer> {

	// @Query("select song_id, song_title, song_album, song_duration, upload_date, song_url, artist_id, thumbnail_id from song where datediff(date(now()), date(song.upload_date)) <= 3")
	@Query("SELECT s FROM Song s WHERE datediff(date(now()), date(s.uploadDate)) <= 3")
	public List<Song> getAllNewSongs();
}
