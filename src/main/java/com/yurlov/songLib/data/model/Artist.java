package com.yurlov.songLib.data.model;

import com.yurlov.songLib.data.model.dto.ArtistDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "genre", nullable = false)
    private String genre;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(name = "artists_songs",
            joinColumns = { @JoinColumn(name = "artist_id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "songs_id", nullable = false) })
    private List<Song> songs = new ArrayList<>();

    public Artist() {
    }

    public Artist(ArtistDto artistDto) {
        this.name = artistDto.getName();
        this.genre = artistDto.getGenre();
    }

    public void apply(ArtistDto artistDto) {
        if (null != artistDto.getName()) {
            this.name = artistDto.getName();
        }
        if (null != artistDto.getGenre()) {
            this.genre = artistDto.getGenre();
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return id.equals(artist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
