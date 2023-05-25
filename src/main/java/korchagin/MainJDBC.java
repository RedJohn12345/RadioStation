package korchagin;

import korchagin.controller.AlbumController;
import korchagin.controller.MusicRecordingController;
import korchagin.controller.PersonController;
import korchagin.model.Album;
import korchagin.model.MusicRecording;
import korchagin.model.enums.MusicGenre;
import korchagin.model.musician.Person;
import korchagin.reflection.ApplicationContext;
import korchagin.reflection.DependencyInjection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

public class MainJDBC {


    @DependencyInjection
    private static AlbumController albumController;

    @DependencyInjection
    private static MusicRecordingController musicRecordingController;

    @DependencyInjection
    private static PersonController personController;

    private static final String URL = "jdbc:postgresql://localhost:9100/radiostation";
    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "root";

    static {
        try {
            ApplicationContext.injectDependencies();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        personController.getDao().setConnection(connection);
        albumController.getDao().setConnection(connection);
        musicRecordingController.getDao().setConnection(connection);
    }

    public static void main(String[] args) {
        Album al = new Album("God, save Cute-rock", 2020);
        Person dora = new Person("Daria", "Shihanova", "Dora");
        Person maybebaby = new Person("Victoria", "Lysyuk", "MaybeBaby");

//        albumController.put(al);
//        personController.put(dora);
//        personController.put(maybebaby);
//        al = albumController.get(16L).get();
//        dora = personController.get(8L).get();
//        maybebaby = personController.get(9L).get();

//        MusicRecording barbi = new MusicRecording(MusicGenre.CUTE_ROCK, "BarbiSize", new HashSet<Person>(List.of(maybebaby)),
//                new HashSet<Person>(List.of(dora)), al, 3);
//
//        musicRecordingController.put(barbi);
//        System.out.println(musicRecordingController.get(10L));
    }
}
