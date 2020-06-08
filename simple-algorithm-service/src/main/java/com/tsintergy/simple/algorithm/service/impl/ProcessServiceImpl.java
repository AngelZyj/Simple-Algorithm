package com.tsintergy.simple.algorithm.service.impl;

import com.tsintergy.simple.algorithm.api.ProcessService;
import com.tsintergy.simple.algorithm.core.process.ConsoleProcess;
import com.tsintergy.simple.algorithm.core.process.ProcessArgs;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author angel
 * @description
 * @date 2020/6/8 11:00
 */
@Service("processService")
public class ProcessServiceImpl implements ProcessService {

    @Async("threadPoolTaskExecutor")
    @Override
    public void doOpenProcess(ProcessArgs args) {
        ConsoleProcess consoleProcess = new ConsoleProcess();
        try {
            consoleProcess.start(args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
