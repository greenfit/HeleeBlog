package com.heleeos.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.heleeos.blog.service.BlogTypeService;

public class RightInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private BlogTypeService blogTypeService;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("types", blogTypeService.getCount());
        super.postHandle(request, response, handler, modelAndView);
    }
}
