package com.lph.distributedlock.service.impl;

import com.lph.distributedlock.condition.ZookeeperLockCondition;
import com.lph.distributedlock.service.abstractImpl.AbstractLockService;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;

@Component
@Conditional(ZookeeperLockCondition.class)
public class DistributedLockByZookeeper extends AbstractLockService {

//    private final Logger logger = Logger.getLogger(DistributedLockByCurator.class);
    private static final Long MUTEX_ACQUIRE_TIMEOUT = 2000L;
    private static final TimeUnit MUTEX_TIMEUNIT = TimeUnit.MILLISECONDS;
//    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired private InterProcessMutex mutex;

    @Override
    public boolean acquireLock(String path){
        try {
            return mutex.acquire(this.MUTEX_ACQUIRE_TIMEOUT, this.MUTEX_TIMEUNIT);
        } catch (Exception e) {
            System.out.println("...acquire mutex exception... " + e.getMessage());
            return false;
        }

    }

    @Override
    public void releaseLock(String path) {
        try {
            mutex.release();
        } catch (Exception e) {
            System.out.println("...release mutex exctption..."+e.getMessage());
        }
    }

}
