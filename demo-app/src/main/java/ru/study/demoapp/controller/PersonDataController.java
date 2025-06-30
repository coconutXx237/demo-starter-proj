package ru.study.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.study.demoapp.model.Person;
import ru.study.demoapp.service.PersonServiceData;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class PersonDataController {

    private final PersonServiceData personService;

    @Autowired
    public PersonDataController(PersonServiceData personService) {
        this.personService = personService;
    }

    @GetMapping("/18")
    public List<Person> getAdultsOutsideMoscow() {
        return personService.findAdultsOutsideMoscow();
    }

    @PostMapping
    public void addUser(@RequestBody Person person) {
        personService.save(person);
    }

    @GetMapping("/count")
    public Map<String, Integer> countByCity() {
        return personService.countPersonsByCity();
    }

    @PostMapping("optimistic/{id}")
    public void updatePersonByIdOptimistic(@PathVariable Integer id, @RequestParam String name) {
       personService.updatePersonOptimistic(id, name);
    }

    @PostMapping("pessimistic/{id}")
    public void updatePersonByIdPessimistic(@PathVariable Integer id, @RequestParam String name) {
        personService.updatePersonPessimistic(id, name);
    }
}
