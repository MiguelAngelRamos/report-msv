package com.kibernumacademy.report.beans;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
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
    
    // Configurar el maximo tiempo de ejecucion permitido
    TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
      .timeoutDuration(Duration.ofMillis(100)) // tiempo maximo para ejecutar la llamada
      .build();

    /*
     * Tiempo máximo de permitido, si una llamada al servicio tarda más de 100ms, el Time Limiter marca esa llamada como fallida
     */

     // Retornamos un Customizer que aplica las configuraciones anteriores 
     return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
      .circuitBreakerConfig(circuitbreakerConfig)
      .timeLimiterConfig(timeLimiterConfig)
      .build());
  }
  
}
