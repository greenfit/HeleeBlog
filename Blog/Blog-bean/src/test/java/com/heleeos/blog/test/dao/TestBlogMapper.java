package com.heleeos.blog.test.dao;

import com.heleeos.blog.dao.BlogMapper;
import com.heleeos.blog.test.TestConfig;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by liyu on 2017/10/13.
 */
public class TestBlogMapper extends TestConfig {

    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void getCount() {
        if(blogMapper == null) {
            logger.error("数据库连接失败！");
            return;
        }
        try {
            int count = blogMapper.getCount(null, null, null);
            logger.info("Blog count :" + count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
