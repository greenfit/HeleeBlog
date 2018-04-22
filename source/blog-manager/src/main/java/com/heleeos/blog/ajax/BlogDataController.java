package com.heleeos.blog.ajax;

import com.heleeos.blog.bean.PageInfo;
import com.heleeos.blog.dto.Blog;
import com.heleeos.blog.dto.Manager;
import com.heleeos.blog.bean.Result;
import com.heleeos.blog.common.BlogState;
import com.heleeos.blog.common.ContentType;
import com.heleeos.blog.common.ConstantKey;
import com.heleeos.blog.service.BlogService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 文章相关的接口
 * Created with Li Yu on 2017/10/13.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
@RestController
@RequestMapping("ajax/blog/")
public class BlogDataController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "list.json")
    public Result getBlogType(HttpServletRequest request) {
        int page = NumberUtils.toInt(request.getParameter("page"), 1);
        int rows = NumberUtils.toInt(request.getParameter("rows"), 10);
        int type = NumberUtils.toInt(request.getParameter("type"), 0);
        String tags = request.getParameter("tags");

        PageInfo<Blog> beans = blogService.getList(type, tags, null, page, rows);
        return Result.SUCCESS(beans);
    }

    @RequestMapping(value = "changeIndex.json")
    public Result changeIndex(HttpServletRequest request) {
        int id = NumberUtils.toInt(request.getParameter("id"), 0);
        int change = NumberUtils.toInt(request.getParameter("change"), 0);
        boolean flag = false;
        if(id > 0 && change != 0) {
            flag = blogService.changeIndex(id, (byte) change);
        }

        return Result.of(flag);
    }

    @RequestMapping(value = "changeState.json")
    public Result changeState(HttpServletRequest request) {
        int id = NumberUtils.toInt(request.getParameter("id"), 0);
        String state = request.getParameter("state");

        boolean flag = false;
        if(id > 0) {
            flag = blogService.changeState(id, BlogState.of(state));
        }

        return Result.of(flag);
    }

    @RequestMapping(value = "update.json")
    public Result update(HttpServletRequest request) {
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
        String url  = request.getParameter("url");
        String tags = request.getParameter("tags");
        String summary = request.getParameter("summary");
        String content = request.getParameter("content");
        int contentType = NumberUtils.toInt(request.getParameter("contentType"), 0);

        if(StringUtils.trimToNull(title) == null){
            return Result.PARAMETER_ERROR("标题不能为空");
        }

        if(StringUtils.trimToNull(type) == null){
            return Result.PARAMETER_ERROR("类型不能为空");
        }

        if(StringUtils.trimToNull(url) == null){
            return Result.PARAMETER_ERROR("显示URL不能为空");
        }

        if(StringUtils.trimToNull(summary) == null){
            return Result.PARAMETER_ERROR("摘要不能为空");
        }

        if(StringUtils.trimToNull(content) == null){
            return Result.PARAMETER_ERROR("内容不能为空");
        }

        blog.setBlogTitle(title);
        blog.setBlogType(type);
        blog.setDisplayURL(url);
        blog.setBlogTags(tags);
        blog.setBlogSummary(summary);
        blog.setContentType(ContentType.of(contentType).getType());
        blog.setBlogContent(content);
        blog.setUpdateTime(new Date());

        Manager manager = (Manager) request.getSession().getAttribute(ConstantKey.SESSION_MANAGER_KEY);
        blog.setManagerId(manager.getId());

        boolean flag = blogService.save(blog);

        return Result.of(flag);
    }
}
