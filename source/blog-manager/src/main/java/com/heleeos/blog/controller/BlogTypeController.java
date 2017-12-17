package com.heleeos.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 博客分类控制器
 * Created with Li Yu on 2017/12/17.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
@RestController
@RequestMapping("blog-type")
public class BlogTypeController {

    @RequestMapping(value = "list.html")
    public ModelAndView toBlogType() {
        return new ModelAndView("blogType/list");
    }
}
