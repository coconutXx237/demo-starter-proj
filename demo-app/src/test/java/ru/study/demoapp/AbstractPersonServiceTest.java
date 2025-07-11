package ru.study.demoapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.study.demoapp.model.Person;
import ru.study.demoapp.repository.PersonRepository;
import ru.study.demoapp.service.PersonService2;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public abstract class AbstractPersonServiceTest {

    @Autowired
    protected PersonRepository personRepository;

    protected abstract PersonService2 getService();

    @BeforeEach
    public void setup() {
        personRepository.deleteAll();

        getService().save(new Person("Ivan", 20, "Москва"));
        getService().save(new Person("Olga", 25, "Питер"));
        getService().save(new Person("Masha", 30, "Новосибирск"));
        getService().save(new Person("Egor", 15, "Питер"));
    }

    @Test
    public void testFindAll18PlusNotMoscow() {
        List<String> actualRes = getService().findAll18PlusNotMoscow().stream()
                .map(Person::getName)
                .toList();
        List<String> expectedRes = List.of("Olga", "Masha");
        assertEquals(expectedRes, actualRes);
    }

    @Test
    public void testCountByCity() {
        Map<String, Integer> result = getService().countPersonsByCity();
        assertThat(result.get("Питер")).isEqualTo(2);
        assertThat(result.get("Москва")).isEqualTo(1);
        assertThat(result.get("Новосибирск")).isEqualTo(1);
    }
}
