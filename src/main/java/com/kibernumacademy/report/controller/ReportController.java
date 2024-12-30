package com.kibernumacademy.report.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kibernumacademy.report.services.ReportService;

import feign.Response;

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

  //*  {MovieFilms} was founded in {20/07/2021} by {Sofia Araya} the websites from this company are {films.io, films.blog, films.shop} */
  @PostMapping()
  public ResponseEntity<String> saveReport(@RequestBody String report) {
    String response = this.reportService.saveReport(report);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("{name}")
  public ResponseEntity<Void> deleteReport(@PathVariable String name) {
    this.reportService.deleteReport(name);
    return ResponseEntity.noContent().build();
  }

  
}
