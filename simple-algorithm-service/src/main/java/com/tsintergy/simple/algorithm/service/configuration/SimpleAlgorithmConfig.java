package com.tsintergy.simple.algorithm.service.configuration;

import com.tsintergy.simple.algorithm.api.ProcessLogService;
import com.tsintergy.simple.algorithm.api.ProcessResultService;
import com.tsintergy.simple.algorithm.service.impl.ProcessLogServiceImpl;
import com.tsintergy.simple.algorithm.service.impl.ProcessResultServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author angel
 * @description
 * @date 2020/6/9 11:53
 */
@Configuration
public class SimpleAlgorithmConfig {

    private static final Logger logger = LoggerFactory.getLogger(SimpleAlgorithmConfig.class);

    @ConditionalOnMissingBean
    @Bean
    public ProcessLogService processLogService() {
        return new ProcessLogServiceImpl();
    }

    @ConditionalOnMissingBean
    @Bean
    public ProcessResultService processResultService() {
        return new ProcessResultServiceImpl();
    }


}
