package com.tsintergy.simple.algorithm.core.store.sftp;

import com.tsintergy.simple.algorithm.core.exception.StoreException;
import com.tsintergy.simple.algorithm.core.properties.SftpStoreProperties;
import com.tsintergy.simple.algorithm.core.store.AbstractStoreManager;
import com.tsintergy.simple.algorithm.core.util.ZipUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;

/**
 * sftp储存服务
 * todo:暂不支持不压缩，和不转文本
 * @author angel
 * @date 2020/2/5 13:10
 */
public class SftpStoreManager extends AbstractStoreManager<SftpFileInfo> {

    private Logger logger = LoggerFactory.getLogger(SftpStoreManager.class);

    /**
     * 是否需要base64转文本
     * 以防万一,传输只能是文本时使用，正常不需要
     */
    private Boolean base64Encode = false;

    private Boolean compress = true;
    /**
     *
     * @param remoteFileInfo 远程文件属性
     * @param localPath 本地文件夹路径
     * @throws StoreException
     */
    @Override
    public void doUpload(SftpFileInfo remoteFileInfo, String localPath) throws StoreException {
        File localFile = new File(localPath);
        if (!localFile.exists()) {
            throw new StoreException("file not exist:" + localPath);
        }
        //1.将本地文件进行预处理（压缩和转文本）
        File compressFile = localFile;
        if (compress) {
            String compressPath = compress(localPath);
            compressFile = new File(compressPath);
            if (StringUtils.isEmpty(remoteFileInfo.getFileName())) {
                remoteFileInfo.setFileName("IN.zip");
            }
        }
        if (base64Encode) {
            encode(compressFile.getAbsolutePath());
        }
        //2.使用vfs辅助上传文件
        try (StandardFileSystemManager manager = new StandardFileSystemManager()) {
            manager.init();
            FileObject localFileObject = manager.resolveFile(compressFile.getAbsolutePath());
            FileObject remoteFileObject = manager.resolveFile(createConnectionUrl(remoteFileInfo));
            remoteFileObject.copyFrom(localFileObject, Selectors.SELECT_SELF);
        } catch (FileSystemException e) {
            throw new StoreException("sftp transform fail" , e);
        }
        //3.上传成功后，将本地压缩文件删除
        try {
            FileUtils.forceDeleteOnExit(compressFile);
        } catch (IOException e) {
            throw new StoreException("error delete local zip file:" + compressFile.getAbsolutePath(), e);
        }

    }

    @Override
    public File doDownload(SftpFileInfo targetFileInfo, String localPath) throws StoreException {
        File downFile = null;
        //1.从远程下载文件
        try (StandardFileSystemManager manager = new StandardFileSystemManager()) {
            manager.init();
            FileObject localFileObject = manager.resolveFile(localPath+File.separator+targetFileInfo.getFileName());
            FileSystemOptions options = new FileSystemOptions();
            //服务器系统重装，服务器间IP地址交换，DHCP，虚拟机重建，中间人劫持公钥会发生变化，
            // 为了不需要频繁修改配置文件，暂不知道如何处理好，先不检验
            SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
                    options, "no");
            //从用户根目录开始
            SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(options, true);
            SftpFileSystemConfigBuilder.getInstance().setSessionTimeoutMillis(options, 60000);
            FileObject remoteFileObject = manager.resolveFile(createConnectionUrl(targetFileInfo), options);
            localFileObject.copyFrom(remoteFileObject, Selectors.SELECT_SELF);
            //file:\D:\现货平台\算法资料\5算法\workspace\CASE_angel1\IN.zip
            downFile = new File(localFileObject.getPublicURIString());//todo:test
        } catch (Exception e) {
            throw new StoreException("SFTP download fail:" + e);
        }
        //2.解压并删除压缩文件
        if (compress) {
            downFile = uncompress(targetFileInfo, localPath);
        }
        return downFile;
    }

    protected String compress(String localPath) throws StoreException {
        //fixme:将压缩后的根目录名字改为IN
        String out = localPath + ".zip";
        File localFile = new File(localPath);
        if (!localFile.isDirectory()) {
            out = localFile.getParent() + File.separator
                    + localFile.getName().substring(0, localFile.getName().lastIndexOf(".")) + ".zip";
        }
        try {
            ZipUtil.toZip(localPath, out, true);
        } catch (Exception e) {
            throw new StoreException("zip fail:" + localPath, e);
        }
        return out;
    }

    protected File uncompress(SftpFileInfo sftpFileInfo,String localPath) throws StoreException {
        String src = localPath + File.separator + sftpFileInfo.getFileName();
        File unZip;
        try {
            unZip = ZipUtil.unZip(src, localPath);
        } catch (Exception e) {
            throw new StoreException("unzip fail:" + src, e);
        }
        try {
            FileUtils.forceDeleteOnExit(new File(src));
        } catch (IOException e) {
            throw new StoreException("delete zip fail:" + src, e);
        }
        return unZip;
    }

    protected void encode(String localPath) {

    }

    private String createConnectionUrl(SftpFileInfo sftpFileInfo) {
        SftpStoreProperties sftpStoreProperties = sftpFileInfo.getSftpStoreProperties();
        String url = "sftp://" + sftpStoreProperties.getUsername() + ":" + sftpStoreProperties.getPassword()
                + "@" + sftpStoreProperties.getHostIp() + ":" + sftpStoreProperties.getPort() +
                sftpStoreProperties.getRemoteRootPath() + File.separator
                + sftpFileInfo.getDirectory() + File.separator + sftpFileInfo.getFileName();
        logger.info("SFTP upload command:{}", url);
        return url;
    }

    public Boolean getBase64Encode() {
        return base64Encode;
    }

//    public void setBase64Encode(Boolean base64Encode) {
//        this.base64Encode = base64Encode;
//    }

    public Boolean getCompress() {
        return compress;
    }

//    public void setCompress(Boolean compress) {
//        this.compress = compress;
//    }



    public static void main(String[] args) throws StoreException {
        SftpStoreManager sftpStoreManager = new SftpStoreManager();
        SftpFileInfo sftpFileInfo = new SftpFileInfo();
        SftpStoreProperties sftpProperties = new SftpStoreProperties();
        sftpProperties.setHostIp("tmos.devops.tsintergy.com");
        sftpProperties.setPort(10010);
        sftpProperties.setUsername("tmos");
        sftpProperties.setPassword("qinghua123%40");
        sftpProperties.setRemoteRootPath("algorithm/bin");
        sftpFileInfo.setSftpStoreProperties(sftpProperties);
        sftpFileInfo.setDirectory("/topo");
        sftpFileInfo.setFileName("/IN.zip");
        //上传后位置：/home/jxasm/test/run/IN.zip
        sftpStoreManager.doUpload(sftpFileInfo,"D:\\现货平台\\算法资料\\5算法\\拓扑分析\\0318\\IN_TOPO");
        //下载后位置：C:\Workspace\Document\algorithm\algorithm
//        sftpStoreManager.doDownload(sftpFileInfo,"D:\\现货平台\\算法资料\\5算法\\workspace\\CASE_angel1");
    }
}
