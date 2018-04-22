package com.heleeos.blog.controller;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heleeos.blog.service.BlogService;

/**
 * 给外部提供功能的控制器.
 */
@RestController
@RequestMapping(value = "api")
public class APIController {
    
    @Autowired
    private BlogService blogService;
    
    @Value("#{configProperties.static_refresh}")
    private String staticRefresh;
    
    /**
     * 新增博客文章阅读的次数.
     */
    @GetMapping(value = "blog/{url}.js")
    public String addBlogCount(HttpServletRequest request, HttpServletResponse response, @PathVariable String url) {
        if(StringUtils.trimToNull(url) == null) return "";
        String key = "BLOG_READ_TIME_" + url;
        Cookie cookie = getCookie(request, key);
        if(cookie != null) {
            long time = NumberUtils.toLong(cookie.getValue(), 0);
            if(System.currentTimeMillis() - time < 3600_000) {
                return "";
            }
        }
        if(blogService.addCountByUrl(url)) {
            cookie = new Cookie(key, System.currentTimeMillis() + "");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            refreshStatic(url);
        }
        return "";
    }
    
    /**
     * 获取cookie.
     * 
     * @param name cookie名字
     */
    private Cookie getCookie(HttpServletRequest request, String name) {
        if(request == null || StringUtils.trimToNull(name) == null) return null;
        try {
            return Arrays.stream(request.getCookies())
                         .filter(cookie -> cookie.getName().equals(name))
                         .findFirst()
                         .get();
        } catch(Exception e) {}
        return null;
    }
    
    /**
     * 刷新已经生成的静态文件.
     */
    private void refreshStatic(String url) {
        try {
            HttpClients.createDefault().execute(new HttpGet(staticRefresh + url + ".html"));
        } catch (Exception e) {}
    }
}
