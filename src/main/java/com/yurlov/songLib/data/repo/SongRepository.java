package com.yurlov.songLib.data.repo;

import com.yurlov.songLib.data.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    List<Song> findByIdIn(List<Long> ids);
}
