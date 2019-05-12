package com.yurlov.songLib.web.controllers;

import com.yurlov.songLib.data.exception.ApiValidationException;
import com.yurlov.songLib.data.model.Artist;
import com.yurlov.songLib.data.model.dto.ArtistDto;
import com.yurlov.songLib.data.services.IArtistService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/artist", produces = MediaType.APPLICATION_JSON_VALUE)
public class ArtistController {

    @Autowired
    private IArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllSong() {
        List<Artist> artists = artistService.findAll();
        return new ResponseEntity<>(artists.stream().map(ArtistDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ArtistDto> getArtist(@PathVariable Long id) {
        Artist artist = artistService.getById(id);
        return new ResponseEntity<>(new ArtistDto(artist), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ArtistDto> insertArtist(@RequestBody ArtistDto artistDto) {
        validate(artistDto);
        Artist artist = new Artist(artistDto);
        Artist insertedArtist = artistService.create(artist);
        return new ResponseEntity<>(new ArtistDto(insertedArtist), HttpStatus.OK);
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<ArtistDto> updateArtist(@PathVariable Long id, @RequestBody ArtistDto artistDto) {
        Artist updatedArtist = artistService.update(id, artistDto);
        return new ResponseEntity<>(new ArtistDto(updatedArtist), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validate(ArtistDto artistDto) {
        if (StringUtils.isBlank(artistDto.getName())) {
            throw new ApiValidationException("Artist name couldn't be empty");
        }
    }
}
