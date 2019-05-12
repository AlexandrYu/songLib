package com.yurlov.songLib.data.model;

import com.yurlov.songLib.data.model.dto.SongDto;

import javax.persistence.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "year_issue")
    private Year yearIssue;

    @Column(name = "writer")
    private String writer;

    @Column(name = "text", length = 48000)
    private String text;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "artists_songs",
            joinColumns = { @JoinColumn(name = "songs_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "artist_id", nullable = false) })
    private List<Artist> artists = new ArrayList<>();

    public Song() {
    }

    public Song(SongDto songDto) {
        this.name = songDto.getName();
        this.yearIssue = songDto.getYearIssue();
        this.writer = songDto.getWriter();
        this.text = songDto.getText();
    }

    public void apply(SongDto songDto) {
        if (null != songDto.getName()) {
            this.name = songDto.getName();
        }
        if (null != songDto.getText()) {
            this.text = songDto.getText();
        }
        if (null != songDto.getWriter()) {
            this.writer = songDto.getWriter();
        }
        if (null != songDto.getYearIssue()) {
            this.yearIssue = songDto.getYearIssue();
        }
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

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id.equals(song.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
