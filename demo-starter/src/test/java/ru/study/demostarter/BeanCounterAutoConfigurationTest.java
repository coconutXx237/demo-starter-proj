package ru.study.demostarter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

public class BeanCounterAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withInitializer(new ConditionEvaluationReportLoggingListener())
            .withUserConfiguration(FirstBeanCounterAutoConfiguration.class, SecondBeanCounterAutoConfiguration.class);

    @Test
    void FirstConfigHasBean() {
        contextRunner
                .withPropertyValues("first-enabled=true")
                .run(context -> assertAll(
                        () -> assertThat(context).hasSingleBean(FirstBeanCounterAutoConfiguration.class)));
    }

    @Test
    void SecondConfigHasNoBean() {
        contextRunner
                .withPropertyValues("second-enabled=false")
                .run(context -> assertAll(
                                () -> assertThat(context).doesNotHaveBean(SecondBeanCounterAutoConfiguration.class)));
    }

    @AutoConfiguration
    @ConditionalOnProperty(name = "first-enabled", havingValue = "true")
    private static class FirstBeanCounterAutoConfiguration {

        @EventListener(ContextRefreshedEvent.class)
        public void onApplicationEvent(ContextRefreshedEvent event) {
            ApplicationContext context = event.getApplicationContext();
            int beanCount = context.getBeanDefinitionCount();
            System.out.println("First! Бинов в контексте: " + beanCount);
        }
    }

    @AutoConfiguration
    @ConditionalOnProperty(name = "second-enabled", havingValue = "true")
    private static class SecondBeanCounterAutoConfiguration {

        @EventListener(ContextRefreshedEvent.class)
        public void onApplicationEvent(ContextRefreshedEvent event) {
            ApplicationContext context = event.getApplicationContext();
            int beanCount = context.getBeanDefinitionCount();
            System.out.println("Second! Бинов в контексте: " + beanCount);
        }
    }
}