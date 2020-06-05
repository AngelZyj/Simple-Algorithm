package com.tsintergy.simple.algorithm.core.dto;

import java.io.Serializable;

/**
 * @author angel
 * @description
 * @date 2020/6/5 15:19
 */
public class BaseRequestDTO implements Serializable {

    /**
     * 指定id
     */
    private String actionId;

    /**
     * 案例id
     */
    private String caseId;

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}
