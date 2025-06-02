package com.microservice.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;  // Correct import
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class MicroserviceSalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSalesApplication.class, args);
		
		
	}

}
