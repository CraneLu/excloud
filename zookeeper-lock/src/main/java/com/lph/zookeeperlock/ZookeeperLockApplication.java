package com.lph.zookeeperlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ZookeeperLockApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperLockApplication.class, args);
	}

}
