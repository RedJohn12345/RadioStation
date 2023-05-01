package korchagin.controller;

import korchagin.dao.MusicRecordingDao;
import korchagin.dao.MusicRecordingDaoCSV;
import korchagin.dao.PersonDao;
import korchagin.model.MusicRecording;
import korchagin.reflection.Component;
import korchagin.reflection.DependencyInjection;

import java.io.IOException;
import java.util.Optional;

@Component
public class MusicRecordingController {

    @DependencyInjection
    private MusicRecordingDao dao;

    public MusicRecordingDao getDao() {
        return dao;
    }

    public Optional<MusicRecording> get(Long musicRecordingId) {
        return this.dao.get(musicRecordingId);
    }

    public void put(MusicRecording musicRecording) {
        this.dao.put(musicRecording);
    }
}
