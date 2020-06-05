package com.tsintergy.simple.algorithm.core.dto;

import java.io.Serializable;

/**
 * @author angel
 * @description 结果的基类
 * @date 2020/6/4 15:00
 */
public class BaseResultDTO implements Serializable {

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
}
