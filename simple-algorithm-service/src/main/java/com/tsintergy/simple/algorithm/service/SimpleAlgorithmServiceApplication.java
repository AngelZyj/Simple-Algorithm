package com.tsintergy.simple.algorithm.service;

import com.tsintergy.simple.algorithm.core.properties.AlgorithmProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = {AlgorithmProperties.class})
@SpringBootApplication
public class SimpleAlgorithmServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAlgorithmServiceApplication.class, args);
    }

}
