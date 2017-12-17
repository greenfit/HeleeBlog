package com.heleeos.blog.bean;

import java.util.Map;

/**
 * 系统信息
 * Created by liyu on 17/12/2017.
 */
public class SystemInfo {

    private Map<String, Object> jvmInfo;

    private Map<String, Object> serverMemory;

    public Map<String, Object> getJvmInfo() {
        return jvmInfo;
    }

    public void setJvmInfo(Map<String, Object> jvmInfo) {
        this.jvmInfo = jvmInfo;
    }

    public Map<String, Object> getServerMemory() {
        return serverMemory;
    }

    public void setServerMemory(Map<String, Object> serverMemory) {
        this.serverMemory = serverMemory;
    }
}
