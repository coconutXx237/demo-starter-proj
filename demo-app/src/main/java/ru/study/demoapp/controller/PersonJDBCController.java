package ru.study.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.study.demoapp.model.Person;
import ru.study.demoapp.service.impl.PersonServiceJDBCImpl;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jdbc")
public class PersonJDBCController {


    private final PersonServiceJDBCImpl personService;

    @Autowired
    public PersonJDBCController(PersonServiceJDBCImpl personService) {
        this.personService = personService;
    }

    @GetMapping("/18")
    public List<Person> getAdultsOutsideMoscow() {
        return personService.findAll18PlusNotMoscow();
    }

    @PostMapping
    public void addUser(@RequestBody Person person) {
        personService.save(person);
    }

    @GetMapping("/count")
    public Map<String, Integer> countPersonsByCity() {
        return personService.countPersonsByCity();
    }
}
