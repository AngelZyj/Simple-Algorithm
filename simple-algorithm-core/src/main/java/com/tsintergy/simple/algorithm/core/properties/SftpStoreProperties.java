package com.tsintergy.simple.algorithm.core.properties;

import java.io.Serializable;

/**
 * SftpProperties
 *
 * @author angel
 * @date 2020/3/9 12:55
 */
public class SftpStoreProperties implements IStoreProperties,Serializable {
    /**
     * sftp服务器ip
     */
    private String hostIp;

    /**
     * sftp服务器端口
     */
    private Integer port = 22;

    /**
     * sftp服务器用户名
     */
    private String username;

    /**
     * sftp服务器密码
     */
    private String password;

    /**
     * sftp服务器基础目录
     * 不包括用户根目录(/home/user/)
     */
    private String remoteRootPath;

    /**
     * 文件下载失败下次超时重试时间
     */
    private static Integer downloadSleep=100;

    /**
     * 文件下载失败重试次数
     */
    private static Integer downloadRetry = 3;

    /**
     * 文件上传失败下次超时重试时间
     */
    private static Integer uploadSleep=100;

    /**
     * 文件上传失败重试次数
     */
    private static Integer uploadRettry=3;

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemoteRootPath() {
        return remoteRootPath;
    }

    public void setRemoteRootPath(String remoteRootPath) {
        this.remoteRootPath = remoteRootPath;
    }

    public static Integer getDownloadSleep() {
        return downloadSleep;
    }

    public static void setDownloadSleep(Integer downloadSleep) {
        SftpStoreProperties.downloadSleep = downloadSleep;
    }

    public static Integer getDownloadRetry() {
        return downloadRetry;
    }

    public static void setDownloadRetry(Integer downloadRetry) {
        SftpStoreProperties.downloadRetry = downloadRetry;
    }

    public static Integer getUploadSleep() {
        return uploadSleep;
    }

    public static void setUploadSleep(Integer uploadSleep) {
        SftpStoreProperties.uploadSleep = uploadSleep;
    }

    public static Integer getUploadRettry() {
        return uploadRettry;
    }

    public static void setUploadRettry(Integer uploadRettry) {
        SftpStoreProperties.uploadRettry = uploadRettry;
    }
}
