package com.heleeos.cms.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.heleeos.cms.bean.Manager;
import com.heleeos.cms.constant.ManagerState;
import com.heleeos.cms.service.ManagerService;

public class TestManager extends TestConfig {

    @Autowired
    private ManagerService managerService;
    
    @Test
    public void save() {
        Manager manager = new Manager();
        manager.setId(2);
        manager.setUsername("admin");
        manager.setPassword(DigestUtils.md5DigestAsHex("li123456".getBytes()));
        manager.setNickname("测试帐号");
        manager.setRealname("测试帐号");
        manager.setState(ManagerState.NORMAL.getState());
        
        managerService.save(manager);
    }
    
    @Test
    public void login() {
        Manager manager = managerService.get("admin", DigestUtils.md5DigestAsHex("li123456".getBytes()));
        if(manager != null) {
            managerService.updateLoginTime(manager.getId());
            System.out.println("登陆用户为:" + manager);
        }
    }
}