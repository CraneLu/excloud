package com.lph.zookeeperlock.aspect;

import com.lph.zookeeperlock.DistributedLockByCurator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ZookeeperDistributedLockAspect {

    @Autowired private DistributedLockByCurator lockByCurator;

    @Pointcut("@annotation(com.lph.zookeeperlock.annotation.Lock)")
    public void zookeeperDistributedLock(){}

    @Around(value = "zookeeperDistributedLock()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        boolean isLocked = lockByCurator.acquireLock("");
        if(isLocked){
            Object object = pjp.proceed();
            lockByCurator.releaseLock("");
            return object;
        }
        return "无法获取Lock";
    }




}
