package com.techprimers.domainservice;

import com.techprimers.domaincrawler.Champion;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class DomainKafkaConsumer {

  @Bean
  public Consumer<KStream<String, Champion>> domainService() {
    return kstream -> kstream.foreach((key, champion) -> {
      System.out.println(String.format(" The Champion [%s] is a HOT STREAK player", champion.getSummonerName()));
    });
  }
}
