package com.heleeos.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class MainController {
    
    /**
     * 首页
     */
    @RequestMapping(value = { "/" , "index.html" })
    public ModelAndView toIndex(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }
    
    @RequestMapping(value = "kiss.html")
    public ModelAndView toKiss(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("kiss");
        return modelAndView;
    }
}
