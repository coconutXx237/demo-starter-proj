package ru.study.demostarter.configuration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@AutoConfiguration
@ConditionalOnProperty(name = "starter-enabled", havingValue = "true")
public class BeanCounterAutoConfiguration {

    @EventListener(ContextRefreshedEvent.class)
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        int beanCount = context.getBeanDefinitionCount();
        System.out.println("Бинов в контексте: " + beanCount);
    }
}
