package com.tsintergy.simple.algorithm.core.process;

import com.tsintergy.simple.algorithm.core.exception.ProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * <p>
 * 控制台进程, 实现了java控制台调用，并把控制台输出结果按行读取通知监听器 <br/>
 * <p>
 * 提供stop方法，支持基于OutputStream发送指令停止进程及杀死进程两种模式
 * </p>
 *
 * @author angel
 * @date 2019/6/6 13:00
 */
public class ConsoleProcess {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleProcess.class);

    public static final String STOP_COMMAND = "stop";

    private String stopCommand;

    Process process;

    /**
     * 如果控制台进程支持基于OutputStream获取指令的功能，则stopCommand为停止指令
     *
     * @param stopCommand
     */
    public ConsoleProcess(String stopCommand) {
        this.stopCommand = stopCommand;
    }

    public ConsoleProcess() {
        this(null);
    }

    /**
     * 启动进程
     *
     * @throws
     */
    public void start(Args args) throws Exception {
        AsyncInputStreamReader inputStreamReader;
        AsyncInputStreamReader errorInputStreamReader;

        String cmd = "";
        try {

            cmd = args.getArgsString();
            logger.info("执行命令： " + cmd);

//            logger.info("LD_LIBRARY_PATH: " + System.getenv("LD_LIBRARY_PATH"));

            process = Runtime.getRuntime().exec(cmd);

            //获取进程的标准输入流
            inputStreamReader = new AsyncInputStreamReader(this, process.getInputStream());

            //获取进程的错误输入流
            errorInputStreamReader = new AsyncInputStreamReader(this, process.getErrorStream());

            inputStreamReader.read();
            errorInputStreamReader.read();

            int result = process.waitFor();
            logger.info("进程中断值: {}", result);
            int value = process.exitValue();
            logger.info("进程退出值: {}", value);

            if (result != 0 || value != 0) {
                //控制台被强制关闭时value会不为0
                process.destroy();
                throw new ProcessException("进程未正常结束或异常中断, 退出值为:" + value);
            }
        } catch (IOException ex) {
            throw new ProcessException("无法启动进程:"+ cmd);
        } catch (Exception ex) {
            throw new ProcessException("进程出现异常", ex);
        } finally {
            closeProcess();
        }
    }

    /**
     * 停止进程
     */
    public void shutdown() throws ProcessException {
        try {
            if (StringUtils.isEmpty(stopCommand)) {
                process.destroy();
            } else {
                stopByCommand();
            }
        } catch (Exception ex) {
            throw new ProcessException("控制台停止失败: ", ex);
        }

    }

    public void stopByCommand() throws ProcessException {
        //3种办法，1. 使用输出流发送stop指令， 2. 强制杀死 3. 由agent发送远程调用杀死
        //存在问题，输入输出流同时使用时输入流存在无法接收
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            writer.write(stopCommand);
            writer.newLine();
            writer.flush(); //记得调用flush，否则缓冲区的内容不会发到进程
        } catch (Exception e) {
            throw new ProcessException("命令停止失败:", e);
        }
    }

    protected void closeProcess() {
        if (process != null) {
            safeCloseStream(process.getInputStream());
            safeCloseStream(process.getErrorStream());
            safeCloseStream(process.getOutputStream());
        }
    }

    /**
     * 安全关闭流
     *
     * @param closeable
     */
    private void safeCloseStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public String getStopCommand() {
        return stopCommand;
    }

    public void setStopCommand(String stopCommand) {
        this.stopCommand = stopCommand;
    }
}
