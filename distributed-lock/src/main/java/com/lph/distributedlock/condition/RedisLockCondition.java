package com.lph.distributedlock.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class RedisLockCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment environment = conditionContext.getEnvironment();
        String lockType = environment.getProperty("distributed-lock.type");
        if("redis".equalsIgnoreCase(lockType)){
            return true;
        }
        return false;
    }
}
