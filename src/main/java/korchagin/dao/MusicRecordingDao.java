package korchagin.dao;

import korchagin.model.Album;
import korchagin.model.MusicRecording;
import korchagin.model.enums.MusicGenre;
import korchagin.model.musician.Person;
import korchagin.reflection.Component;
import korchagin.reflection.DependencyInjection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class MusicRecordingDao implements DAO<Long, MusicRecording> {

    private Connection connection;

    @DependencyInjection
    private AlbumDao albumDao;

    @DependencyInjection
    private PersonDao personDao;

    public void setConnection(Connection connection) {
        this.connection = connection;
        albumDao.setConnection(connection);
        personDao.setConnection(connection);
    }

    @Override
    public void put(MusicRecording object) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO \"MusicRecording\" (name, genre, album_id, duration, rating) VALUES (?, ?, ?, ?, ?) RETURNING id");

            statement.setString(1, object.getName());
            statement.setInt(2, object.getGenre().ordinal());
            statement.setInt(3, object.getAlbum().getIdentity().intValue());
            statement.setInt(4, object.getDuration());
            statement.setInt(5, object.getRating());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            Long id = resultSet.getLong("id");

            statement = connection.prepareStatement(
                    "INSERT INTO \"MusicRecordingAuthor\" (music_recording_id, author_id) VALUES (?, ?)");

            for (Person author : object.getAuthors()) {
                statement.setInt(1, id.intValue());
                statement.setInt(2, author.getIdentity().intValue());
                statement.executeUpdate();
            }

            statement = connection.prepareStatement(
                    "INSERT INTO \"MusicRecordingPerformer\" (music_recording_id, performer_id) VALUES (?, ?)");

            for (Person performer : object.getPerformers()) {
                statement.setInt(1, id.intValue());
                statement.setInt(2, performer.getIdentity().intValue());
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<MusicRecording> get(Long key) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"MusicRecording\" WHERE id = ?");
            statement.setInt(1, key.intValue());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            MusicGenre genre = MusicGenre.values()[resultSet.getInt("genre")];
            Album album = albumDao.get(resultSet.getLong("album_id")).get();
            String name = resultSet.getString("name");
            Integer duration = resultSet.getInt("duration");
            Integer rating = resultSet.getInt("rating");
            Set<Person> authors = new HashSet<>();
            Set<Person> performers = new HashSet<>();

            statement =  connection.prepareStatement(
                    "SELECT author_id FROM \"MusicRecordingAuthor\" WHERE music_recording_id = ?");
            statement.setInt(1, key.intValue());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                authors.add(personDao.get(resultSet.getLong("author_id")).get());
            }

            statement =  connection.prepareStatement(
                    "SELECT performer_id FROM \"MusicRecordingPerformer\" WHERE music_recording_id = ?");
            statement.setInt(1, key.intValue());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                performers.add(personDao.get(resultSet.getLong("performer_id")).get());
            }


            MusicRecording musicRecording = new MusicRecording(genre, name, authors, performers, album, duration);
            musicRecording.setRating(rating);
            musicRecording.setIdentity(key);
            return Optional.of(musicRecording);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
