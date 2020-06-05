package com.tsintergy.simple.algorithm.api.dto;


import com.tsintergy.simple.algorithm.core.dto.BaseRequestDTO;

/**
 * @author angel
 * @description 上传请求
 * @date 2020/6/5 15:18
 */
public class UploadRequestDTO extends BaseRequestDTO {

    /**
     * 算法类型
     */
    private String algorithmType;

    /**
     * 求解器类型
     */
    private String solverType;

    /**
     * 本地文件位置
     */
    private String localDir;


    public String getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
    }

    public String getSolverType() {
        return solverType;
    }

    public void setSolverType(String solverType) {
        this.solverType = solverType;
    }

    public String getLocalDir() {
        return localDir;
    }

    public void setLocalDir(String localDir) {
        this.localDir = localDir;
    }
}
