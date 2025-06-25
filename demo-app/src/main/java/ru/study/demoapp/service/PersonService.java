package ru.study.demoapp.service;

import org.springframework.stereotype.Service;
import ru.study.demoapp.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PersonService {

    private final Map<Integer, Person> persons = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<Person> getAll() {
        return new ArrayList<>(persons.values());
    }

    public Person getById(Integer id) {
        return persons.get(id);
    }

    public Person create(Person person) {
        int id = idCounter.getAndIncrement();
        person.setId(id);
        persons.put(id, person);
        return person;
    }

    public Person updateById(Integer id, Person updatedPerson) {
        Person movie = persons.get(id);
        if (movie != null) {
            updatedPerson.setId(id);
            persons.put(id, updatedPerson);
            return updatedPerson;
        }
        return null;
    }

    public void deleteById(Integer id) {
        persons.remove(id);
    }

}
