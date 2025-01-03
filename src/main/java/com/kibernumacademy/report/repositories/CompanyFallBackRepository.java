package com.kibernumacademy.report.repositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.kibernumacademy.report.models.Company;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class CompanyFallBackRepository {
  
  private final WebClient webClient;
  private final String uri;

  public CompanyFallBackRepository(WebClient webClient, @Value("${fallback.uri}") String uri) {
    this.webClient = webClient;
    this.uri = uri;
  }

  // Llamada al Servicio
  public Company getCompany(String name) {
    log.warn("Llamando al servicio de fallback company {}", uri);
    return this.webClient
      .get()
      .uri(uri, name)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(Company.class)
      .log()
      .block();
  }

  
}
