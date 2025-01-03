package com.kibernumacademy.report.beans;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;

@Configuration
public class CircuitBreakerBean {

  @Bean
  public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
    CircuitBreakerConfig circuitbreakerConfig = CircuitBreakerConfig.custom()
    .failureRateThreshold(50)
    .waitDurationInOpenState(Duration.ofMillis(1000))
    .slidingWindowSize(2)
    .build();
    
  }
  
}
