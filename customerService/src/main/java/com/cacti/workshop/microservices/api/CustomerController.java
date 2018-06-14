package com.cacti.workshop.microservices.api;

import com.cacti.workshop.microservices.model.Customer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.cacti.workshop.microservices.CustomerServiceApplication;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
public class CustomerController {


    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public CustomerController(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

  @RequestMapping("/customer_bank")
  @PostMapping
  @HystrixCommand(fallbackMethod = "fallback")
	public HttpEntity myCircuitBreaker(@RequestBody Customer customer) throws Exception 	{
    rabbitTemplate.convertAndSend(CustomerServiceApplication.topicExchangeName, "CUSTOMER", customer.toString());
    receiver.getLatch().await(500, TimeUnit.MILLISECONDS);
	return ResponseEntity.ok(customer);
	}
	  
	public HttpEntity fallback(Customer customer) {
    	return new ResponseEntity( "{\"Error\":\"Sin Respuesta de la cola. Respuesta automtica de Circuit Braker\"}", HttpStatus.REQUEST_TIMEOUT);
	  }

}
