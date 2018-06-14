package com.cacti.workshop.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Dia14EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Dia14EurekaServerApplication.class, args);
	}
}
