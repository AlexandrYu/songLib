package com.yurlov.songLib.data.repo;

import com.yurlov.songLib.data.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findByIdIn(List<Long> ids);
}
