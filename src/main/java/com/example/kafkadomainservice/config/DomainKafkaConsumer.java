package com.example.kafkadomainservice.config;

import com.example.kafkadomainService.model.Domain;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DomainKafkaConsumer {

    Logger log = LoggerFactory.getLogger(DomainKafkaConsumer.class.getSimpleName());

    @Bean
    public Consumer<KStream<String, Domain>> domainService() {
        return kstream -> kstream.foreach((key, domain) -> {
            log.info(String.format("Domain Consumed [%s] Status [%s]", domain.getDomain(), domain.isDead()));
        });
    }
}
