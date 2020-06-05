package com.tsintergy.simple.algorithm.api;


import com.tsintergy.simple.algorithm.api.dto.InvokeRequestDTO;
import com.tsintergy.simple.algorithm.api.dto.InvokeResultDTO;

/**
 * @author angel
 * @description 调用服务
 * @date 2020/6/4 10:31
 */
public interface InvokeService {

    InvokeResultDTO doInvokeAlgorithm(InvokeRequestDTO invokeRequestDTO);

}
