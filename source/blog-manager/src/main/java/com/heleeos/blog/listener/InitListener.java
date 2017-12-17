package com.heleeos.blog.listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 容器监听器
 * Created with Li Yu on 2017/12/17.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
@Component
public class InitListener implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null) {
            logger.info("启动 blog-manager 成功");
        }
    }
}
