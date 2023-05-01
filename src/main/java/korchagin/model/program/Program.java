package korchagin.model.program;

import korchagin.model.MusicRecording;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class Program {

    protected List<MusicRecording> musicRecordings;
    protected Date date;

    public Program(Date date) {
        musicRecordings = new LinkedList<>();
        this.date = date;
    }

    public List<MusicRecording> getMusicRecordings() {
        return musicRecordings;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
