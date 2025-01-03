package com.kibernumacademy.report.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import com.kibernumacademy.report.helpers.ReportHelper;
import com.kibernumacademy.report.models.Company;
import com.kibernumacademy.report.models.WebSite;
import com.kibernumacademy.report.repositories.CompanyFallBackRepository;
import com.kibernumacademy.report.repositories.CompanyRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ReportServiceImpl implements ReportService{

  private final CompanyRepository companyRepository;
  private final CompanyFallBackRepository companyFallBackRepository;
  private final ReportHelper reportHelper;
  private final Resilience4JCircuitBreakerFactory circuitBreakerFactory;

  @Override
  public String makeReport(String name) {
   //Company company = this.companyRepository.getByName(name).orElseThrow();
   // return reportHelper.getReportTemplate(company);

   var circuitbreaker = this.circuitBreakerFactory.create("circuitbreaker");
   return circuitbreaker.run(() -> makeReportMain(name), throwable -> makeReportFallBack(name, throwable));
  }
   
  @Override
  public String saveReport(String report) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    List<String> placeholders =  this.reportHelper.getPlaceHoldersFromTemplate(report);

    var webSites = Arrays.stream(placeholders.get(3).split(","))
                  .map(website -> WebSite.builder()
                                          .name(website.trim())
                                          .build())
                  .toList();
    
    Company company = Company.builder()
      .name(placeholders.get(0))
      .foundationDate(LocalDate.parse(placeholders.get(1), format))
      .founder(placeholders.get(2))
      .webSites(webSites)
      .build();
    this.companyRepository.postByName(company);
    return "Saved Company";
  }

  @Override
  public void deleteReport(String name) {
    this.companyRepository.deleteByName(name);
  }

  public String makeReportMain(String name) {
    Company company = this.companyRepository.getByName(name).orElseThrow();
    return reportHelper.getReportTemplate(company);
   }

  public String makeReportFallBack(String name, Throwable throwable) {
      log.warn(throwable.getMessage());
      return reportHelper.getReportTemplate(this.companyFallBackRepository.getCompany(name));
   }
  
}
