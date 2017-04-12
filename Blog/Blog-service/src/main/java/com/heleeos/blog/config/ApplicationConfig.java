package com.heleeos.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

/**
 * 应用程序的所有设置项
 */
@Component
@ConfigurationProperties(prefix="config")
@ImportResource(locations={"classpath:database.xml"})
public class ApplicationConfig {
    
    private String imageHost;
    
    public String getImageHost() {
        return imageHost;
    }
    
    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
