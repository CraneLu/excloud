package com.lph.distributedlock.Component;

import com.lph.distributedlock.condition.RedisLockCondition;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.SetParams;

import java.util.*;

@Conditional(RedisLockCondition.class)
@Component
public class RedisMutex {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    private ThreadLocal<String> lockFlag = new ThreadLocal<String>();
    //定义释放锁的lua脚本
    private final static DefaultRedisScript<Long> UNLOCK_LUA_SCRIPT = new DefaultRedisScript<>(
            "if redis.call(\"get\",KEYS[1]) == KEYS[2] then return redis.call(\"del\",KEYS[1]) else return -1 end"
            , Long.class
    );

    public boolean acquire(String key, long expire, int retryTimes, long sleepMillis){
        boolean result = setRedis(key, expire);
        // 如果获取锁失败，按照传入的重试次数进行重试
        while(!result && retryTimes-- > 0){
            try {
                System.out.println("lock failed, retrying..." + retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result = setRedis(key, expire);
        }
        return result;
    }

    public void release(String key){
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            List<String> keys = new ArrayList<String>();
            keys.add(key);
            keys.add(lockFlag.get());
            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
            //todo:若是集群cluster方式待优化
            Long result = redisTemplate.execute(UNLOCK_LUA_SCRIPT, keys);

        } catch (Exception e) {
            System.out.println("release lock occured an exception:"+e.getMessage());
        }
    }

    private boolean setRedis(String key, long expire) {
        try {
            String result = redisTemplate.execute((new RedisCallback<String>() {
                @Nullable
                @Override
                public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    LettuceConnection commands = (LettuceConnection) redisConnection.getNativeConnection();
                    String uuid = UUID.randomUUID().toString();
                    lockFlag.set(uuid);
                    SetParams setParams = new SetParams();
                    setParams.nx().px(expire);
                    return commands..set(key, uuid, setParams);

                }
            }));
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            System.out.println("set redis occured an exception:"+e.getMessage());
        }
        return false;
    }

}
