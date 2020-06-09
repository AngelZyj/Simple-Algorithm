package com.tsintergy.simple.algorithm.api;

/**
 * @author angel
 * @description
 * @date 2020/6/9 14:24
 */
public interface ProcessLogService {

    /**
     * 保存算法控制台日志
     * 只保存“#”号开头的json
     * @param actionId 指令id
     * @param caseId 案例id
     * @param solverId 求解器id
     * @param line 控制台输出内容
     */
    void doSaveSolverLog(String actionId,String caseId,String solverId,String line);

}
