package com.tsintergy.simple.algorithm.api.dto;


import com.tsintergy.simple.algorithm.core.dto.BaseResultDTO;
import com.tsintergy.simple.algorithm.core.enums.ActionStatusEnum;

/**
 * @author angel
 * @description 启动算法是否成功
 * @date 2020/6/4 14:59
 */
public class InvokeResultDTO extends BaseResultDTO {

    /**
     * 执行情况
     */
    ActionStatusEnum actionStatus;
}
