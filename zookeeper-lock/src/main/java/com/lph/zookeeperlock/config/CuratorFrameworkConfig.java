package com.lph.zookeeperlock.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorFrameworkConfig {

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework(){
        return CuratorFrameworkFactory.builder()
                .connectString("192.168.183.10:2181,192.168.183.11:2181,192.168.183.12:2181")
                .connectionTimeoutMs(5000) // 连接超时时间
                .sessionTimeoutMs(50000)    // 会话超时时间
                // 刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
    }

    @Bean
    public InterProcessMutex interProcessMutex(){
        return new InterProcessMutex(curatorFramework(), "/locks");
    }

}
