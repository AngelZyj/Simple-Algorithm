package com.tsintergy.simple.algorithm.service.impl;

import com.tsintergy.simple.algorithm.api.ProcessResultService;
import com.tsintergy.simple.algorithm.api.pojo.SolverDO;

/**
 * @author angel
 * @description
 * @date 2020/6/12 16:00
 */
public class ProcessResultServiceImpl implements ProcessResultService {


    @Override
    public void doOnShutdown(String caseId, String actionId, SolverDO solverDO) {

    }
}
