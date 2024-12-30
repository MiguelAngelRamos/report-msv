package com.kibernumacademy.report.models;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
  private Long id;
  private String name;
  private String founder;
  private String logo;

  @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyy")
  private LocalDate foundationDate;

  private List<WebSite> webSites;
}
