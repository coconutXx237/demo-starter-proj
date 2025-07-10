package ru.study.demoapp.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.study.demoapp.model.CityCount;
import ru.study.demoapp.model.Person;
import ru.study.demoapp.repository.PersonRepository;
import ru.study.demoapp.service.PersonService2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceDataImpl implements PersonService2 {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceDataImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public List<Person> findAll18PlusNotMoscow() {
        return personRepository.findByAgeAndCity();
    }

    @Override
    public Map<String, Integer> countPersonsByCity() {
        List<CityCount> rawResult = personRepository.countByCity();
        Map<String, Integer> result = new HashMap<>();
        for (CityCount el : rawResult) {
            String city = el.getCity();
            int count = (int) el.getCount();

            result.put(city, count);
        }
        return result;
    }

    @Transactional
    public void updatePersonOptimistic(int id, String newUsername) {
        Person person = personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        person.setName(newUsername);
        personRepository.save(person);
    }

    @Transactional
    public void updatePersonPessimistic(int id, String newUsername) {
        Person person = personRepository.findByIdForUpdate(id).orElseThrow(() -> new RuntimeException("Person not found"));
        person.setName(newUsername);
        personRepository.save(person);
    }
}
