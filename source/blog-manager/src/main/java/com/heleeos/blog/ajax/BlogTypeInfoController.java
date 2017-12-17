package com.heleeos.blog.ajax;

import com.heleeos.blog.bean.Result;
import com.heleeos.blog.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("ajax/blog-type/")
public class BlogTypeInfoController {

    @Autowired
    private BlogTypeService blogTypeService;

    @RequestMapping(value = "list.json")
    public Result getBlogType() {
        Result result = new Result();
        result.putBeanList(blogTypeService.getList(false));
        return result;
    }
}
