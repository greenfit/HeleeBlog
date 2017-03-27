package com.heleeos.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heleeos.blog.bean.Blog;
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
    public Result getList(HttpServletRequest request) {
        Result result = new Result();
        
        int page = NumberUtils.toInt(request.getParameter("page"), 1);
        int rows = NumberUtils.toInt(request.getParameter("rows"), 5);
        int type = NumberUtils.toInt(request.getParameter("type"), 0);
        String tags = request.getParameter("tags");
        
        int count = blogService.getCount(type, tags);
        int max = count / rows + (count % rows == 0 ? 0 : 1);//余数不为0,要加一
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

        result.setCode(200);
        result.putMessage("beans", blogService.gets(type, tags, page, rows));
        result.putMessage("count", count);
        result.putMessage("page", page);
        result.putMessage("rows", rows);
        result.putMessage("start", start);
        result.putMessage("end", end);
        result.putMessage("max", max);
        
        return result;
    }

    @RequestMapping(value = "blog.json")
    public Result getBlog(HttpServletRequest request) {
        Result result = new Result();
        
        String url = request.getParameter("url");
        if(url != null) {
            Blog blog = blogService.getByURL(url);
            result.setCode(200);
            result.putBean(blog);
        } else {
            result.setCode(404);
            result.putInfo("文章未找到");
        }        
        return result;
    }
}
