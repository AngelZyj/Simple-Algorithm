package com.tsintergy.simple.algorithm.core.store;


import com.tsintergy.simple.algorithm.core.enums.StoreTypeEnum;
import com.tsintergy.simple.algorithm.core.exception.StoreException;
import com.tsintergy.simple.algorithm.core.properties.StoreProperties;
import com.tsintergy.simple.algorithm.core.store.local.LocalFileInfo;
import com.tsintergy.simple.algorithm.core.store.local.LocalStoreManager;
import com.tsintergy.simple.algorithm.core.store.oss.OSSFileInfo;
import com.tsintergy.simple.algorithm.core.store.oss.OSSStoreManager;
import com.tsintergy.simple.algorithm.core.store.sftp.SftpFileInfo;
import com.tsintergy.simple.algorithm.core.store.sftp.SftpStoreManager;
import org.springframework.util.StringUtils;

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

    public static <T extends BaseFileInfo> T createBaseFileInfo(StoreProperties storeProperties, String remoteDir, String remoteFileName) throws StoreException {
        if (Objects.equals(StoreTypeEnum.OSS, storeProperties.getStoreType())) {
            OSSFileInfo ossFileInfo = new OSSFileInfo();
            ossFileInfo.setOssProperteis(storeProperties.toStoreProperties());
            if (StringUtils.isEmpty(remoteDir)) {
                ossFileInfo.setObjectName(remoteFileName);
            } else {
                ossFileInfo.setObjectName(remoteDir + "/" + remoteFileName);
            }
            return (T) new OSSFileInfo();
        }else if (Objects.equals(StoreTypeEnum.SFTP, storeProperties.getStoreType())) {
            SftpFileInfo sftpFileInfo = new SftpFileInfo();
            sftpFileInfo.setSftpStoreProperties(storeProperties.toStoreProperties());
            sftpFileInfo.setDirectory(remoteDir);
            sftpFileInfo.setFileName(remoteFileName);
            return (T) sftpFileInfo;
        } else if (Objects.equals(StoreTypeEnum.LOCAL, storeProperties.getStoreType())) {
            LocalFileInfo localFileInfo = new LocalFileInfo();
            localFileInfo.setLocalStoreProperties(storeProperties.toStoreProperties());
            localFileInfo.setDirectory(remoteDir);
            localFileInfo.setFileName(remoteFileName);
            return (T) localFileInfo;
        }
        throw new StoreException("can't create FileInfo !");
    }
}
