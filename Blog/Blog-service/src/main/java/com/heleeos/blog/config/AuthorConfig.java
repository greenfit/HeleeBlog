package com.heleeos.blog.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 访问权限的配置项
 * 
 * @author liyu
 */
@Configuration
public class AuthorConfig extends WebMvcConfigurerAdapter {
    
    /**
     * 添加拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorInterceptor())
                .addPathPatterns("/cms/*")
                .excludePathPatterns("/cms/login.html", "/cms/login.json", "/cms/image/code.jpg", "/cms/islogin.json");
    }
}

/**
 * 登陆过滤器.
 * 
 * @author liyu
 */
class AuthorInterceptor extends HandlerInterceptorAdapter {
    
    /* 检测是否登陆,没有登录的跳转到登陆页面 */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        Object obj = req.getSession().getAttribute("KEY_AUTHOR_SESSION");
        if (obj == null) {
            resp.sendRedirect(req.getContextPath() + "/login.html");
            return false;
        }
        return true;
    }
}
