package com.tsintergy.simple.algorithm.core.properties;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/8 15:43
 */
public class OSSStoreProperties implements IStoreProperties, Serializable {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    /**
     * 阿里OSS对象存储服务的bucketName
     */
    private String bucket;

    /**
     * 文件上传的目录，可以为空
     */
    private String uploadDir;

    /**
     * 是否进行加解密处理
     */
    private boolean isEncryption = false;

    /**
     * des加解密的key值
     */
    private String desKey;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getDesKey() {
        return desKey;
    }

    public void setDesKey(String desKey) {
        this.desKey = desKey;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public boolean isEncryption() {
        return isEncryption;
    }

    public void setEncryption(boolean encryption) {
        isEncryption = encryption;
    }
}
