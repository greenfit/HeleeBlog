package com.heleeos.blog.test.service;

import com.heleeos.blog.test.TestConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.heleeos.blog.bean.Manager;
import com.heleeos.blog.common.ManagerState;
import com.heleeos.blog.service.ManagerService;

import java.util.Date;

public class TestManagerService extends TestConfig {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ManagerService managerService;
    
    @Test
    public void save() {
        Manager manager = new Manager();
        manager.setId(2);
        manager.setUserName("admin");
        manager.setPassWord(DigestUtils.md5DigestAsHex("li123456".getBytes()));
        manager.setNickName("测试帐号");
        manager.setRealName("测试帐号");
        manager.setLoginTime(new Date());
        manager.setManagerState(ManagerState.FORBIDDEN.getState());
        toLogger(logger, managerService.save(manager));
    }
    
    @Test
    public void login() {
        toLogger(logger, managerService.get("admin", DigestUtils.md5DigestAsHex("li123456".getBytes())));
    }
}