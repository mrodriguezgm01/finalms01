package com.cacti.workshop.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Dia15ProductCatalogServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(Dia15ProductCatalogServiceApplication.class, args);
  }
}
