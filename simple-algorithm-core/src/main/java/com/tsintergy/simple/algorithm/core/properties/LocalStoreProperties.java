package com.tsintergy.simple.algorithm.core.properties;

import java.io.Serializable;

/**
 * SftpProperties
 *
 * @author angel
 * @date 2020/3/9 12:55
 */
public class LocalStoreProperties implements IStoreProperties,Serializable {

    /**
     * 基础目录
     */
    private String basePath;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
