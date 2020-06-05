package com.tsintergy.simple.algorithm.core.store;

import com.tsintergy.simple.algorithm.core.exception.StoreException;
import com.tsintergy.simple.algorithm.core.util.Md5ValidUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/5 13:10
 */
public abstract class AbstractStoreManager<T extends BaseFileInfo> implements IStoreManager<T> {

    private boolean validMd5 = true;

    @Override
    public void upload(T sourceFile, String localPath) throws StoreException {
        String md5 = null;
        if(validMd5){
            try {
                md5 = Md5ValidUtil.digestMd5(new FileInputStream(localPath));
            } catch (IOException e) {
                throw new StoreException("计算md5失败, localPath = " + localPath, e);
            }
        }
        sourceFile.setMd5(md5);
        doUpload(sourceFile, localPath);
    }

    @Override
    public File download(T targetFile, String localPath) throws StoreException {
        File file = doDownload(targetFile, localPath);
        if(validMd5){
            Md5ValidUtil.valid(localPath, targetFile.getMd5());
        }
        return file;
    }

    /**
     * 上传文件
     * @param sourceFile
     * @param localPath
     * @throws StoreException
     */
    abstract protected void doUpload(T sourceFile, String localPath) throws StoreException;

    /**
     * 下载文件
     * @param targetFile
     * @param localPath
     * @return 下载后文件
     * @throws StoreException
     */
    abstract protected File doDownload(T targetFile, String localPath) throws StoreException;

    public boolean isValidMd5() {
        return validMd5;
    }

    public void setValidMd5(boolean validMd5) {
        this.validMd5 = validMd5;
    }

}
