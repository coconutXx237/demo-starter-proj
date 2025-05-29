package ru.study.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.study.demoapp.metrics.CounterMetric;

@RestController
public class MyController {

    private final CounterMetric counterMetric;

    @Autowired
    public MyController(CounterMetric counterMetric) {
        this.counterMetric = counterMetric;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<String> getStatus() {
        counterMetric.increment();
        return ResponseEntity.ok("Supposed to be incremented");
    }
}
