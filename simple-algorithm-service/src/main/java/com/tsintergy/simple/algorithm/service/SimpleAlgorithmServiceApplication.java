package com.tsintergy.simple.algorithm.service;

import com.tsintergy.simple.algorithm.core.properties.AlgorithmProperties;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

@EnableConfigurationProperties(value = {AlgorithmProperties.class})
@EntityScan("com.tsintergy.**.pojo")
@EnableDubbo
@ImportResource(value = {"classpath:dubbo/*.xml"})
@SpringBootApplication
public class SimpleAlgorithmServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAlgorithmServiceApplication.class, args);
    }

}
