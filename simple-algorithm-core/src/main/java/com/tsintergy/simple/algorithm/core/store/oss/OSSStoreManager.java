package com.tsintergy.simple.algorithm.core.store.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.tsintergy.simple.algorithm.core.exception.StoreException;
import com.tsintergy.simple.algorithm.core.properties.OSSStoreProperties;
import com.tsintergy.simple.algorithm.core.store.AbstractStoreManager;
import org.springframework.util.Assert;

import java.io.File;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/5 12:44
 */
public class OSSStoreManager extends AbstractStoreManager<OSSFileInfo> {

    /**
     * 本地文件上传到oss
     * @param targetFileInfo
     * @param localPath
     */
    @Override
    public void doUpload(OSSFileInfo targetFileInfo, String localPath) throws StoreException {
        valid(targetFileInfo);

        File file = new File(localPath);
        if(!file.exists()){
            throw new StoreException("文件不存在" + localPath);
        }

        OSSClient ossClient = null;
        try{
            ossClient = createClient(targetFileInfo);

            if(!ossClient.doesBucketExist(targetFileInfo.getBucketName())){
                throw new StoreException("存储空间" + targetFileInfo.getBucketName() + "不存在");
            }
            // 上传OSS文件到本地
            ossClient.putObject(targetFileInfo.getBucketName(), targetFileInfo.getObjectName(), file);
        }catch (StoreException e){
            throw e;
        }catch (Exception e){
            e.printStackTrace();
            throw new StoreException("上传文件失败, bucketName = " + targetFileInfo.getBucketName() + ", key = " + targetFileInfo.getObjectName(), e);
        }finally {
            closeClient(ossClient);
        }
    }

    /**
     * 下载文件到指定路径
     * @param sourceFileInfo
     * @param localPath
     * @return
     */
    @Override
    public File doDownload(OSSFileInfo sourceFileInfo, String localPath) throws StoreException {
        valid(sourceFileInfo);

        OSSClient ossClient = null;
        try{
            ossClient = createClient(sourceFileInfo);

            if(!ossClient.doesBucketExist(sourceFileInfo.getBucketName())){
                throw new StoreException("存储空间" + sourceFileInfo.getBucketName() + "不存在");
            }
            // 下载OSS文件到本地。如果指定的本地文件存在会覆盖，不存在则新建。
            ossClient.getObject(new GetObjectRequest(sourceFileInfo.getBucketName(), sourceFileInfo.getObjectName()), new File(localPath));
            return new File(localPath + "/" + sourceFileInfo.getObjectName());
        }catch (StoreException e){
            throw e;
        }catch (Exception e){
            e.printStackTrace();
            throw new StoreException("下载文件失败, bucketName = " + sourceFileInfo.getBucketName() + ", key = " + sourceFileInfo.getObjectName(), e);
        }finally {
            closeClient(ossClient);
        }
    }

    protected void valid(OSSFileInfo fileInfo){

        Assert.hasLength(fileInfo.getBucketName(), "bucketName is required");
        Assert.hasLength(fileInfo.getObjectName(), "objectName is required");
    }

    protected OSSClient createClient(OSSFileInfo fileInfo){
        OSSStoreProperties ossProperties = fileInfo.getOssProperteis();

        return new OSSClient(ossProperties.getEndpoint(), ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
    }

    protected void closeClient(OSSClient ossClient){
        if(ossClient != null){
            try{
                ossClient.shutdown();
            }catch (Exception e){}
        }
    }
}
