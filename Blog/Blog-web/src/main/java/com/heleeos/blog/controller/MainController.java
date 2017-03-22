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
        int rows = 5;
        int count = blogService.getCount(null, null, null);
        int max = count / rows + (count % rows == 0 ? 0 : 1);//余数不为0,要加一//最后一页
        int start = 1;//开始显示页码的页
        int end = max;
        
        //合法验证
        if(page > max) page = max;
        if(page < 1) page = 1;
        
        //显示当前页码的前后2个
        if(page - 2 > 1) start = page - 2;
        if(page + 2 < max) end = page + 2;
        
        if(page < 4) end = 5;
        if(page > max - 4) start = max - 4;
        if(start < 1) start = 1;
        if(end > max) end = max;
               
        modelAndView.addObject("beans", blogService.gets(null, null, null, page, rows));
        modelAndView.addObject("count", count);
        modelAndView.addObject("page", page);
        modelAndView.addObject("rows", rows);
        modelAndView.addObject("start", start);
        modelAndView.addObject("end", end);
        modelAndView.addObject("max", max);
        return modelAndView;        
    }
    
    @RequestMapping(value = "{dispURL}.html")
    public ModelAndView toBlog(@PathVariable String dispURL) {
        ModelAndView modelAndView = new ModelAndView("blog/disp");
        Blog blog = blogService.getByURL(dispURL);
        if(blog == null) {
            //TODO 修改为404
            return toIndex();
        } else {
            modelAndView.addObject("dispURL", dispURL);
            modelAndView.addObject("blog", blog);
            blogService.addCount(blog.getId());
            return modelAndView;
        }
    }
    
    @RequestMapping(value = "{dispURL}.json")
    public Result getBlog(@PathVariable String dispURL) {
        Result result = new Result();
        String content = blogService.getContentByURL(dispURL);
        if(content == null) {
            result.setCode(404);
            result.putInfo("文章不存在!");
        } else {
            result.setCode(200);
            result.putMessage("content", content);
        }
        return result;
    }
    
    @RequestMapping(value = "kiss.html")
    public ModelAndView toKiss(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("kiss");
        return modelAndView;
    }
}
