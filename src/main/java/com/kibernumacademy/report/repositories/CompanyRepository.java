package com.kibernumacademy.report.repositories;

import java.util.Optional;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kibernumacademy.report.beans.LoadBalancerConfiguration;
import com.kibernumacademy.report.models.Company;

@FeignClient(name="company-service")
@LoadBalancerClient(name="company-service", configuration=LoadBalancerConfiguration.class)
public interface CompanyRepository {

  @GetMapping("/company/{name}")
  Optional<Company> getByName(@PathVariable String name);

  @PostMapping("/company")
  Company postByName(@RequestBody Company company);
}
