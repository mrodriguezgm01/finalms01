package com.cacti.workshop.microservices.api;

import com.cacti.workshop.microservices.model.Account;
import com.cacti.workshop.microservices.repositories.AccountRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.HashMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.ok;
import java.util.List;
import java.util.Map;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
public class AccountCatalogController {

  private final AccountRepository accountRepository;

  public AccountCatalogController(AccountRepository accountRepository) {
    this.accountRepository = accountRepository; 
  }

  @RequestMapping("/account_bank")
  @PostMapping
  @HystrixCommand(fallbackMethod = "fallbackPost")
  public HttpEntity create(@RequestBody Account account) {
	  return ok(accountRepository.index(account));
  }
      
  
  @RequestMapping("/account_bank/{id}")
  @GetMapping
  @HystrixCommand(fallbackMethod = "fallbackGet")
  public HttpEntity list(@PathVariable String id) {
	  Page<Account> accounts = accountRepository.findByidCliente(id, new PageRequest(0, 100));
	  List<Account> laccount = accounts.getContent();
	  return ok( laccount);
  }
  
  public HttpEntity fallbackPost(Account account) {
    Map<String, Object> resultado = new HashMap();
    resultado.put("Error", "Sin Respuesta a POST de Elastic Search. Respuesta automatica de Circuit Braker");
    return new ResponseEntity<>(resultado, HttpStatus.REQUEST_TIMEOUT);
  }
    
  public HttpEntity fallbackGet(String id) {
    Map<String, Object> resultado = new HashMap();
    resultado.put("Error", "Sin Respuesta a GET de Elastic Search. Respuesta automatica de Circuit Braker");
    return new ResponseEntity<>(resultado, HttpStatus.REQUEST_TIMEOUT);
  }
   
}

