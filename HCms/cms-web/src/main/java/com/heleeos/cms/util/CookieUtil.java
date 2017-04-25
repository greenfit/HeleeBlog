package com.heleeos.cms.util;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.heleeos.cms.bean.Manager;
import com.heleeos.cms.service.RedisService;

@Component
public class CookieUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);
    
    private static final String COOKIE_MANAGER_KEY = "HCMS_COOKIE_MANAGER_KEY_";
    
    private static final String REDIS_MANAGER_KEY = "HCMS_REDIS_MANAGER_KEY_";
    
    private static RedisService redisService;
    
    @Autowired
    public void serRedisService(RedisService redisService) {
        CookieUtil.redisService = redisService;
    }

    /**
     * 通过Cookie获取管理者信息.
     */
    public static Manager getManager(HttpServletRequest request) {
        Cookie cookie = getCookie(request, COOKIE_MANAGER_KEY);
        if(cookie == null) {
            return null;
        } else {
            return redisService.get(cookie.getValue(), Manager.class);
        }
    }
    
    /**
     * 把管理者信息保存到Cookie中.
     */
    public static void saveManager(HttpServletResponse response, Manager manager) {
        String redisKey = REDIS_MANAGER_KEY + UUID.randomUUID();
        redisService.put(redisKey, manager);
        
        Cookie cookie = new Cookie(COOKIE_MANAGER_KEY, redisKey);
        response.addCookie(cookie);
    }
    
    /**
     * 清空Cookie中的管理者信息.
     */
    public static void clearManager(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request, COOKIE_MANAGER_KEY);
        if(cookie != null) {
            redisService.clear(cookie.getValue());
            clearCookie(response, COOKIE_MANAGER_KEY);
        }
    }
    
    /**
     * 根据Name获取Cookie.
     * 
     * @param name Cookie的名称
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        logger.debug("get '{}' from cookie", name);
        if(StringUtils.trimToNull(name) == null) return null;
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        for(Cookie cookie : cookies) {
            if(name.equals(cookie.getName())) return cookie;
        }
        return null;
    }
    
    /**
     * 清空指定的Cookie内容.
     */
    public static void clearCookie(HttpServletResponse response, String key) {
        Cookie cookie = new Cookie(key, "");
        response.addCookie(cookie);
    }
}
