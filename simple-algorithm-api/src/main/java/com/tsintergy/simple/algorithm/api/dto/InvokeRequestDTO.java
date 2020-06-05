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
}
