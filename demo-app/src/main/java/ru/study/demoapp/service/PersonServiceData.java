package ru.study.demoapp.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.study.demoapp.model.Person;
import ru.study.demoapp.repository.PersonRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonServiceData {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceData(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public List<Person> findAdultsOutsideMoscow() {
        return personRepository.findByAgeAndCity();
    }

    public Map<String, Integer> countPersonsByCity() {
        List<Object[]> rawResult = personRepository.countByCity();
        Map<String, Integer> result = new HashMap<>();
        for (Object[] row : rawResult) {
            String city = (String) row[0];
            int count = ((Number) row[1]).intValue();
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
