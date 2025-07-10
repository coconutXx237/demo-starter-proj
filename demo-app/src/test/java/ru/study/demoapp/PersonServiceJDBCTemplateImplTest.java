package ru.study.demoapp;

import org.springframework.beans.factory.annotation.Autowired;
import ru.study.demoapp.service.PersonService2;
import ru.study.demoapp.service.impl.PersonServiceJDBCTemplateImpl;

public class PersonServiceJDBCTemplateImplTest extends AbstractPersonServiceTest {

    @Autowired
    private PersonServiceJDBCTemplateImpl personServiceTemplate;

    @Override
    protected PersonService2 getService() {
        return personServiceTemplate;
    }
}
