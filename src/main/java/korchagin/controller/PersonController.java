package korchagin.controller;

import korchagin.dao.PersonDaoCSV;
import korchagin.model.musician.Person;
import korchagin.reflection.Component;
import korchagin.reflection.DependencyInjection;

import java.io.IOException;
import java.util.Optional;

@Component
public class PersonController {
    @DependencyInjection
    private PersonDaoCSV dao;

    public PersonController() throws IOException {
    }

    public Optional<Person> get(Long personId) {
        return this.dao.get(personId);
    }

    public void put(Person person) {
        this.dao.put(person);
    }
}
