package com.kibernumacademy.report.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kibernumacademy.report.helpers.ReportHelper;
import com.kibernumacademy.report.models.Company;
import com.kibernumacademy.report.models.WebSite;
import com.kibernumacademy.report.repositories.CompanyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService{

  private final CompanyRepository companyRepository;
  private final ReportHelper reportHelper;

  @Override
  public String makeReport(String name) {
   Company company = this.companyRepository.getByName(name).orElseThrow();
   return reportHelper.getReportTemplate(company);
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteReport'");
  }
  
}
