package com.tsintergy.simple.algorithm.core.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 实现异步按行读取算法进程的控制台输出，读到行后触发行回调事件
 *
 * @author angel
 */
public class AsyncInputStreamReader {

    private final static Logger logger = LoggerFactory.getLogger(AsyncInputStreamReader.class);

    private final ConsoleProcess consoleProcess;
    private InputStream inputStream;
    private ConsoleListener listener;
    private String encoding = "GBK";

    public AsyncInputStreamReader(ConsoleProcess consoleProcess, InputStream inputStream,ConsoleListener listener) {
        this.consoleProcess = consoleProcess;
        this.inputStream = inputStream;
        this.listener = listener;

    }

    /**
     * 启动新线程异步读取输入流
     */
    public void read() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream, encoding));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        //进程的输入流必须及时读取，否则会阻塞进程导致假死
                        logger.info("{}算法控制台输出信息：{}",LocalDateTime.now(),line);
                        if (Objects.nonNull(listener)) {
                            listener.onReadLine(ConsoleEvent.createEvent(consoleProcess, line));
                        }
                    }
                    if (Objects.nonNull(listener)) {
                        listener.onComplete();
                    }
                } catch (Exception e) {
                    logger.error("读取标准输出出错", e);
                    if (Objects.nonNull(listener)) {
                        listener.onError("读取标准输出流出错", e);
                    }
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Exception e) {
                            logger.error("标准输出流关闭失败", e);
                        }
                    }
                }
            }
        }).start();
    }

}