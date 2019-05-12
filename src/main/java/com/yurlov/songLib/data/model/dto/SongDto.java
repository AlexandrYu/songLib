package com.yurlov.songLib.data.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yurlov.songLib.data.model.Song;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SongDto {

    private Long id;

    private String name;

    private Year yearIssue;

    private String writer;

    private String text;

    private List<Long> artistIds;

    private List<ArtistDto> artists;

    public SongDto() {
    }

    public SongDto(Song song) {
        this.id = song.getId();
        this.name = song.getName();
        this.yearIssue = song.getYearIssue();
        this.writer = song.getWriter();
        this.text = song.getText();
        this.artists = song.getArtists().stream().map(ArtistDto::new).collect(Collectors.toList());

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

    public Year getYearIssue() {
        return yearIssue;
    }

    public void setYearIssue(Year yearIssue) {
        this.yearIssue = yearIssue;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Long> getArtistIds() {
        return artistIds;
    }

    public void setArtistIds(List<Long> artistIds) {
        this.artistIds = artistIds;
    }

    public List<ArtistDto> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDto> artists) {
        this.artists = artists;
    }
}
