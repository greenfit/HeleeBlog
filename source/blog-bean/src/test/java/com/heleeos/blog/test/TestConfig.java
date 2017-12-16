package com.heleeos.blog.test;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:application*.xml"})
public class TestConfig extends AbstractJUnit4SpringContextTests {

    private static Gson gson = new Gson();

    public static String toJSON(Object object) {
        return gson.toJson(object);
    }

    public static void toLogger(Logger logger, List<?> list) {
        list.forEach((item) -> toLogger(logger, item));
    }

    public static void toLogger(Logger logger, Object object) {
        logger.info(toJSON(object));
    }
}
