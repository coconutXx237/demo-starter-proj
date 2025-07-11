package ru.study.demoapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import ru.study.demoapp.model.Person;
import ru.study.demoapp.repository.PersonRepository;
import ru.study.demoapp.service.impl.PersonServiceDataImpl;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LockTest {

    @Autowired
    private PersonServiceDataImpl personService;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll();

        Person person = new Person("Privet", 30, "Москва");
        personRepository.save(person);
    }

    @Test
    public void testOptimisticLock_mustBeConflict() {
        int id = personRepository.findAll().get(0).getId();
        Person person1 = personRepository.findById(id).get();
        Person person2 = personRepository.findById(id).get();

        person1.setName("Test111");
        personRepository.save(person1);

        assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
            person2.setName("22222");
            personRepository.save(person2);
        });
    }

    @Test
    public void testPessimisticLock_mustBeConflict() throws InterruptedException {
        Person person = new Person("Pessimistic", 333, "Питер");
        personRepository.save(person);

        CountDownLatch latch = new CountDownLatch(2);

        Runnable tx1 = () -> {
            personService.updatePersonPessimistic(person.getId(), "Tx1");
            latch.countDown();
        };

        Runnable tx2 = () -> {
            personService.updatePersonPessimistic(person.getId(), "Tx2");
            latch.countDown();
        };

        Thread t1 = new Thread(tx1);
        Thread t2 = new Thread(tx2);

        t1.start();
        Thread.sleep(100);
        t2.start();

        latch.await();

        Person updated = personRepository.findById(person.getId()).get();
        assertTrue(updated.getName().equals("Tx1") || updated.getName().equals("Tx2"));
    }
}
