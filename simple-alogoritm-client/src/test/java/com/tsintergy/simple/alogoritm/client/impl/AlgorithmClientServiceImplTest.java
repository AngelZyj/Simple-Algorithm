package com.tsintergy.simple.alogoritm.client.impl;

import com.tsintergy.simple.algorithm.api.dto.InvokeRequestDTO;
import com.tsintergy.simple.algorithm.api.dto.InvokeResultDTO;
import com.tsintergy.simple.alogoritm.client.SimpleAlogoritmClientApplication;
import com.tsintergy.simple.alogoritm.client.api.AlgorithmClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.json.JSON;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(classes = SimpleAlogoritmClientApplication.class)
class AlgorithmClientServiceImplTest {

    @Autowired
    private AlgorithmClient algorithmClient;

    @Test
    void doInvokeAlgorithm() {
        InvokeRequestDTO invokeRequestDTO = new InvokeRequestDTO();
        invokeRequestDTO.setCaseId("20200427084847289");
        invokeRequestDTO.setActionId(UUID.randomUUID().toString());
        invokeRequestDTO.setAlgorithmType("TOPO");
        invokeRequestDTO.setSolverType("CPLEX");
        invokeRequestDTO.setVersion("-1");
        invokeRequestDTO.setRemoteDir("/20200427084847289/TOPO_CPLEX/a7f6631a6a0d47a4b63b81bf5e64c31e");
        invokeRequestDTO.setRemoteFileName("/IN");
        invokeRequestDTO.setDebug(false);
        invokeRequestDTO.setModel(false);
        InvokeResultDTO invokeResultDTO = algorithmClient.doInvokeAlgorithm(invokeRequestDTO);
        log.info(invokeResultDTO.getActionStatus().getText());
    }

}