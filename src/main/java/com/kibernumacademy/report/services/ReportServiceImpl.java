package com.kibernumacademy.report.services;

import org.springframework.stereotype.Service;

import com.kibernumacademy.report.helpers.ReportHelper;
import com.kibernumacademy.report.models.Company;
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
  public String saveReport(String nameReport) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveReport'");
  }

  @Override
  public void deleteReport(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteReport'");
  }
  
}
