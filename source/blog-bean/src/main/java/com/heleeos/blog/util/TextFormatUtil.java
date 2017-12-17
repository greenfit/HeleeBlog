package com.heleeos.blog.util;

/**
 * 格式化为文本内容的工具
 * Created by liyu on 17/12/2017.
 */
public class TextFormatUtil {

    private static final String[] MEMORY = {"B", "KB", "MB", "G", "T", "P"};

    private static final String[] TIME1 = {"ns", "ms", "s", "m", "h", "d", "y"};

    private static final long[]   TIME2 = {1_000_000, 1000, 60, 60, 24, 365, Integer.MAX_VALUE};

    /**
     * 格式化内存, 自动加上内存的单位.
     * @param memory 字节为单位
     */
    public static String formatMemory(long memory) {
        return formatMemory(memory, 0);
    }

    /**
     * 格式化时间, 自动加上时间单位
     * @param time 纳秒为单位的
     */
    public static String formatTime(long time) {
        return formatTime(time, 0);
    }

    /**
     * 格式化数字, 自动加上 %
     * @param number 数字
     */
    public static String formatDouble(double number) {
        return ((int)(number * 10000) / 100.0) + "%";
    }

    /**
     * 递归格式化当前的内存值
     * @param memory 内存
     * @param unitIndex 单位下标
     */
    private static String formatMemory(double memory, int unitIndex) {
        if(memory < 1024) {
            //保留2位小数
            memory = ((int)(memory * 100)) / 100.0;
            return memory + MEMORY[unitIndex];
        } else {
            return formatMemory(memory / 1024, unitIndex + 1);

        }
    }

    /**
     * 递归格式化时间
     * @param time 时间
     * @param timeIndex 时间下标
     */
    private static String formatTime(long time, int timeIndex) {
        if(time < TIME2[timeIndex]) {
            return time + TIME1[timeIndex];
        } else {
            String temp = time % TIME2[timeIndex] + TIME1[timeIndex];
            return formatTime(time / TIME2[timeIndex], timeIndex + 1) + " " + temp;
        }
    }
}
