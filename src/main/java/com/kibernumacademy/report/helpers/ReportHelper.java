package com.kibernumacademy.report.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kibernumacademy.report.models.Company;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ReportHelper {
  @Value("${report.template}")
  private String reportTemplate;

  public String getReportTemplate(Company company) {
    return this.reportTemplate
      .replace("{company}", company.getName())
      .replace("{foundation_date}", company.getFoundationDate().toString())
      .replace("{founder}", company.getFounder())
      .replace("{web_sites}", company.getWebSites().toString());
  }

}
