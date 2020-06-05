package com.tsintergy.simple.algorithm.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * @author angel
 * @description
 * @date 2020/6/5 14:59
 */
@ConfigurationProperties(prefix = "algorithm")
public class AlgorithmProperties implements Serializable {

    private StoreProperties store;

    public StoreProperties getStore() {
        return store;
    }

    public void setStore(StoreProperties store) {
        this.store = store;
    }
}
