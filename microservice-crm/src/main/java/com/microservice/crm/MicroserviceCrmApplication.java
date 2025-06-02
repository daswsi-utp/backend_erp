package com.microservice.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;  // Correct import
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.microservice.crm.feign")
public class MicroserviceCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCrmApplication.class, args);
	}

}
