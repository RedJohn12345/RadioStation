package korchagin;

import korchagin.dao.AlbumDaoCSV;
import korchagin.dao.MusicRecordingDaoCSV;
import korchagin.dao.PersonDaoCSV;
import korchagin.model.Album;
import korchagin.model.MusicRecording;
import korchagin.model.enums.MusicGenre;
import korchagin.model.musician.Person;
import korchagin.reflection.ApplicationContext;
import korchagin.reflection.DependencyInjection;

import java.util.HashSet;
import java.util.List;

public class Main {

/*    @Controller
    private static MusicRecordingController mrc;

    @Controller
    private static AlbumController ac;

    @Controller
    private static PersonController pc;*/

    @DependencyInjection
    private static MusicRecordingDaoCSV mrc;

    @DependencyInjection
    private static AlbumDaoCSV ac;

    @DependencyInjection
    private static PersonDaoCSV pc;

    static {
        try {
            ApplicationContext.injectDependencies();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Album al = new Album("God, save Cute rock", 2021);
        Person dora = new Person("Daria", "Shihanova", "Dora");
        Person maybebaby = new Person("Victoria", "Lysyuk", "MaybeBaby");
         MusicRecording barbi = new MusicRecording(MusicGenre.CUTE_ROCK, "BarbiSize", new HashSet<Person>(),
                new HashSet<Person>(List.of(dora, maybebaby)), al, 3);
        al.setIdentity(1L);
        dora.setIdentity(1L);
        maybebaby.setIdentity(2L);
        ac.put(al);
        pc.put(dora);
        pc.put(maybebaby);

        barbi.setIdentity(1L);
        mrc.put(barbi);

        System.out.println(mrc.get(1L).get());
    }
}
