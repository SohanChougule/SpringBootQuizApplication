package com.example.servicecregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServicecRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicecRegistryApplication.class, args);
	}

}
