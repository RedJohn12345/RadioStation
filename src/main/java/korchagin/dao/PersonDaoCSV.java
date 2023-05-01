package korchagin.dao;

import korchagin.model.musician.Person;
import korchagin.reflection.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class PersonDaoCSV extends AbstractCSVFileDAO<Long, Person> {


    public PersonDaoCSV() throws IOException {
        super("personTable");
    }

    @Override
    public void put(Person object) {
        var key = object.getIdentity();
        var fields = new Object[] {
                object.getName(),
                object.getSurname(),
                object.getNickname()
        };

        try {
            this.putInCSVFile(key, fields);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Person> get(Long key) {
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

        var person = new Person((String) fields[1], (String) fields[2], (String) fields[3]);
        person.setIdentity(key);
        return Optional.of(person);
    }
}
