package com.tsintergy.simple.algorithm.core.store;

import com.tsintergy.simple.algorithm.core.exception.StoreException;

import java.io.File;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/5 12:44
 */
public interface IStoreManager<T extends BaseFileInfo> {

    /**
     * 上传文件
     * @param sourceFile
     * @param localPath
     * @throws StoreException
     */
    void upload(T sourceFile, String localPath) throws StoreException;

    /**
     * 下载文件
     * @param targetFile
     * @param localPath
     * @return 下载后文件文件
     * @throws StoreException
     */
    File download(T targetFile, String localPath) throws StoreException;

}
