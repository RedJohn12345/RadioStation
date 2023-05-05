package korchagin;

import korchagin.controller.AlbumController;
import korchagin.controller.MusicRecordingController;
import korchagin.controller.PersonController;
import korchagin.dao.AlbumDaoCSV;
import korchagin.dao.MusicRecordingDaoCSV;
import korchagin.dao.PersonDaoCSV;
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

public class Main {
    
    @DependencyInjection
    private static MusicRecordingDaoCSV mr;

    @DependencyInjection
    private static AlbumDaoCSV ac;

    @DependencyInjection
    private static PersonDaoCSV pc;

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

        albumController.getDao().setConnection(connection);
        personController.getDao().setConnection(connection);
        musicRecordingController.getDao().setConnection(connection);
    }

    public static void main(String[] args) {
//        Album al = new Album("God, save Cute-rock", 2020);
//        Person dora = new Person("Daria", "Shihanova", "Dora");
//        Person maybebaby = new Person("Victoria", "Lysyuk", "MaybeBaby");
//        MusicRecording barbi = new MusicRecording(MusicGenre.CUTE_ROCK, "BarbiSize", new HashSet<Person>(List.of(maybebaby)),
//                new HashSet<Person>(List.of(dora)), al, 3);
//        ac.put(al);
//        pc.put(dora);
//        pc.put(maybebaby);
//
//        barbi.setIdentity(1L);
//        mrc.put(barbi);

//        System.out.println(mrc.get(1L).get());

        //albumController.put(al);
        //personController.put(dora);
        //personController.put(maybebaby);

        //musicRecordingController.put(barbi);
    }
}
