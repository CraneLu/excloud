package com.lph.excloudeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ExcloudEurekaserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcloudEurekaserverApplication.class, args);
	}
}
