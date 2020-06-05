package com.tsintergy.simple.algorithm.core.store.sftp;


import com.tsintergy.simple.algorithm.core.properties.SftpStoreProperties;
import com.tsintergy.simple.algorithm.core.store.BaseFileInfo;

public class SftpFileInfo extends BaseFileInfo {

    /**
     * sftp配置
     */
    private SftpStoreProperties sftpStoreProperties;

    /**
     * sftp文件相对于sftpProperties.remoteRootPath当前文件所在目录
     */
    private String directory;

    /**
     * sftp文件名字
     */
    private String fileName;


    public SftpStoreProperties getSftpStoreProperties() {
        return sftpStoreProperties;
    }

    public void setSftpStoreProperties(SftpStoreProperties sftpStoreProperties) {
        this.sftpStoreProperties = sftpStoreProperties;
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
