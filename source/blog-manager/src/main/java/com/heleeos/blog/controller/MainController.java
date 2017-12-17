package com.heleeos.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.heleeos.blog.common.SessionKey;

@RestController
public class MainController {

    /**
     * 管理端首页. 
     */
    @RequestMapping(value = {"/", "index.html"})
    public ModelAndView toIndex() {
        return new ModelAndView("main/index");
    }

    /**
     * 管理端登陆界面.
     */
    @RequestMapping(value = "login.html")
    public ModelAndView toLogin(HttpServletRequest request){
        if(request.getSession().getAttribute(SessionKey.SESSION_MANAGER_KEY) != null) {
            return toIndex();
        } else {
            return new ModelAndView("main/login");
        }
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
