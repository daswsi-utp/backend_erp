package com.microservice.rrhh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;  // Correct import
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceRrhhApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceRrhhApplication.class, args);
	}

}
