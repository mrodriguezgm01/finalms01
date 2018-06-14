package com.cacti.workshop.microservices.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "catalog", type = "product")
public class Product {
  @Id
  private String id;
  private String name;
  private String description;
}
