package korchagin.model;

import korchagin.dao.IdentityInterface;
import korchagin.model.enums.MusicGenre;
import korchagin.model.musician.Person;
import korchagin.model.user_request.UserRequestComponent;

import java.util.Set;

public class MusicRecording implements UserRequestComponent, IdentityInterface<Long> {
    private MusicGenre genre;
    private String name;
    private Set<Person> authors;
    private Set<Person> performers;
    private Album album;
    private Integer duration;
    private Integer rating;
    private Long identity;

    public MusicRecording(MusicGenre genre, String name, Set<Person> authors, Set<Person> performers, Album album, Integer duration) {
        this.genre = genre;
        this.name = name;
        this.authors = authors;
        this.performers = performers;
        this.album = album;
        rating = 0;
        setDuration(duration);
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        if (duration >= 0) {
            this.duration = duration;
        } else {
            throw new IllegalArgumentException("value out of range");
        }
    }

    public Integer getRating() {
        return rating;
    }

    public Set<Person> getAuthors() {
        return authors;
    }

    public Set<Person> getPerformers() {
        return performers;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public Long getIdentity() {
        return identity;
    }

    @Override
    public void setIdentity(Long identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "MusicRecording{" +
                "genre=" + genre +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", performers=" + performers +
                ", album=" + album +
                ", duration=" + duration +
                ", rating=" + rating +
                ", identity=" + identity +
                '}';
    }
}
