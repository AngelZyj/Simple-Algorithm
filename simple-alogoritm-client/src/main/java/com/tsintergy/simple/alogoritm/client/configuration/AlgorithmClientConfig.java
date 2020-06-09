package com.tsintergy.simple.alogoritm.client.configuration;

import com.tsintergy.simple.alogoritm.client.api.AlgorithmClient;
import com.tsintergy.simple.alogoritm.client.impl.AlgorithmClientServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author angel
 * @description
 * @date 2020/6/9 18:43
 */
@Configuration
public class AlgorithmClientConfig {

    @ConditionalOnMissingBean
    @Bean
    AlgorithmClient algorithmClient() {
        return new AlgorithmClientServiceImpl();
    }
}
