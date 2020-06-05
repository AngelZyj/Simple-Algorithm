package com.tsintergy.simple.algorithm.api.dto;

import com.tsintergy.simple.algorithm.core.dto.BaseResultDTO;

/**
 * @author angel
 * @description
 * @date 2020/6/5 15:13
 */
public class UploadResultDTO extends BaseResultDTO {

    /**
     * 存储路径
     */
    private String dir;

    /**
     * 存储文件名
     */
    private String fileName;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
