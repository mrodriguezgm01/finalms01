package com.cacti.workshop.microservices.repositories;

import com.cacti.workshop.microservices.model.Account;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AccountRepository extends ElasticsearchRepository<Account, String> {
	
	Page<Account> findByidCliente(String name, Pageable pageable);
}
