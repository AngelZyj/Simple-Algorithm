package com.tsintergy.simple.algorithm.core.store.local;

import com.tsintergy.simple.algorithm.core.properties.LocalStoreProperties;
import com.tsintergy.simple.algorithm.core.store.BaseFileInfo;

public class LocalFileInfo extends BaseFileInfo {

    private LocalStoreProperties localStoreProperties;

    /**
     * 文件相对于localStoreProperties.basePath当前文件所在目录
     */
    private String directory;

    /**
     * 文件名字
     */
    private String fileName;

    public LocalStoreProperties getLocalStoreProperties() {
        return localStoreProperties;
    }

    public void setLocalStoreProperties(LocalStoreProperties localStoreProperties) {
        this.localStoreProperties = localStoreProperties;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
