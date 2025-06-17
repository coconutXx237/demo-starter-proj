package ru.study.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.study.demoapp.metrics.CounterMetric;

import java.util.Locale;
import java.util.Map;

@RestController
public class MyController {

    private final CounterMetric counterMetric;
    private final MessageSource messageSource;

    @Autowired
    public MyController(CounterMetric counterMetric, MessageSource messageSource) {
        this.counterMetric = counterMetric;
        this.messageSource = messageSource;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<String> getStatus() {
        counterMetric.increment();
        return ResponseEntity.ok("Supposed to be incremented");
    }

    @GetMapping("/hello-lang")
    public String getGreeting(Locale locale) {
        return messageSource.getMessage("greeting", null, locale);
    }

    @GetMapping("/user/info")
    public String userInfo() {
        return "Hello, user!";
    }

    @GetMapping("/admin/info")
    public String adminInfo() {
        return "Hello, admin!";
    }

    @PostMapping("/add-user")
    public String addUser(@RequestBody Map<String, String> user) {
        return "Условно добавили юзера " + user.get("username") + " с паролем " + user.get("password");
    }
}
