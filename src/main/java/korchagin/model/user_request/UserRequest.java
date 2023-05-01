package korchagin.model.user_request;

import korchagin.model.Album;
import korchagin.model.MusicRecording;
import korchagin.model.enums.UserRequestType;
import korchagin.model.musician.Person;

public class UserRequest {
    private MusicRecording musicRecording;
    private UserRequestType type;
    private UserRequestComponent component;


    public UserRequest(MusicRecording musicRecording) {
        this.musicRecording = musicRecording;
        this.component = musicRecording;
        this.type = UserRequestType.CERTAIN_MUSIC;
    }

    public UserRequest(Album album) {
        this.component = album;
        type = UserRequestType.BY_ALBUM;
    }

    public UserRequest(Person person, UserRequestType type) {
        this.component = person;
        this.type = type;
    }

    public MusicRecording getMusicRecording() {
        return musicRecording;
    }

    public void setMusicRecording(MusicRecording musicRecording) {
        this.musicRecording = musicRecording;
    }

    public UserRequestType getType() {
        return type;
    }

    public UserRequestComponent getComponent() {
        return component;
    }
}
