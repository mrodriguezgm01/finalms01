package com.cacti.workshop.microservices.api;

import com.cacti.workshop.microservices.model.Product;
import com.cacti.workshop.microservices.repositories.ProductRepository;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/product")
public class ProductCatalogController {

  private final ProductRepository productRepository;

  public ProductCatalogController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @PostMapping
  public HttpEntity create(@RequestBody Product product) {
    return ok(productRepository.index(product));
  }
}
