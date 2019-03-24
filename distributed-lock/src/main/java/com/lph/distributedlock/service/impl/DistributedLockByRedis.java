package com.lph.distributedlock.service.impl;

import com.lph.distributedlock.Component.RedisMutex;
import com.lph.distributedlock.condition.RedisLockCondition;
import com.lph.distributedlock.service.abstractImpl.AbstractLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Conditional(RedisLockCondition.class)
public class DistributedLockByRedis extends AbstractLockService {

    @Autowired
    private RedisMutex redisMutex;

    @Override
    public boolean acquireLock(String key, long expire, int retryTimes, long retrySleepMilliSeconds) {
        return redisMutex.acquire(key, expire, retryTimes, retrySleepMilliSeconds);
    }

    @Override
    public void releaseLock(String key) {
        redisMutex.release(key);
    }
}
