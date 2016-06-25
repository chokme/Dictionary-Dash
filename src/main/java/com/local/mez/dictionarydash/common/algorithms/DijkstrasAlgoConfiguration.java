package com.local.mez.dictionarydash.common.algorithms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DijkstrasAlgoConfiguration {

    @Bean
    public DijkstrasAlgo dijkstrasAlgo() {
        return new DijkstrasAlgo();
    }
}
