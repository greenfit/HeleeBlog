package com.heleeos.blog.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 初始化监听器
 * Created by liyu on 15/12/2017.
 */
@Component
public class InitListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {
            logger.info("启动 blog-web 成功");
        }
    }
}
