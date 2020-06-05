package com.tsintergy.simple.algorithm.core.store.local;

import com.tsintergy.simple.algorithm.core.exception.StoreException;
import com.tsintergy.simple.algorithm.core.store.AbstractStoreManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class LocalStoreManager extends AbstractStoreManager<LocalFileInfo> {

    @Override
    public void doUpload(LocalFileInfo sourceFile, String localPath) throws StoreException {
        String dest = sourceFile.getLocalStoreProperties().getBasePath() + File.separator + sourceFile.getDirectory();
        try {
            File destDir = new File(dest);
//            if (!destDir.exists()) {
//                destDir.mkdirs();
//            }
            FileUtils.copyDirectoryToDirectory(new File(localPath), destDir);
        } catch (IOException e) {
            throw new StoreException("upload files fail", e);
        }
    }

    @Override
    public File doDownload(LocalFileInfo targetFile, String localPath) throws StoreException {
        String src = targetFile.getLocalStoreProperties().getBasePath()
                + File.separator + targetFile.getDirectory()
                +File.separator+targetFile.getFileName();
        try {
            File srcFile = new File(src);
            File local = new File(localPath);
            FileUtils.copyDirectoryToDirectory(srcFile, local);
            File downloadFile = new File(localPath + File.separator + srcFile.getName());
            return downloadFile;
        } catch (IOException e) {
            throw new StoreException("download files fail", e);
        }
    }

  /*  public static void main(String[] args) throws StoreException {
        LocalStoreProperties localStoreProperties = new LocalStoreProperties();
        localStoreProperties.setBasePath("D:\\现货平台\\算法资料\\5算法\\");
        LocalFileInfo fileInfo = new LocalFileInfo();
        fileInfo.setLocalStoreProperties(localStoreProperties);
        fileInfo.setDirectory("拓扑分析\\0318\\IN_TOPO_test");
        LocalStoreManager manager = new LocalStoreManager();
        manager.doUpload(fileInfo,"D:\\现货平台\\算法资料\\5算法\\拓扑分析\\0318\\IN_TOPO");
//        manager.doDownload(fileInfo,"D:\\现货平台\\算法资料\\5算法\\workspace\\CASE_angel1");
    }*/

}
