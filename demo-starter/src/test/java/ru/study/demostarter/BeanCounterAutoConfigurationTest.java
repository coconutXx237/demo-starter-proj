package ru.study.demostarter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import ru.study.demostarter.configuration.BeanCounterAutoConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class BeanCounterAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withInitializer(new ConditionEvaluationReportLoggingListener())
            .withUserConfiguration(BeanCounterAutoConfiguration.class);

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void checkPrintToConsole(){
        String res = outputStreamCaptor.toString().trim();
        System.setOut(standardOut);
        System.out.println("> > > BeanCounterAutoConfigurationTest : " + res);
    }

    @Test
    void printsToConsole() {
        contextRunner
                .withPropertyValues("starter-enabled=true")
                .run(context -> {
                    String actual = outputStreamCaptor.toString().trim();
                    assertTrue(actual.contains("Бинов в контексте"));
                });
    }

    @Test
    void doesNotPrintToConsole() {
        contextRunner
                .withPropertyValues("starter-enabled=false")
                .run(context -> {
                    String actual = outputStreamCaptor.toString();
                    assertEquals("", actual);
                });
    }
}
