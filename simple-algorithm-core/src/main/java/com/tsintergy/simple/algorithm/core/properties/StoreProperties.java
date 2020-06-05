package com.tsintergy.simple.algorithm.core.properties;



import com.tsintergy.simple.algorithm.core.enums.StoreTypeEnum;
import com.tsintergy.simple.algorithm.core.exception.StoreException;

import java.io.Serializable;
import java.util.Objects;

/**
 * BaseStoreProperties
 * 存储属性的父类
 * @author angel
 * @date 2020/3/15 2:19
 */
public class StoreProperties implements Serializable {

    private StoreTypeEnum storeType;
    private OSSStoreProperties oss;
    private SftpStoreProperties sftp;
    private LocalStoreProperties local;

    public <T extends IStoreProperties> T toStoreProperties() throws StoreException {
        if (Objects.equals(StoreTypeEnum.LOCAL, storeType)) {
            return (T) local;
        } else if (Objects.equals(StoreTypeEnum.SFTP, storeType)) {
            return (T) sftp;
        } else if (Objects.equals(StoreTypeEnum.OSS, storeType)) {
            return (T) oss;
        }
        throw new StoreException("no storeProperties exist !");
    }

    public StoreTypeEnum getStoreType() {
        return storeType;
    }

    public void setStoreType(StoreTypeEnum storeType) {
        this.storeType = storeType;
    }

    public void setOss(OSSStoreProperties oss) {
        this.oss = oss;
    }

    public void setSftp(SftpStoreProperties sftp) {
        this.sftp = sftp;
    }

    public void setLocal(LocalStoreProperties local) {
        this.local = local;
    }
}
