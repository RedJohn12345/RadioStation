package korchagin.dao;

import korchagin.model.Album;
import korchagin.reflection.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class AlbumDaoCSV extends AbstractCSVFileDAO<Long, Album> {


    public AlbumDaoCSV() throws IOException {
        super("albumTable");
    }

    @Override
    public void put(Album object) {
        var key = object.getIdentity();
        var fields = new Object[] {
                object.getName(),
                object.getYear()
        };

        try {
            this.putInCSVFile(key, fields);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Album> get(Long key) {
        Optional<Object[]> fieldsInternal;
        try {
            fieldsInternal = this.getFromCSV(key);
        } catch (IOException e) {
            throw  new IllegalStateException(e);
        }

        if (fieldsInternal.isEmpty()) {
            return  Optional.empty();
        }

        var fields = fieldsInternal.get();

        var album = new Album((String) fields[1],  Integer.parseInt((String) fields[2]));
        album.setIdentity(key);
        return Optional.of(album);
    }
}
