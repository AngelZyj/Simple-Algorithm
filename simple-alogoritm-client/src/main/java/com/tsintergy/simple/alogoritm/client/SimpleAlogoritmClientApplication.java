package com.tsintergy.simple.alogoritm.client;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;

@EnableDubbo
@ImportResource(value = {"classpath:dubbo/*.xml"})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class SimpleAlogoritmClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimpleAlogoritmClientApplication.class, args);
    }

}
