package korchagin.dao;

import korchagin.model.Album;
import korchagin.model.MusicRecording;
import korchagin.model.enums.MusicGenre;
import korchagin.model.musician.Person;
import korchagin.reflection.DependencyInjection;
import org.jetbrains.annotations.NotNull;
import korchagin.reflection.Component;
import korchagin.utils.Utils;

import java.io.IOException;
import java.util.*;


@Component
public class MusicRecordingDaoCSV extends AbstractCSVFileDAO<Long, MusicRecording> {

    @DependencyInjection
    private AlbumDaoCSV ac;

    @DependencyInjection
    private PersonDaoCSV pc;
    public MusicRecordingDaoCSV() throws IOException {
        super("musicRecordingTable");
/*        ac = new AlbumDaoCSV();
        pc = new PersonDaoCSV();*/
    }

    @Override
    public void put(@NotNull MusicRecording object) {
        var key = object.getIdentity();
        var fields = new Object[] {
                object.getName(),
                object.getGenre().ordinal(),
                (object.getAlbum() == null ? "" : object.getAlbum().getIdentity()),
                object.getDuration(),
                Utils.objectsToSet(object.getAuthors()),
                Utils.objectsToSet(object.getPerformers()),
                object.getRating()
        };

        try {
            this.putInCSVFile(key, fields);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<MusicRecording> get(Long key) {
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

        Album al = (fields[3].equals("") ? null : ac.get(Long.parseLong((String) fields[3])).get());
        List<Long> authors = Utils.strToList((String)fields[5]);
        List<Long> performers = Utils.strToList((String)fields[6]);

        var musicRecording = new MusicRecording(MusicGenre.values()[Integer.parseInt((String) fields[2])], (String)fields[1],
                getPersons(authors), getPersons(performers), al,
                Integer.parseInt((String) fields[4]));

        musicRecording.setRating(Integer.parseInt((String) fields[7]));

        musicRecording.setIdentity(key);
        return Optional.of(musicRecording);
    }

    private Set<Person> getPersons(List<Long> id) {
        Set<Person> personSet = new HashSet<>();
        for (Long i : id) {
            personSet.add(pc.get(i).get());
        }

        return personSet;
    }
}
