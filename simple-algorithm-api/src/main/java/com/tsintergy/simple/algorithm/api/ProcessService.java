package com.tsintergy.simple.algorithm.api;


import com.tsintergy.simple.algorithm.api.dto.InvokeRequestDTO;
import com.tsintergy.simple.algorithm.api.pojo.SolverDO;
import com.tsintergy.simple.algorithm.core.process.ProcessArgs;

/**
 * @author angel
 * @description 进程服务
 * @date 2020/6/4 10:30
 */
public interface ProcessService {

    /**
     * 开启算法进程
     * @param args 进程启动参数
     * @param invokeRequestDTO 本次请求参数
     * @param solverDO
     */
    void doOpenProcess(ProcessArgs args, InvokeRequestDTO invokeRequestDTO, SolverDO solverDO);
}
