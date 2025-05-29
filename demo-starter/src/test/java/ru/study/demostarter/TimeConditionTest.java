package ru.study.demostarter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import ru.study.demostarter.configuration.TimeDependingAutoConfiguration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeConditionTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withInitializer(new ConditionEvaluationReportLoggingListener())
            .withUserConfiguration(TimeDependingAutoConfiguration.class);

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void worksAtEvenMinutes() {
        contextRunner
                .run(context -> {
                    int minute = LocalTime.now().getMinute();
                    if (minute % 2 == 0) {
                        String actual = outputStreamCaptor.toString().trim();
                        assertTrue(actual.contains("Работаю по чётным минутам"));
                    }
                });
    }

    @Test
    void notWorksAtOddMinutes() {
        contextRunner
                .run(context -> {
                    int minute = LocalTime.now().getMinute();
                    if (minute % 2 != 0) {
                        String actual = outputStreamCaptor.toString().trim();
                        assertEquals("", actual);
                    }
                });
    }
}
