package ru.study.demoapp.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CounterMetric {

    private final Counter myCounter;

    public CounterMetric(MeterRegistry meterRegistry) {
        this.myCounter = meterRegistry.counter("custom.my_counter");
    }

    public void increment() {
        myCounter.increment();
    }
}
