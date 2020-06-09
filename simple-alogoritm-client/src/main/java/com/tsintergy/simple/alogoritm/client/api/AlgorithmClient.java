package com.tsintergy.simple.alogoritm.client.api;

import com.tsintergy.simple.algorithm.api.dto.InvokeRequestDTO;
import com.tsintergy.simple.algorithm.api.dto.InvokeResultDTO;

/**
 * @author angel
 * @description
 * @date 2020/6/9 18:24
 */
public interface AlgorithmClient {

    InvokeResultDTO doInvokeAlgorithm(InvokeRequestDTO invokeRequestDTO);

}
