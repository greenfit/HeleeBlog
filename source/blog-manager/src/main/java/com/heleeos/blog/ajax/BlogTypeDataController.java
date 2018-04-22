package com.heleeos.blog.ajax;

import com.heleeos.blog.dto.BlogType;
import com.heleeos.blog.bean.Result;
import com.heleeos.blog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Result<List<BlogType>> getBlogType() {
        return Result.SUCCESS(blogTypeService.getList(false));
    }
}
