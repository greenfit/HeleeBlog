package com.heleeos.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.heleeos.blog.bean.Result;
import com.heleeos.blog.service.BlogTypeService;

@RestController
@RequestMapping(value = "cms/blog")
public class BlogTypeController {
    
    @Autowired
    private BlogTypeService blogTypeService;
    
    @RequestMapping(value = "type.html")
    public ModelAndView toBlogType() {
        ModelAndView modelAndView = new ModelAndView("blog/type");
        return modelAndView;
    }
    
    @RequestMapping(value = "type.json")
    public Result getBlogType(HttpServletRequest request) {
        Result result = new Result();
        result.putBean(blogTypeService.gets());
        return result;
    }
    
    @RequestMapping(value = "typeCount.json")
    public Result getBlogTypeCount(HttpServletRequest request) {
        Result result = new Result();
        result.putBean(blogTypeService.getCount());
        return result;
    }
}
