package ru.study.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.study.demoapp.model.Person;
import ru.study.demoapp.service.PersonServiceJDBCTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class PersonJDBCTemplateController {

    private final PersonServiceJDBCTemplate personService;

    @Autowired
    public PersonJDBCTemplateController(PersonServiceJDBCTemplate personService) {
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
