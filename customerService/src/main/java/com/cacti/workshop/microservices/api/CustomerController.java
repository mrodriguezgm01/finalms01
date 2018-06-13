package com.cacti.workshop.microservices.api;

import com.cacti.workshop.microservices.model.Customer;
import com.cacti.workshop.microservices.CustomerServiceApplication;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    public CustomerController(Receiver receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

  @PostMapping
  public HttpEntity addCustomer(@RequestBody Customer customer) throws Exception {
    rabbitTemplate.convertAndSend(CustomerServiceApplication.topicExchangeName, "CUSTOMER", customer.toString());
    receiver.getLatch().await(500, TimeUnit.MILLISECONDS);
    return ResponseEntity.ok("Enviando a cola cliente : "+customer.toString());
  }
}
