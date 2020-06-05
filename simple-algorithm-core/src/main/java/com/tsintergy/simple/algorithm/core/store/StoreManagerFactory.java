package com.tsintergy.simple.algorithm.core.store;


import com.tsintergy.simple.algorithm.core.enums.StoreTypeEnum;
import com.tsintergy.simple.algorithm.core.exception.StoreException;
import com.tsintergy.simple.algorithm.core.store.local.LocalStoreManager;
import com.tsintergy.simple.algorithm.core.store.oss.OSSStoreManager;
import com.tsintergy.simple.algorithm.core.store.sftp.SftpStoreManager;

import java.util.Objects;

/**
 * StoreManagerFactory
 * 存储管理工厂类
 * @author angel
 * @date 2020/3/22 19:45
 */
public class StoreManagerFactory {

    /**
     * @param storeType
     * @return
     * @throws StoreException
     */
    public static AbstractStoreManager<? extends BaseFileInfo> createStoreManager(StoreTypeEnum storeType) throws StoreException {
        if (Objects.equals(StoreTypeEnum.OSS, storeType)) {
            return new OSSStoreManager();
        }else if (Objects.equals(StoreTypeEnum.SFTP, storeType)) {
            return new SftpStoreManager();
        }else if (Objects.equals(StoreTypeEnum.LOCAL, storeType)) {
            return new LocalStoreManager();
        }
        throw new StoreException("no match storeManager !");
    }
}
