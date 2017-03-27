package com.heleeos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heleeos.blog.bean.Result;
import com.heleeos.blog.service.BlogService;

/**
 * 给前端提供服务接口.
 */
@RestController
public class WebServiceController {
    
    @Autowired
    private BlogService blogService;
    
    @RequestMapping(value = "list.json")
    public Result getList() {
        Result result = new Result();
        return result;
    }

    @RequestMapping(value = "blog.json")
    public Result getBlog() {
        Result result = new Result();
        return result;
    }
}
