package com.tsintergy.simple.algorithm.core.process;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/5 14:22
 */
public class ConsoleEvent {

    private ConsoleProcess process;
    private String line;

    public static ConsoleEvent createEvent(ConsoleProcess process, String line){
        ConsoleEvent event = new ConsoleEvent();
        event.process = process;
        event.line = line;
        return event;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

}
