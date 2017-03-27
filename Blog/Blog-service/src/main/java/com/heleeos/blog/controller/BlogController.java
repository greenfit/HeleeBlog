package com.heleeos.blog.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.heleeos.blog.bean.Blog;
import com.heleeos.blog.bean.Manager;
import com.heleeos.blog.bean.Result;
import com.heleeos.blog.constant.ContentType;
import com.heleeos.blog.constant.SessionKey;
import com.heleeos.blog.service.BlogService;
import com.heleeos.blog.service.BlogTypeService;

@RestController
@RequestMapping(value = "cms/blog")
public class BlogController {
    
    @Autowired
    private BlogService blogService;
    
    @Autowired
    private BlogTypeService blogTypeService;

    @RequestMapping(value = "list.html")
    public ModelAndView toBlogList() {
        ModelAndView modelAndView = new ModelAndView("blog/list");
        return modelAndView;
    }
    
    @RequestMapping(value = "list.json")
    public Result getBlogType(HttpServletRequest request) {
        Result result = new Result();
        
        int page = NumberUtils.toInt(request.getParameter("page"), 1);
        int rows = NumberUtils.toInt(request.getParameter("rows"), 10);   
        int type = NumberUtils.toInt(request.getParameter("type"), 0);
        String tags = request.getParameter("tags");
        
        List<Blog> beans = blogService.gets(type, tags, page, rows);
        int count        = blogService.getCount(type, tags);
        
        result.putMessage("beans", beans);
        result.putMessage("page", page);
        result.putMessage("count", count);
        return result;
    }
    
    @RequestMapping(value = "add.html")
    public ModelAndView toAdd(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("blog/add");
        int id = NumberUtils.toInt(request.getParameter("id"), 0);
        if(id != 0) {
            modelAndView.addObject("bean", blogService.get(id));
        }
        modelAndView.addObject("types", blogTypeService.gets());
        return modelAndView;
    }
    
    @RequestMapping(value = "update.json")
    public Result update(HttpServletRequest request) {
        Result result = new Result();
        
        Blog blog;
        int id = NumberUtils.toInt(request.getParameter("id"), -1);
        if(id == -1){
            blog = new Blog();
        }else{
            blog = blogService.get(id);
            if(blog == null) blog = new Blog();
        }
        
        String title = request.getParameter("title");
        String type = request.getParameter("type");
        String disp = request.getParameter("disp");
        String tags = request.getParameter("tags");
        String summary = request.getParameter("summary");
        String content = request.getParameter("content");
        int contentType = NumberUtils.toInt(request.getParameter("contentType"), 0);
        
        if(StringUtils.trimToNull(title) == null){
            result.setCode(400);
            result.putInfo("标题不能为空!");
            return result;
        }
        
        if(StringUtils.trimToNull(type) == null){
            result.setCode(400);
            result.putInfo("类型不能为空!");
            return result;
        }
        
        if(StringUtils.trimToNull(disp) == null){
            result.setCode(400);
            result.putInfo("显示URL不能为空!");
            return result;
        }
        
        if(StringUtils.trimToNull(summary) == null){
            result.setCode(400);
            result.putInfo("摘要不能为空!");
            return result;
        }
        
        if(StringUtils.trimToNull(content) == null){
            result.setCode(400);
            result.putInfo("内容不能为空!");
            return result;
        }
                
        blog.setTitle(title);
        blog.setType(type);
        blog.setDisp(disp);
        blog.setTags(tags);
        blog.setSummary(summary);
        blog.setContentType(ContentType.of(contentType).getType());
        blog.setContent(content);
        blog.setLasttime(new Date());
        
        Manager manager = (Manager) request.getSession().getAttribute(SessionKey.SESSION_MANAGER_KEY);
        blog.setManagerid(manager.getId());
        
        boolean bol = blogService.save(blog);
        if(bol){
            result.setCode(200);
            result.putInfo("");
        }else{
            result.setCode(400);
            result.putInfo("提交失败");
        }
        return result;
    }
}
