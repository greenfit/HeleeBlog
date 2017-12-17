package com.heleeos.blog.util;

/**
 * 格式化为文本内容的工具
 * Created by liyu on 17/12/2017.
 */
public class TextFormatUtil {

    private static final String[] UNITS = {"B", "KB", "MB", "G", "T", "P"};

    /**
     * 格式化内存, 自动加上内存的单位.
     * @param memory 内存
     */
    public static String formatMemory(long memory) {
        return formatMemory(memory, 0);
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
            return memory + UNITS[unitIndex];
        } else {
            return formatMemory(memory / 1024, unitIndex + 1);

        }
    }
}
