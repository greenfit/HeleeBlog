package com.heleeos.blog.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.heleeos.blog.service.BlogService;
import com.heleeos.blog.service.BlogTypeService;

@Controller
@RequestMapping("blog/")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogTypeService blogTypeService;

    @RequestMapping(value = "list.html")
    public ModelAndView toBlogList() {
        return new ModelAndView("blog/list");
    }

    @RequestMapping(value = "add.html")
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("blog/add");
        int id = NumberUtils.toInt(request.getParameter("id"), 0);
        if(id != 0) {
            //非0表示编辑功能
            modelAndView.addObject("bean", blogService.get(id));
        }
        modelAndView.addObject("types", blogTypeService.getList());
        return modelAndView;
    }
}
