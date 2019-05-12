package com.yurlov.songLib.data.services.impl;

import com.yurlov.songLib.data.model.Artist;
import com.yurlov.songLib.data.model.Song;
import com.yurlov.songLib.data.model.dto.ArtistDto;
import com.yurlov.songLib.data.repo.ArtistRepository;
import com.yurlov.songLib.data.repo.SongRepository;
import com.yurlov.songLib.data.services.IArtistService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistService extends GenericService<Artist> implements IArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    @Override
    protected JpaRepository<Artist, Long> getJpaRepository() {
        return artistRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Artist update(Long id, ArtistDto artistDto) {
        Artist artist = getById(id);
        artist.apply(artistDto);
        if (CollectionUtils.isNotEmpty(artistDto.getSongIds())) {
            List<Song> songs = songRepository.findByIdIn(artistDto.getSongIds());
            artist.getSongs().clear();
            artist.getSongs().addAll(songs);
        }   return artistRepository.save(artist);
    }

}
