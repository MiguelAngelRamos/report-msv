package com.kibernumacademy.report.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kibernumacademy.report.services.ReportService;

@RestController
@RequestMapping("report")
public class ReportController {

  private final ReportService reportService;

  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }

  // localhost:7070/report/{name}
  // localhost:7070/report/Facebook
  @GetMapping("{name}")
  public ResponseEntity<Map<String, String>> getReport(@PathVariable String name) {
   return ResponseEntity.ok(Map.of("report", reportService.makeReport(name)));
  }

  
}
