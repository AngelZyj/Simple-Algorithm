package com.tsintergy.simple.algorithm.core.enums;

/**
 * @author angel
 * @description 执行状态
 * @date 2020/6/5 9:49
 */
public enum ActionStatusEnum {

    /**
     * 指令执行成功
     */
    SUCCESS(0, "指令执行成功"),

    /**
     * 无对应算法程序
     */
    NO_SOLVER(1,"无对应算法程序");

    private final int id;
    private final String text;

    ActionStatusEnum(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
