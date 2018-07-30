package com.lph.excloudapiexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableEurekaClient
@EnableCaching
public class ExcloudApiExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcloudApiExchangeApplication.class, args);
	}
}
