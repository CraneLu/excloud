package com.lph.distributedlock.service.abstractImpl;

import com.lph.distributedlock.service.ILockService;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractLockService implements ILockService {

    @Override
    public boolean acquireLock(String path) {
        return false;
    }

    @Override
    public boolean acquireLock(String key, long expire, int retryTimes, long retrySleepMilliSeconds) {
        return false;
    }

    @Override
    public void releaseLock(String path) {

    }
}
