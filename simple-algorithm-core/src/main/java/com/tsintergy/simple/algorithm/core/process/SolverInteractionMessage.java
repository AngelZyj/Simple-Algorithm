package com.tsintergy.simple.algorithm.core.process;

/**
 * <p>
 *     算法交互信息，即算法计算过程的输出过程
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/6 11:19
 */
public class SolverInteractionMessage{

    /**
     * 信息时标 <br />
     * 格式：yyyy-MM-dd_hh:mm:ss
     */
    private String occurTime;

    /**
     * 信息时标微秒, 格式zzz
     */
    private String uSeconds;


    /**
     * 流程步骤
     * @see {@link com.tsintergy.simple.algorithm.api.pojo.SolverStepDO}
     */
    private int step;

    /**
     * 告警级别<br/>
     * 0.调试 <br/>
     * 1.正常 <br/>
     * 2.告警 <br/>
     * 3.错误
     */
    private int level;

    /**
     * 算法输出消息
     * 最长256个字符或128个汉字
     */
    private String message;

    public String getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public String getuSeconds() {
        return uSeconds;
    }

    public void setuSeconds(String uSeconds) {
        this.uSeconds = uSeconds;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
