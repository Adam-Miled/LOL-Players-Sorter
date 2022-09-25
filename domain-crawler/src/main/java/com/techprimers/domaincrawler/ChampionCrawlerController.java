package com.techprimers.domaincrawler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain")
public class ChampionCrawlerController {


  private ChampionCrawlerService domainCrawlerService;

  public ChampionCrawlerController(ChampionCrawlerService domainCrawlerService) {
    this.domainCrawlerService = domainCrawlerService;
  }

  @GetMapping("/lookup/{summonerName}")
  public String lookup(@PathVariable("summonerName") final String name) {
    domainCrawlerService.crawl(name);
    return "Domain crawler has scrapped your data";
  }
}
