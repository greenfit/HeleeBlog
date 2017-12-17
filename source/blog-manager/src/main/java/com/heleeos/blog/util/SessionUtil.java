package com.heleeos.blog.util;

import com.heleeos.blog.bean.Manager;
import com.heleeos.blog.common.ConstantKey;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 会话相关的工具
 * Created with Li Yu on 2017/12/17.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public class SessionUtil {

    /**
     * 从当前服务器会话中获取管理员
     */
    public static Manager getManagerFromSession(HttpServletRequest request) {
        Object obj = request.getSession().getAttribute(ConstantKey.SESSION_MANAGER_KEY);
        if(obj != null && obj instanceof Manager) {
            return (Manager) obj;
        }
        return null;
    }

    /**
     * 保存管理员到当前服务器会话中
     * @param manager 管理员
     */
    public static void saveManagerToSession(HttpServletRequest request, Manager manager) {
        request.getSession().setAttribute(ConstantKey.SESSION_MANAGER_KEY, manager);
    }

    /**
     * 从当前会话中移除管理员信息
     */
    public static void removeSessionManager(HttpServletRequest request) {
        request.getSession().removeAttribute(ConstantKey.SESSION_MANAGER_KEY);
    }

    /**
     * 从当前会话中获取验证码
     */
    public static String getCaptchaFromSession(HttpServletRequest request) {
        return request.getSession().getAttribute(ConstantKey.SESSION_CAPTCHA_KEY).toString();
    }

    /**
     * 保存验证码到当前会话中
     * @param captcha 验证码
     */
    public static void saveCaptchaToSession(HttpServletRequest request, String captcha) {
        request.getSession().setAttribute(ConstantKey.SESSION_CAPTCHA_KEY, captcha);
    }

    /**
     * 从cookie中获取令牌
     */
    public static String getTokenFromCookie(HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                     .filter(cookie -> StringUtils.trimToNull(cookie.getValue()) != null)
                     .filter(cookie -> cookie.getName().equals(ConstantKey.COOKIE_MANAGER_KEY))
                     .findFirst()
                     .map(Cookie::getValue).orElse(null);
    }

    /**
     * 保存令牌到cookie中
     * @param token 令牌
     */
    public static void saveTokenToCookie(HttpServletResponse response, String token) {
        if(StringUtils.trimToNull(token) == null) return;
        Cookie cookie = new Cookie(ConstantKey.COOKIE_MANAGER_KEY, token);
        cookie.setMaxAge(3 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    /**
     * 移除Cookie中的令牌
     */
    public static void removeCookieToken(HttpServletResponse response) {
        Cookie cookie = new Cookie(ConstantKey.COOKIE_MANAGER_KEY, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    /**
     * 把当前会话中的管理信息保存到cookie中
     */
    public static void saveCookieFromSession(HttpServletRequest request, HttpServletResponse response) {
        String token = SessionUtil.getTokenFromCookie(request);
        if(token == null) {
            Manager manager = SessionUtil.getManagerFromSession(request);
            SessionUtil.saveTokenToCookie(response, manager.getLoginToken());
        }
    }
}
