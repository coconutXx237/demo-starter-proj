package ru.study.demoapp.service;

import ru.study.demoapp.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonService2 {
    void save(Person person);
    List<Person> findAll18PlusNotMoscow();
    Map<String, Integer> countPersonsByCity();
}
