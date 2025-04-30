package com.microservice.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;  // Correct import

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCrmApplication.class, args);
	}

}
