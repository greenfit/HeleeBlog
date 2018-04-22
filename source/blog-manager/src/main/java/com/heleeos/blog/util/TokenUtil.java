package com.heleeos.blog.util;

import com.heleeos.blog.dto.Manager;
import org.springframework.util.DigestUtils;

/**
 * 令牌相关的工具
 * Created with Li Yu on 2017/12/17.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public class TokenUtil {

    public static String createToken(Manager manager) {
        String temp = manager.hashCode() + System.currentTimeMillis() + Math.random() + "md5";
        return DigestUtils.md5DigestAsHex(temp.getBytes()).toUpperCase();
    }
}
