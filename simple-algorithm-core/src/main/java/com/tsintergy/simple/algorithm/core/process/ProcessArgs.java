package com.tsintergy.simple.algorithm.core.process;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * </p>
 *
 * @author angel
 * @date 2019/6/6 12:49
 */
public class ProcessArgs implements Args {

    public static final String INPUT_PREFIX = "/p:";
    public static final String OUTPUT_PREFIX = "/e:";
    public static final String SLAVE_PREFIX = "/s:";
    public static final String M_PREFIX = "/m+";
    public static final String Z_PREFIX = "/z+";

    /**
     * 求解器进程名称
     */
    private String solverProcessName;

    /**
     * 求解器进程进程所在目录
     */
    private String solverProcessDir;

    /**
     * 求解器从机端口号
     */
    private Integer solverPort;

    /**
     * 指定算法输入目录
     */
    private String input;

    /**
     * 指定算法输出目录
     */
    private String output;

    /**
     * 求解器网络信息
     */
    private List<String> solverIps;

    /**
     * 输出debug
     */
    private Boolean debug;

    /**
     * 输出模型
     */
    private Boolean model;


    public ProcessArgs() {
        solverIps = new ArrayList<>();
    }

    public ProcessArgs(String solverProcessDir, String solverProcessName, String input, String output, Boolean debug, Boolean model) {
        this.solverProcessDir = solverProcessDir;
        this.solverProcessName = solverProcessName;
        this.input = input;
        this.output = output;
        this.debug = debug;
        this.model = model;
    }

    /**
     * 获取参数
     *
     * @return
     * @throws
     */
    @Override
    public String getArgsString() {
        Assert.hasText(solverProcessName, "solverProcessName is required");
        Assert.hasText(input, "input is required");
        Assert.hasText(output, "output is required");
//        Assert.notEmpty(solverIps, "solverIps is required");

        StringBuilder sb = new StringBuilder();

//        dascuc /p:/home/tsintergy/caseid/in /e:/home/tsintergy/caseid/out /s:192.168.1.11:1000,192.168.1.12:1000

        if (!StringUtils.isEmpty(solverProcessDir)) {
            sb.append(solverProcessDir);
            if (solverProcessDir.charAt(solverProcessDir.length() - 1) != '/') {
                sb.append("/");
            }
        }
        sb.append(solverProcessName)
                .append(" ")
                .append(INPUT_PREFIX).append(input)
                .append(" ")
                .append(OUTPUT_PREFIX).append(output)
                .append(" ");
        if (Objects.equals(true, debug)) {
            sb.append(M_PREFIX);

        }
        if (Objects.equals(true, model)) {
            sb.append(" ").append(Z_PREFIX);
        }
        if (solverIps != null && solverIps.size() > 0) {
            sb.append(" ").append(SLAVE_PREFIX);
            for (int i = 0; i < solverIps.size(); i++) {
                sb.append(solverIps.get(i));
                if (solverPort != null) {
                    sb.append(":").append(solverPort);
                }
                if (i != solverIps.size() - 1) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    public String getSolverProcessName() {
        return solverProcessName;
    }

    public void setSolverProcessName(String solverProcessName) {
        this.solverProcessName = solverProcessName;
    }

    public Integer getSolverPort() {
        return solverPort;
    }

    public void setSolverPort(Integer solverPort) {
        this.solverPort = solverPort;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void addSolverIp(String ip) {
        solverIps.add(ip);
    }

    public List<String> getSolverIps() {
        return solverIps;
    }

    public void setSolverIps(List<String> solverIps) {
        this.solverIps = solverIps;
    }

    public String getSolverProcessDir() {
        return solverProcessDir;
    }

    public void setSolverProcessDir(String solverProcessDir) {
        this.solverProcessDir = solverProcessDir;
    }

    public Boolean getDebug() {
        return debug;
    }

    public void setDebug(Boolean debug) {
        this.debug = debug;
    }

    public Boolean getModel() {
        return model;
    }

    public void setModel(Boolean model) {
        this.model = model;
    }
}
