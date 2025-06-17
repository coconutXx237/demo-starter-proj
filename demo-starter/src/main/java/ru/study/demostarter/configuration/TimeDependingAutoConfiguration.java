package ru.study.demostarter.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@AutoConfiguration
@Conditional(TimeCondition.class)
public class TimeDependingAutoConfiguration {

    @EventListener(ContextRefreshedEvent.class)
    public void printMsg() {
        System.out.println("Работаю по чётным минутам");
    }
}
