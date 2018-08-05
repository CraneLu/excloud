package com.lph.excloudapiexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableEurekaClient
//@EnableCaching
public class ExcloudApiExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcloudApiExchangeApplication.class, args);
	}
}
