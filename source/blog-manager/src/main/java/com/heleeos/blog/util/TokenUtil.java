package com.heleeos.blog.util;

import com.heleeos.blog.bean.Manager;
import org.springframework.util.DigestUtils;

/**
 *
 * Created by liyu on 17/12/2017.
 */
public class TokenUtil {

    public static String grentorToken(Manager manager) {
        String temp = manager.hashCode() + System.currentTimeMillis() + Math.random() + "md5";
        return DigestUtils.md5DigestAsHex(temp.getBytes()).toUpperCase();
    }
}
