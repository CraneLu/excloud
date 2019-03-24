package com.lph.distributedlock.service;

public interface ILockService {

    boolean acquireLock(String path);

    void releaseLock(String path);

}
