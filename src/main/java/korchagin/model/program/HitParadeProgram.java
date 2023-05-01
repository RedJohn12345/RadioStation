package korchagin.model.program;

import korchagin.model.enums.MusicGenre;

import java.sql.Date;

public class HitParadeProgram extends Program {
    private MusicGenre genre;

    public HitParadeProgram(Date date, MusicGenre genre) {
        super(date);
        this.genre = genre;
    }


    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }
}
