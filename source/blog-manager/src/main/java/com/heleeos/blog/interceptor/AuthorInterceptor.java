package com.heleeos.blog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heleeos.blog.bean.Manager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.heleeos.blog.common.SessionKey;
/**
 * 登陆过滤器.
 * 
 * @author liyu
 */
public class AuthorInterceptor extends HandlerInterceptorAdapter {

    @Value("#{configProperties.image_host}")
    private String imageHost;
    
    /* 检测是否登陆,没有登录的跳转到登陆页面 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute(SessionKey.SESSION_MANAGER_KEY);
        if (obj == null || !(obj instanceof Manager)) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        if(modelAndView != null) {
            Object admin = request.getSession().getAttribute(SessionKey.SESSION_MANAGER_KEY);
            modelAndView.addObject("admin", admin);
            modelAndView.addObject("imageHost", imageHost);
        }
    }
}
