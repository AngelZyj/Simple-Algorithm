package com.tsintergy.simple.algorithm.core.store;

import java.io.Serializable;

/**
 * <p>
 * </p>
 *
 * @author longyz@tsintergy.com
 * @date 2019/6/5 13:09
 */
public class BaseFileInfo implements Serializable {

    private String md5;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
