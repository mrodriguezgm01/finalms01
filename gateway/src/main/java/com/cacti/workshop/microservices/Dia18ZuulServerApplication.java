package com.cacti.workshop.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class Dia18ZuulServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(Dia18ZuulServerApplication.class, args);
  }
}
