package com.cacti.workshop.microservices.repositories;

import com.cacti.workshop.microservices.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
}
