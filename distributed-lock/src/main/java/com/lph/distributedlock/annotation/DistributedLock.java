package com.lph.distributedlock.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DistributedLock {

    /** redis的key 或 zookeeper的path */
    String key() default "";

    /** **********************redis******************************* */
    /** 持有redis锁的超时时间,单位毫秒 */
    long keepMills() default 30000;

    /** 当获取失败时候动作 */
    LockFailAction action() default LockFailAction.CONTINUE;

    /** 重试的间隔时间,设置GIVEUP忽略此项*/
    long retrysleepMills() default 200;

    /** 重试次数*/
    int retryTimes() default 5;

    enum LockFailAction{
        /** 放弃 */
        GIVEUP,
        /** 继续 */
        CONTINUE;
    }
    /** **********************redis******************************* */


}
