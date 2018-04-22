package com.heleeos.blog.interceptor;

import com.heleeos.blog.dto.Manager;
import com.heleeos.blog.service.ManagerService;
import com.heleeos.blog.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限验证过滤器
 * Created with Li Yu on 2017/12/17.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public class AuthorInterceptor extends HandlerInterceptorAdapter {

    @Value("#{configProperties.image_host}")
    private String imageHost;

    @Autowired
    private ManagerService managerService;

    /**
     * 检测是否登陆, 没有登录的跳转到登陆页面
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Manager manager = SessionUtil.getManagerFromSession(request);
        if(manager == null) {
            String token = SessionUtil.getTokenFromCookie(request);
            manager = managerService.getManagerByToken(token);
            SessionUtil.saveManagerToSession(request, manager);
        }

        if(manager == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
        if(modelAndView != null) {
            modelAndView.addObject("admin", SessionUtil.getManagerFromSession(request));
            modelAndView.addObject("imageHost", imageHost);
        }
    }
}
