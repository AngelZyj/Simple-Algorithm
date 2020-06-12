package com.tsintergy.simple.algorithm.core.process.listener;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/7 07:29
 */
public interface ConsoleProcessListener {

    /**
     * 控制台启动
     */
    default void onStart() {

    }

    /**
     * 控制台正常关闭
     */
    void onShutdown();

    /**
     * 手动关闭控制台，停止进程
     */
    void onStop();

    /**
     * 控制台异常
     * @param msg
     * @param e
     */
    void onError(String msg, Exception e);

}
