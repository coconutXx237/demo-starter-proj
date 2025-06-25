package ru.study.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.study.demoapp.model.Person;
import ru.study.demoapp.service.PersonService;

import java.util.List;

@RequestMapping("/api/persons")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> add(@RequestBody Person person) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.create(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getById(@PathVariable Integer id) {
        Person person = personService.getById(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> update(@PathVariable Integer id, @RequestBody Person person) {
        Person updPerson = personService.updateById(id, person);
        return updPerson != null ? ResponseEntity.ok(updPerson) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        personService.deleteById(id);
    }
}
