package com.yurlov.songLib.data.services;

import com.yurlov.songLib.data.model.Song;
import com.yurlov.songLib.data.model.dto.SongDto;

public interface ISongService extends IGenericService<Song> {

    Song update(Long id, SongDto songDto);
}
