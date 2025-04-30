package com.microservice.manufacture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;  // Correct import

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceManufactureApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceManufactureApplication.class, args);
	}

}
