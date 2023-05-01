package korchagin.controller;

import korchagin.dao.AlbumDaoCSV;
import korchagin.model.Album;
import korchagin.reflection.Component;
import korchagin.reflection.DependencyInjection;

import java.io.IOException;
import java.util.Optional;

@Component
public class AlbumController {

    @DependencyInjection
    private AlbumDaoCSV dao;

    public AlbumController() throws IOException {
    }

    public Optional<Album> get(Long albumId) {
        return this.dao.get(albumId);
    }

    public void put(Album album) {
        this.dao.put(album);
    }
}
