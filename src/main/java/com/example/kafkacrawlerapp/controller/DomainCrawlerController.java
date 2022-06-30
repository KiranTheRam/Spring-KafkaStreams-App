package com.example.kafkacrawlerapp.controller;

import com.example.kafkacrawlerapp.service.DomainCrawlerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/domain")
public class DomainCrawlerController {

    private DomainCrawlerService domainCrawlerService;

    public DomainCrawlerController(DomainCrawlerService domainCrawlerService) {
        this.domainCrawlerService = domainCrawlerService;
    }


    @GetMapping("/lookup/{domainName}")
    public String lookUp(@PathVariable("domainName") final String domainName) {
        domainCrawlerService.crawl(domainName);
        return "Domain crawler has scraped requested data";
    }

}
