package com.kibernumacademy.report.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  /*
    {TechNova} was founded in {12/04/2020} by {Liam Carter} the websites from this company are {technova.io, technova.blog, technova.shop}
   * 
   */

  public List<String> getPlaceHoldersFromTemplate(String template) {
    List<String> placeholders = new ArrayList<>();
    Pattern pattern = Pattern.compile("\\{(.*?)}\\}"); // Busca cualquier texto que estre entre {}  El cuantificador es .*? "no codicioso"
    Matcher matcher = pattern.matcher(template);

    while(matcher.find()) {
      placeholders.add(matcher.group(1));
    /**
     * 
     *  placeholders.add("TechNova");
     *  placeholders.add("12/04/2020");
     *  placeholders.add("Liam Carter");
     *  placeholders.add("technova.io, technova.blog, technova.shop");
     */
    }
    return placeholders;
   
  }
}
