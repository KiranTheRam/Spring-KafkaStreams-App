package com.example.kafkadomainprocessor.config;

import com.example.kafkadomainprocessor.model.Domain;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class DomainKafkaProcessor {
    private Logger log = LoggerFactory.getLogger(DomainKafkaProcessor.class.getSimpleName());

    @Bean
    public Function<KStream<String, Domain>, KStream<String, Domain>> domainProcessor() {
        return kstream -> kstream.filter((key, domain) -> {
            if (domain.isDead()) {
                log.info("Inactive Domain: " + domain.getDomain());
            } else {
                log.info("Active Domain: " + domain.getDomain());
            }
            return !domain.isDead();
        });

    }
}
