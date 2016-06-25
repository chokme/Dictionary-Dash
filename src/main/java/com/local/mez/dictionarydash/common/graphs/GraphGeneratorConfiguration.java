package com.local.mez.dictionarydash.common.graphs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphGeneratorConfiguration {

    @Bean
    public GraphGenerator graphGenerator() {
        return new GraphGenerator(graphProcessor());
    }

    public GraphProcessor<String> graphProcessor() {
        return new SingleCharDiffGraphProcessor();
    }
}
