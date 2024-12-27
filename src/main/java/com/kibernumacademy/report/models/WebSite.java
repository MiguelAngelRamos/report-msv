package com.kibernumacademy.report.models;

import lombok.Data;


@Data
public class WebSite {

  private Long id;
  private String name;
  private String url;
  private Category category;
  private String description;
  private Company company;
}
