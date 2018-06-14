package com.cacti.workshop.microservices.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "catalog", type = "account")
public class Account {
  @Id
  public String noCuenta;
  public String saldo;
  public String fechUltiTran;
  public String idCliente;
}
