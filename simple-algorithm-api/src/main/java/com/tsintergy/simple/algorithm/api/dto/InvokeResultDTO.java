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

    public InvokeResultDTO(String caseId, String actionId,ActionStatusEnum actionStatus) {
        super(caseId,actionId);
        this.actionStatus = actionStatus;
    }

    public ActionStatusEnum getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(ActionStatusEnum actionStatus) {
        this.actionStatus = actionStatus;
    }

    public void convertFail(ActionStatusEnum actionStatus) {
        super.convertFail();
        setActionStatus(actionStatus);
        setMessage(actionStatus.getText());
    }
}
