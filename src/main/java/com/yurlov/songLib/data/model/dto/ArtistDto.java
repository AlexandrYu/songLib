package com.yurlov.songLib.data.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yurlov.songLib.data.model.Artist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArtistDto {

    private Long id;

    private String name;

    private String genre;

    private List<Long> songIds;

    private Map<Long, String> songs = new HashMap<>();

    public ArtistDto() {
    }

    public ArtistDto(Artist artist) {
        this.id = artist.getId();
        this.name = artist.getName();
        this.genre = artist.getGenre();
        artist.getSongs().forEach(s -> this.songs.put(s.getId(), s.getName()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    public List<Long> getSongIds() {
        return songIds;
    }

    public void setSongIds(List<Long> songIds) {
        this.songIds = songIds;
    }

    public Map<Long, String> getSongs() {
        return songs;
    }

    public void setSongs(Map<Long, String> songs) {
        this.songs = songs;
    }
}
