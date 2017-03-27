package com.heleeos.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.heleeos.blog.bean.Manager;
import com.heleeos.blog.config.ApplicationConfig;
import com.heleeos.blog.constant.SessionKey;

public abstract class ApplicationController {
	
	@Autowired
	protected ApplicationConfig config;
	
	protected String getImageHost() {
	    return config.getImageHost();
	}
	
	/**
	 * 设置管理者信息
	 */
	protected void setManager(HttpServletRequest request, Manager manager) {
	    request.getSession().setAttribute(SessionKey.SESSION_MANAGER_KEY, manager);
    }
	
	/**
	 * 获取管理者信息
	 */
	protected Manager getManager(HttpServletRequest request) {
	    Object obj = request.getSession().getAttribute(SessionKey.SESSION_MANAGER_KEY);
        if(obj != null && obj instanceof Manager){
            return (Manager) obj;
        } else {
            return null;
        }
	}
	
	/**
	 * 清空管理者信息
	 */
	protected void removeManager(HttpServletRequest request) {
	    request.getSession().removeAttribute(SessionKey.SESSION_MANAGER_KEY);
    }
	
	/**
	 * 获取验证码
	 */
	protected String getCptcha(HttpServletRequest request) {
	    Object obj = request.getSession().getAttribute(SessionKey.SESSION_CPTCHA_KEY);
	    if(obj != null) {
	        return obj.toString();
	    } else {
	        return null;
	    }
	}
	
	/**
	 * 设置验证码
	 */
	protected void setCptcha(HttpServletRequest request, String text) {
	    request.getSession().setAttribute(SessionKey.SESSION_CPTCHA_KEY, text);
    }
}
