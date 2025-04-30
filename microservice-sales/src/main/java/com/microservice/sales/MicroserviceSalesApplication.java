package com.microservice.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;  // Correct import

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSalesApplication.class, args);
	}

}
