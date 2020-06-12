package com.tsintergy.simple.algorithm.core.process.listener;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/5 14:22
 */
public interface ConsoleListener {

    /**
     * 行事件
     * @param event
     */
    void onReadLine(ConsoleEvent event);

    /**
     * 控制台关闭
     */
    default void onComplete(){}

    /**
     * 控制台错误
     * @param msg
     * @param e
     */
    default void onError(String msg, Exception e){}
}
