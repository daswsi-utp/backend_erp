package com.microservice.logistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;  // Correct import

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceLogisticApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceLogisticApplication.class, args);
	}

}
