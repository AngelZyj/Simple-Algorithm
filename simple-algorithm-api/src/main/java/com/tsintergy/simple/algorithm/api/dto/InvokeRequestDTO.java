package com.tsintergy.simple.algorithm.api.dto;


import com.tsintergy.simple.algorithm.core.dto.BaseRequestDTO;

/**
 * @author angel
 * @description
 * @date 2020/6/5 16:43
 */
public class InvokeRequestDTO extends BaseRequestDTO {

    /**
     * 算法类型
     */
    private String algorithmType;

    /**
     * 求解器类型
     */
    private String solverType;

    /**
     * 版本类型
     */
    private String version;

    /**
     * 远程输入文件存储路径
     */
    private String remoteDir;

    /**
     * 远程输入文件名
     */
    private String remoteFileName;

    /**
     * 输出debug
     */
    private Boolean debug;

    /**
     * 输出模型
     */
    private Boolean model;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public String getRemoteFileName() {
        return remoteFileName;
    }

    public void setRemoteFileName(String remoteFileName) {
        this.remoteFileName = remoteFileName;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public Boolean getModel() {
        return model;
    }

    public void setModel(Boolean model) {
        this.model = model;
    }
}
