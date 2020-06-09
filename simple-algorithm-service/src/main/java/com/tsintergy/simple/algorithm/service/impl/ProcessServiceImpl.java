package com.tsintergy.simple.algorithm.service.impl;

import com.tsintergy.simple.algorithm.api.ProcessLogService;
import com.tsintergy.simple.algorithm.api.ProcessService;
import com.tsintergy.simple.algorithm.api.dto.InvokeRequestDTO;
import com.tsintergy.simple.algorithm.api.pojo.SolverDO;
import com.tsintergy.simple.algorithm.core.process.ConsoleEvent;
import com.tsintergy.simple.algorithm.core.process.ConsoleListener;
import com.tsintergy.simple.algorithm.core.process.ConsoleProcess;
import com.tsintergy.simple.algorithm.core.process.ProcessArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author angel
 * @description
 * @date 2020/6/8 11:00
 */
@Service("processService")
public class ProcessServiceImpl implements ProcessService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);

    @Autowired
    private ProcessLogService processLogService;

    @Autowired(required = false)
    private ConsoleListener consoleListener;

    @Async("threadPoolTaskExecutor")
    @Override
    public void doOpenProcess(ProcessArgs args, InvokeRequestDTO invokeRequestDTO, SolverDO solverDO) {
        ConsoleProcess consoleProcess = new ConsoleProcess();
        consoleProcess.setConsoleListener(consoleListener);
        if (Objects.isNull(consoleListener)) {
            consoleProcess.setConsoleListener(defaultConsoleListener(invokeRequestDTO,solverDO));
        }
        try {
            consoleProcess.start(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConsoleListener defaultConsoleListener(InvokeRequestDTO invokeRequestDTO,SolverDO solverDO) {
        logger.info("use simpleAlgorithm service consoleListener!");
        return event -> processLogService.doSaveSolverLog(
                invokeRequestDTO.getActionId(),
                invokeRequestDTO.getCaseId(),
                solverDO.getId(),
                event.getLine()
        );
    }
}
