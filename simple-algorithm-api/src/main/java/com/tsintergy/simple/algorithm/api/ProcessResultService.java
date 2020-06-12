package com.tsintergy.simple.algorithm.api;

import com.tsintergy.simple.algorithm.api.pojo.SolverDO;

/**
 * @author angel
 * @description 控制台进程结果处理类
 * @date 2020/6/9 14:24
 */
public interface ProcessResultService {

    /**
     * 算法调用正常时时间
     * @param caseId
     * @param actionId
     * @param solverDO
     */
    void doOnShutdown(String caseId, String actionId, SolverDO solverDO);



}
