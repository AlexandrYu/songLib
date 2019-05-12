package com.yurlov.songLib.web.controllers;

import com.yurlov.songLib.data.exception.ApiValidationException;
import com.yurlov.songLib.data.model.Song;
import com.yurlov.songLib.data.model.dto.SongDto;
import com.yurlov.songLib.data.services.ISongService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/song", produces = MediaType.APPLICATION_JSON_VALUE)
public class SongController {

    @Autowired
    private ISongService songService;

    @GetMapping
    public ResponseEntity<List<SongDto>> getAllSong() {
        List<Song> songs = songService.findAll();
        return new ResponseEntity<>(songs.stream().map(SongDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SongDto> getSong(@PathVariable Long id) {
        Song song = songService.getById(id);
        return new ResponseEntity<>(new SongDto(song), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SongDto> insertSong(@RequestBody SongDto songDto) {
        validate(songDto);
        Song song = new Song(songDto);
        Song insertedSong = songService.create(song);
        return new ResponseEntity<>(new SongDto(insertedSong), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<SongDto> updateSong(@PathVariable Long id, @RequestBody SongDto song) {
        Song updatedSong = songService.update(id, song);
        return new ResponseEntity<>(new SongDto(updatedSong), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSong (@PathVariable Long id) {
        songService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validate(SongDto songDto) {
        if (StringUtils.isBlank(songDto.getName())) {
            throw new ApiValidationException("Song name couldn't be empty");
        }
    }
}
