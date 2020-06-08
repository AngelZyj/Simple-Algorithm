package com.tsintergy.simple.algorithm.core.process;

import com.tsintergy.tsdl.core.algorithm.invoker.AlgorithmInvokerException;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/6 12:20
 */
public interface Args {
    /**
     * 获取参数
     * @return
     * @throws AlgorithmInvokerException
     */
    String getArgsString();
}
