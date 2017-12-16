package com.heleeos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.heleeos.blog.bean.Result;
import com.heleeos.blog.service.BlogTypeService;

@RestController
public class BlogTypeController {
    
    @Autowired
    private BlogTypeService blogTypeService;
    
    @RequestMapping(value = "blog/type.html")
    public ModelAndView toBlogType() {
        return new ModelAndView("blog/type");
    }
    
    @RequestMapping(value = "blog/type.json")
    public Result getBlogType() {
        Result result = new Result();
        result.putBean(blogTypeService.getList(false));
        return result;
    }
}
