package com.tsintergy.simple.algorithm.service.impl;

import com.tsintergy.simple.algorithm.api.ProcessLogService;
import com.tsintergy.simple.algorithm.api.ProcessResultService;
import com.tsintergy.simple.algorithm.api.ProcessService;
import com.tsintergy.simple.algorithm.api.dto.InvokeRequestDTO;
import com.tsintergy.simple.algorithm.api.pojo.SolverDO;
import com.tsintergy.simple.algorithm.core.exception.StoreException;
import com.tsintergy.simple.algorithm.core.process.ConsoleProcess;
import com.tsintergy.simple.algorithm.core.process.ProcessArgs;
import com.tsintergy.simple.algorithm.core.process.listener.ConsoleListener;
import com.tsintergy.simple.algorithm.core.process.listener.ConsoleProcessListener;
import com.tsintergy.simple.algorithm.core.properties.AlgorithmProperties;
import com.tsintergy.simple.algorithm.core.properties.StoreProperties;
import com.tsintergy.simple.algorithm.core.store.StoreManagerFactory;
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

    @Autowired
    private ProcessResultService processResultService;

    @Autowired(required = false)
    private ConsoleListener consoleListener;

    @Autowired(required = false)
    private ConsoleProcessListener consoleProcessListener;

    @Autowired
    private AlgorithmProperties algorithmProperties;

    @Async("threadPoolTaskExecutor")
    @Override
    public void doOpenProcess(ProcessArgs args, InvokeRequestDTO invokeRequestDTO, SolverDO solverDO) {
        //开启调用算法进程
        ConsoleProcess consoleProcess = new ConsoleProcess();
        consoleProcess.setConsoleListener(consoleListener);
        consoleProcess.setConsoleProcessListener(consoleProcessListener);
        if (Objects.isNull(consoleListener)) {
            consoleProcess.setConsoleListener(defaultConsoleListener(invokeRequestDTO, solverDO));
        }
        if (Objects.isNull(consoleProcessListener)) {
            consoleProcess.setConsoleProcessListener(defaultConsoleProcessListener());
        }
        try {
            consoleProcess.start(args);
        } catch (Exception e) {
            logger.error("调用算法进程失败", e);
        }

    }

    public ConsoleListener defaultConsoleListener(InvokeRequestDTO invokeRequestDTO, SolverDO solverDO) {
        logger.info("use simpleAlgorithm service default consoleListener!");
        return event -> processLogService.doSaveSolverLog(
                invokeRequestDTO.getActionId(),
                invokeRequestDTO.getCaseId(),
                solverDO.getId(),
                event.getLine()
        );
    }

    public ConsoleProcessListener defaultConsoleProcessListener() {
        logger.info("use simpleAlgorithm service default consoleProcessListener!");
        ConsoleProcessListener consoleProcessListener = new ConsoleProcessListener() {

            @Override
            public void onShutdown() {
                logger.info("算法控制台正常关闭");
            }

            @Override
            public void onStop() {

            }

            @Override
            public void onError(String msg, Exception e) {

            }
        };
        return consoleProcessListener;
    }

    private void upload(StoreProperties storeProperties,ProcessArgs args) throws StoreException {
        StoreManagerFactory.createStoreManager(storeProperties.getStoreType())
                .download(
                        StoreManagerFactory.createBaseFileInfo(storeProperties.toStoreProperties(),"",""),
                        args.getOutput()
                );

    }
}
