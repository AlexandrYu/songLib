package com.yurlov.songLib.data.services.impl;


import com.yurlov.songLib.data.model.Artist;
import com.yurlov.songLib.data.model.Song;
import com.yurlov.songLib.data.model.dto.SongDto;
import com.yurlov.songLib.data.repo.ArtistRepository;
import com.yurlov.songLib.data.repo.SongRepository;
import com.yurlov.songLib.data.services.ISongService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongService extends GenericService<Song> implements ISongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    protected JpaRepository<Song, Long> getJpaRepository() {
        return songRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Song update(Long id, SongDto songDto) {
        Song song = getById(id);
        song.apply(songDto);
        if (CollectionUtils.isNotEmpty(songDto.getArtistIds())) {
            List<Artist> artists = artistRepository.findByIdIn(songDto.getArtistIds());
            song.getArtists().clear();
            song.getArtists().addAll(artists);
        }
        return songRepository.save(song);
    }
}
