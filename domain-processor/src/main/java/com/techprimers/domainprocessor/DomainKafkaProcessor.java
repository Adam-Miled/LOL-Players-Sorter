package com.techprimers.domainprocessor;

import com.techprimers.domaincrawler.Champion;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Function;

@Configuration
public class DomainKafkaProcessor {

  @Bean
  public Function<KStream<String, Champion>, KStream<String, Champion>> domainProcessor() {

    return kstream -> kstream.filter((key, champion) -> {

      boolean result = champion.getHotStreak() ;

      if (result) {
        System.out.println("GOLD PLAYER: " + champion.getSummonerName());
      } else {
        System.out.println("CASUAL PLAYER: " + champion.getSummonerName());
      }
      return result;
    });

  }

}
