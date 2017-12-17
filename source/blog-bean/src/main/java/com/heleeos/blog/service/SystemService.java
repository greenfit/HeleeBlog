package com.heleeos.blog.service;

import com.heleeos.blog.util.TextFormatUtil;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * 版本相关的内容
 * Created by liyu on 2017/10/13.
 */
@Service
public class SystemService {

    /**
     * 获取当前项目版本
     */
    public String getBeanVersion() {
        return "1.0.1";
    }

    /**
     *
     */
    public Map<String, Object> getSystemInfo() {
        Map<String, Object> systemMap = new HashMap<>();

        Runtime runtime = Runtime.getRuntime();
        systemMap.put("availableProcessors", runtime.availableProcessors());
        systemMap.put("totalMemory", TextFormatUtil.formatMemory(runtime.totalMemory()));
        systemMap.put("freeMemory", TextFormatUtil.formatMemory(runtime.freeMemory()));
        systemMap.put("maxMemory", TextFormatUtil.formatMemory(runtime.maxMemory()));

        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        systemMap.put("totalPhysicalMemory", TextFormatUtil.formatMemory(operatingSystemMXBean.getTotalPhysicalMemorySize()));
        systemMap.put("freePhysicalMemory", TextFormatUtil.formatMemory(operatingSystemMXBean.getFreePhysicalMemorySize()));
        return systemMap;
    }


}
