package com.heleeos.blog.service;

import org.springframework.stereotype.Service;

/**
 * 版本相关的内容
 * Created by liyu on 2017/10/13.
 */
@Service
public class VersionService {

    /**
     * 获取当前项目版本
     */
    public String getBeanVersion() {
        return "1.0.1";
    }
}
