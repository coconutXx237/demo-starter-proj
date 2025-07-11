package ru.study.demoapp;

import org.springframework.beans.factory.annotation.Autowired;
import ru.study.demoapp.service.PersonService2;
import ru.study.demoapp.service.impl.PersonServiceDataImpl;

public class PersonServiceDataImplTest extends AbstractPersonServiceTest {

    @Autowired
    private PersonServiceDataImpl personServiceData;

    @Override
    protected PersonService2 getService() {
        return personServiceData;
    }
}
