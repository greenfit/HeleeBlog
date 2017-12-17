package com.heleeos.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("blog-type")
public class BlogTypeController {

    @RequestMapping(value = "list.html")
    public ModelAndView toBlogType() {
        return new ModelAndView("blogType/list");
    }
}
