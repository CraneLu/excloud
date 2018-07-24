package com.lph.excloudapiexchange;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ExcloudApiExchangeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcloudApiExchangeApplication.class, args);
	}
}
