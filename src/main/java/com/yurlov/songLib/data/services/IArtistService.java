package com.yurlov.songLib.data.services;

import com.yurlov.songLib.data.model.Artist;
import com.yurlov.songLib.data.model.dto.ArtistDto;

public interface IArtistService extends IGenericService<Artist> {

    Artist update(Long id, ArtistDto artistDto);

}
