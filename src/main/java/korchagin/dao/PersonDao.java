package korchagin.dao;

import korchagin.model.Album;
import korchagin.model.musician.Person;
import korchagin.reflection.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class PersonDao implements DAO<Long, Person> {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void put(Person object) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO \"person\" (name, surname, nickname) VALUES (?, ?, ?)");

            statement.setString(1, object.getName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getNickname());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Person> get(Long key) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM \"person\" WHERE id = ?");
            statement.setInt(1, key.intValue());
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String nickname = resultSet.getString("nickname");
            Person person = new Person(name, surname, nickname);
            person.setIdentity(key);
            return Optional.of(person);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
