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

    /**
     * 存储服务配置
     */
    private StoreProperties store;

    /**
     * 算法工作路径
     */
    private String workspace;

    public StoreProperties getStore() {
        return store;
    }

    public void setStore(StoreProperties store) {
        this.store = store;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }
}
