package korchagin.model.musician;

import korchagin.dao.IdentityInterface;
import korchagin.model.user_request.UserRequestComponent;

public class Person implements UserRequestComponent, IdentityInterface<Long> {
    private String name;
    private String surname;
    private String nickname;
    private Long identity;

    public Person(String name, String surname, String nickname) {
        this.name = name;
        this.surname = surname;
        this.nickname = nickname;
    }

    public Person() {}

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        nickname = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return nickname == null ? surname + " " + name : nickname;
    }


    @Override
    public Long getIdentity() {
        return identity;
    }

    @Override
    public void setIdentity(Long identity) {
        this.identity = identity;
    }
}
