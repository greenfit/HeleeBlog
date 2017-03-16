package com.heleeos.blog.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.heleeos.blog.bean.Blog;
import com.heleeos.blog.bean.Result;
import com.heleeos.blog.service.BlogService;

@RestController
public class BlogController {
    
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "blog/list.html")
    public ModelAndView toBlogList() {
        ModelAndView modelAndView = new ModelAndView("blog/list");
        
        return modelAndView;
    }
    
    @RequestMapping(value = "blog/list.json")
    public Result getBlogType(HttpServletRequest request) {
        Result result = new Result();
        
        int managerId = NumberUtils.toInt(request.getParameter("managerId"), 0);
        int page = NumberUtils.toInt(request.getParameter("page"), 1);
        int rows = NumberUtils.toInt(request.getParameter("rows"), 10);   
        int type = NumberUtils.toInt(request.getParameter("type"), 0);
        String tags = request.getParameter("tags");
        
        List<Blog> beans = blogService.gets(type, tags, managerId, page, rows);
        int count        = blogService.getCount(type, tags, managerId);
        
        result.putMessage("beans", beans);
        result.putMessage("page", page);
        result.putMessage("count", count);
        return result;
    }
}
