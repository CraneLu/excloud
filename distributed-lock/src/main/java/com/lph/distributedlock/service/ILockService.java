package com.lph.distributedlock.service;

public interface ILockService {

    /**
     * 适用于redis
     * @param path
     * @return
     */
    boolean acquireLock(String path);

    /**
     * 适用于zookeeper
     * @param key
     * @param expire
     * @param retryTimes
     * @param retrySleepMilliSeconds
     * @return
     */
    boolean acquireLock(String key, long expire, int retryTimes, long retrySleepMilliSeconds);

    /**
     * 适用于redis和zookeeper
     * @param path
     */
    void releaseLock(String path);

}
