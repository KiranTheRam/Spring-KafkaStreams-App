package com.example.kafkacrawlerapp.service;

import com.example.kafkacrawlerapp.model.DomainList;
import com.example.kafkacrawlerapp.model.Domain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DomainCrawlerService {

    private final Logger log = LoggerFactory.getLogger(DomainCrawlerService.class.getSimpleName());

    private final String KAFKA_TOPIC = "web-domains";

    private KafkaTemplate<String, Domain> kafkaTemplate;

    public DomainCrawlerService(KafkaTemplate<String, Domain> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void crawl(String name) {

        Mono<DomainList> domainListMono = WebClient.create()
                .get()
                .uri("https://api.domainsdb.info/v1/domains/search?limit=50&domain=" + name)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(DomainList.class);


        domainListMono.subscribe(domainList -> {
            domainList.domains
                    .forEach(domain -> {
                        kafkaTemplate.send(KAFKA_TOPIC, domain);
                        System.out.println("Domain message" + domain.getDomain());
                    });
        });

    }
}
