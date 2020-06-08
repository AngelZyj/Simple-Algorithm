package com.tsintergy.simple.algorithm.core.dto;

import java.io.Serializable;

/**
 * @author angel
 * @description 结果的基类
 * @date 2020/6/4 15:00
 */
public class BaseResultDTO implements Serializable {

    /**
     * 案例id
     */
    private String caseId;

    /**
     * 指令id
     */
    private String actionId;

    /**
     * 操作是否成功
     */
    private Boolean success;

    /**
     * 操作信息
     */
    private String message;

    public BaseResultDTO() {
        this.success = true;
        this.message = "success";
    }

    public BaseResultDTO(String caseId, String actionId) {
        this.caseId = caseId;
        this.actionId = actionId;
        this.success = true;
        this.message = "success";
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void convertFail() {
        setSuccess(false);
        setMessage("fail");
    }
}
