package com.heleeos.blog.ajax;

import com.heleeos.blog.bean.Blog;
import com.heleeos.blog.bean.Manager;
import com.heleeos.blog.bean.Result;
import com.heleeos.blog.common.BlogState;
import com.heleeos.blog.common.ContentType;
import com.heleeos.blog.common.ConstantKey;
import com.heleeos.blog.service.BlogService;
import com.heleeos.blog.service.BlogTypeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 文章相关的内容接口
 * Created by liyu on 2017/10/13.
 */
@RestController
@RequestMapping("ajax/blog/")
public class BlogInfoController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "list.json")
    public Result getBlogType(HttpServletRequest request) {
        Result result = new Result();

        int page = NumberUtils.toInt(request.getParameter("page"), 1);
        int rows = NumberUtils.toInt(request.getParameter("rows"), 10);
        int type = NumberUtils.toInt(request.getParameter("type"), 0);
        String tags = request.getParameter("tags");

        List<Blog> beans = blogService.getList(type, tags, null, page, rows);
        int count        = blogService.getCount(type, tags, null);

        result.putMessage("beans", beans);
        result.putMessage("page", page);
        result.putMessage("rows", rows);
        result.putMessage("count", count);
        return result;
    }

    @RequestMapping(value = "changeIndex.json")
    public Result changeIndex(HttpServletRequest request) {
        Result result = new Result();

        int id = NumberUtils.toInt(request.getParameter("id"), 0);
        int change = NumberUtils.toInt(request.getParameter("change"), 0);
        boolean flag = false;
        if(id > 0 && change != 0) {
            flag = blogService.changeIndex(id, (byte) change);
        }

        result.setCode(flag ? 200 : 400);
        result.putInfo(flag ? "" : "修改失败");
        return result;
    }

    @RequestMapping(value = "changeState.json")
    public Result changeState(HttpServletRequest request) {
        Result result = new Result();

        int id = NumberUtils.toInt(request.getParameter("id"), 0);
        String state = request.getParameter("state");

        boolean flag = false;
        if(id > 0) {
            flag = blogService.changeState(id, BlogState.of(state));
        }

        result.setCode(flag ? 200 : 400);
        result.putInfo(flag ? "" : "修改失败");
        return result;
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

        blog.setBlogTitle(title);
        blog.setBlogType(type);
        blog.setDispUrl(disp);
        blog.setBlogTags(tags);
        blog.setBlogSummary(summary);
        blog.setContentType(ContentType.of(contentType).getType());
        blog.setBlogContent(content);
        blog.setUpdateTime(new Date());

        Manager manager = (Manager) request.getSession().getAttribute(ConstantKey.SESSION_MANAGER_KEY);
        blog.setManagerId(manager.getId());

        boolean flag = blogService.save(blog);

        result.setCode(flag ? 200 : 400);
        result.putInfo(flag ? "" : "保存失败");
        return result;
    }
}
