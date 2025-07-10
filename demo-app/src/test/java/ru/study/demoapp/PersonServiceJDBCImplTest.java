package ru.study.demoapp;

import org.springframework.beans.factory.annotation.Autowired;
import ru.study.demoapp.service.PersonService2;
import ru.study.demoapp.service.impl.PersonServiceJDBCImpl;

public class PersonServiceJDBCImplTest extends AbstractPersonServiceTest {

    @Autowired
    private PersonServiceJDBCImpl personServiceJdbc;

    @Override
    protected PersonService2 getService() {
        return personServiceJdbc;
    }
}
