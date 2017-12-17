package com.heleeos.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.heleeos.blog.bean.Manager;
import com.heleeos.blog.service.ManagerService;
import com.heleeos.blog.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {

    @Autowired
    private ManagerService managerService;

    /**
     * 管理端首页. 
     */
    @RequestMapping(value = {"/", "index.html"})
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
        String token = SessionUtil.getTokenFromCookie(request);
        if(token == null) {
            Manager manager = SessionUtil.getManagerFromSession(request);
            SessionUtil.saveTokenToCookie(response, manager.getLoginToken());
        }

        return new ModelAndView("main/index");
    }

    /**
     * 管理端登陆界面.
     */
    @RequestMapping(value = "login.html")
    public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response) {
        Manager manager = SessionUtil.getManagerFromSession(request);
        if(manager != null) {
            return toIndex(request, response);
        }

        String token = SessionUtil.getTokenFromCookie(request);
        manager = managerService.getManagerByToken(token);
        if(manager != null) {
            SessionUtil.saveManagerToSession(request, manager);
            return toIndex(request, response);
        }
        return new ModelAndView("main/login");
    }
    
    /**
     * 管理员个人设置页面. 
     */
    @RequestMapping(value = "profile.html")
    public ModelAndView toProfile(){
        return new ModelAndView("main/profile");
    }

    @RequestMapping(value = "info.html")
    public ModelAndView toSystemInfo() {
        return new ModelAndView("main/info");
    }
}
