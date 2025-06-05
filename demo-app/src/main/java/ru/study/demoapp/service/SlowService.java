package ru.study.demoapp.service;

import org.springframework.stereotype.Component;

@Component
public class SlowService {
    public void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
