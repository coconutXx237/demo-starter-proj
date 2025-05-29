package ru.study.demostarter.configuration;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.time.LocalTime;

public class TimeCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return isCurrentMinuteEven();
    }

    private boolean isCurrentMinuteEven () {
        int minute = LocalTime.now().getMinute();
        return minute % 2 == 0;
    }
}
