package com.tsintergy.simple.algorithm.core.store.oss;


import com.tsintergy.simple.algorithm.core.properties.OSSStoreProperties;
import com.tsintergy.simple.algorithm.core.store.BaseFileInfo;

/**
 * <p>
 *     根据南瑞规范定义文件信息
 *     //TODO OSSFileInfo的属性需要确定
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/5/30 18:44
 */
public class OSSFileInfo extends BaseFileInfo {

    private OSSStoreProperties ossProperteis;
    private String bucketName;
    private String objectName;

    public OSSStoreProperties getOssProperteis() {
        return ossProperteis;
    }

    public void setOssProperteis(OSSStoreProperties ossProperteis) {
        this.ossProperteis = ossProperteis;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }


}
