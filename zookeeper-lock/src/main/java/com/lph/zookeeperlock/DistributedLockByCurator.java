package com.lph.zookeeperlock;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;

@Component
public class DistributedLockByCurator {

//    private final Logger logger = Logger.getLogger(DistributedLockByCurator.class);
    private static final String LOCK_ROOT_PATH = "/locks";
    private static final Long MUTEX_ACQUIRE_TIMEOUT = 2000L;
    private static final TimeUnit MUTEX_TIMEUNIT = TimeUnit.MILLISECONDS;
//    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired private InterProcessMutex mutex;

    public boolean acquireLock(String path){
        try {
            return mutex.acquire(this.MUTEX_ACQUIRE_TIMEOUT, this.MUTEX_TIMEUNIT);
        } catch (Exception e) {
            System.out.println("...acquire mutex exception... " + e.getMessage());
            return false;
        }

    }

    public void releaseLock(String path) {
        try {
            mutex.release();
        } catch (Exception e) {
            System.out.println("...release mutex exctption..."+e.getMessage());
        }
    }

    public void addWatcher(){

    }


}
