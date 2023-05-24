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

public class MainCSV {

    @DependencyInjection
    private static MusicRecordingDaoCSV mr;

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
        Album al = new Album("God, save Cute-rock", 2020);
        Person dora = new Person("Daria", "Shihanova", "Dora");
        Person maybebaby = new Person("Victoria", "Lysyuk", "MaybeBaby");
        MusicRecording barbi = new MusicRecording(MusicGenre.CUTE_ROCK, "BarbiSize", new HashSet<Person>(List.of(maybebaby)),
                new HashSet<Person>(List.of(dora)), al, 3);
        al.setIdentity(1L);
        ac.put(al);
        dora.setIdentity(1L);
        maybebaby.setIdentity(2L);
        pc.put(dora);
        pc.put(maybebaby);

        barbi.setIdentity(1L);
        mr.put(barbi);

        System.out.println(mr.get(1L));

    }
}
