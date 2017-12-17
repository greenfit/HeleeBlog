package com.heleeos.blog.test.service;

import com.heleeos.blog.service.SystemService;
import com.heleeos.blog.test.TestConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by liyu on 16/12/2017.
 */
public class TestSystemService extends TestConfig {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private SystemService systemService;

    @Test
    public void getBeanVersion() {
        toLogger(logger, systemService.getBeanVersion());
    }

    @Test
    public void getSystemInfo() {
        toLogger(logger, systemService.getSystemInfo());
    }
}
