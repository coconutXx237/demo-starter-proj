package ru.study.demoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnotherService {

    private final SomeService someService;

    @Autowired
    public AnotherService(SomeService someService) {
        this.someService = someService;
    }
}
