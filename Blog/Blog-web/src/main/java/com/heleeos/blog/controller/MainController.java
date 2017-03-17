package com.heleeos.blog.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.heleeos.blog.bean.Blog;
import com.heleeos.blog.bean.Result;
import com.heleeos.blog.service.BlogService;

@RestController
public class MainController {
    
    @Autowired
    private BlogService blogService;
    
    /**
     * 首页
     */
    @RequestMapping(value = { "/" , "index.html" })
    public ModelAndView toIndex() {
        return toBlogList("1");
    }
    
    @RequestMapping(value = "list/{pg}.html")
    public ModelAndView toBlogList(@PathVariable String pg) {
        ModelAndView modelAndView = new ModelAndView("blog/list");
        int page = NumberUtils.toInt(pg, 1);
        modelAndView.addObject("beans", blogService.gets(null, null, null, page, 3));
        return modelAndView;        
    }
    
    @RequestMapping(value = "{dispURL}.html")
    public ModelAndView toBlog(@PathVariable String dispURL) {
        ModelAndView modelAndView = new ModelAndView("blog/disp");
        //TODO 优化:加入缓存
        Blog blog = blogService.getByURL(dispURL);
        if(blog == null) {
            //TODO 修改为404
            return toIndex();
        } else {
            modelAndView.addObject("dispURL", dispURL);
            return modelAndView;
        }
    }
    
    @RequestMapping(value = "{dispURL}.json")
    public Result getBlog(@PathVariable String dispURL) {
        Result result = new Result();
        
        Blog blog = blogService.getByURL(dispURL);
        if(blog == null) {
            result.setCode(404);
            
        } else {
            result.setCode(200);
            result.putBean(blog);
        }
        return result;
    }
    
    @RequestMapping(value = "kiss.html")
    public ModelAndView toKiss(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("kiss");
        return modelAndView;
    }
}
