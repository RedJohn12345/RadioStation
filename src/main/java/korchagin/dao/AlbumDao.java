package korchagin.dao;

import korchagin.model.Album;
import korchagin.reflection.Component;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AlbumDao implements DAO<Long, Album> {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void put(Album object) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO \"album\" (name, year) VALUES (?, ?)");

            statement.setString(1, object.getName());
            statement.setInt(2, object.getYear());


            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Album> get(Long key) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"album\" WHERE id = ?");
            statement.setInt(1, key.intValue());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Integer year = resultSet.getInt("year");
            Album album = new Album(name, year);
            album.setIdentity(id);

            return Optional.of(album);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<List<Album>> getAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"album\"");
            List<Album> albums = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Integer year = resultSet.getInt("year");
                Album album = new Album(name, year);
                album.setIdentity(id);
                albums.add(album);
            }

            return Optional.of(albums);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
