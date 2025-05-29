package com.microservice.planning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;  // Correct import

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicePlannigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePlannigApplication.class, args);
	}

}
