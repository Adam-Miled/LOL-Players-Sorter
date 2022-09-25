package com.techprimers.domaincrawler;

import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ChampionCrawlerService {

  private KafkaTemplate<String, Champion> kafkaTemplate;
  private final String KAFKA_TOPIC = "unprocessed_champs";

  public ChampionCrawlerService(KafkaTemplate<String, Champion> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }


  public void crawl(String name) {

    Flux<Champion> domainListMono = WebClient.create()
        .get()
        .uri("https://euw1.api.riotgames.com/lol/league-exp/v4/entries/RANKED_SOLO_5x5/PLATINUM/I?page=1&api_key=RGAPI-fe9fcb3e-32ef-4ddd-99d5-426c2c5426dc")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(Champion.class);





    domainListMono.subscribe(champion -> {
            Champion champion1 = (Champion) champion;

            kafkaTemplate.send(KAFKA_TOPIC, champion1);
            System.out.println("The Champion's Summoner name is : " + champion1.getSummonerName());

    });

  }
}
