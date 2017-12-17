package com.heleeos.blog.ajax;

import com.heleeos.blog.bean.Result;
import com.heleeos.blog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章分类接口
 * Created with Li Yu on 2017/10/13.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
@RestController
@RequestMapping("ajax/blog-type/")
public class BlogTypeDataController {

    @Autowired
    private BlogTypeService blogTypeService;

    @RequestMapping(value = "list.json")
    public Result getBlogType() {
        Result result = new Result();
        result.putBeanList(blogTypeService.getList(false));
        return result;
    }
}
