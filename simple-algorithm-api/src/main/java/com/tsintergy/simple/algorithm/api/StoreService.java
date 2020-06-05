package com.tsintergy.simple.algorithm.api;


import com.tsintergy.simple.algorithm.api.dto.UploadRequestDTO;
import com.tsintergy.simple.algorithm.api.dto.UploadResultDTO;

/**
 * @author angel
 * @description
 * @date 2020/6/4 14:40
 */
public interface StoreService {

    /**
     * 上传文件
     * @param uploadRequestDTO 上传文件夹内容到文件存储服务
     * @return 上传结果
     */
    UploadResultDTO doUploadFile(UploadRequestDTO uploadRequestDTO);

}
