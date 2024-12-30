package com.kibernumacademy.report.beans;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoadBalancerConfiguration {
  @Bean
  public ServiceInstanceListSupplier serviceInstanceListSupplier(ConfigurableApplicationContext context) {
    log.info("Configurando el balanceador de carga");
    return ServiceInstanceListSupplier.builder()
    .withBlockingDiscoveryClient()
    .build(context);
  }
}
