package com.tsintergy.simple.alogoritm.client.impl;

import com.tsintergy.simple.algorithm.api.InvokeService;
import com.tsintergy.simple.algorithm.api.dto.InvokeRequestDTO;
import com.tsintergy.simple.algorithm.api.dto.InvokeResultDTO;
import com.tsintergy.simple.alogoritm.client.api.AlgorithmClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.UUID;

/**
 * @author angel
 * @description
 * @date 2020/6/9 18:26
 */
public class AlgorithmClientServiceImpl implements AlgorithmClient {

    @Autowired
    private InvokeService invokeService;

    @Override
    public InvokeResultDTO doInvokeAlgorithm(InvokeRequestDTO invokeRequestDTO) {
        Assert.notNull(invokeRequestDTO.getCaseId(), "invokeRequestDTO's caseId can't be null !");
        Assert.notNull(invokeRequestDTO.getAlgorithmType(), "invokeRequestDTO's algorithmType can't be null !");
        Assert.notNull(invokeRequestDTO.getRemoteDir(), "invokeRequestDTO's remoteDir can't be null !");
        Assert.notNull(invokeRequestDTO.getRemoteFileName(), "invokeRequestDTO's remoteFileName can't be null !");
        if (Objects.isNull(invokeRequestDTO.getActionId())) {
            invokeRequestDTO.setActionId(UUID.randomUUID().toString());
        }
        if (Objects.isNull(invokeRequestDTO.getSolverType())) {
            invokeRequestDTO.setSolverType("CPLEX");
        }
        if (Objects.isNull(invokeRequestDTO.getVersion())) {
            invokeRequestDTO.setSolverType("-1");
        }
        if (Objects.isNull(invokeRequestDTO.getDebug())) {
            invokeRequestDTO.setDebug(true);
        }
        if (Objects.isNull(invokeRequestDTO.getModel())) {
            invokeRequestDTO.setModel(true);
        }
        return invokeService.doInvokeAlgorithm(invokeRequestDTO);
    }

}
