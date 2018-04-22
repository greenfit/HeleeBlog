package com.heleeos.blog.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heleeos.blog.dto.Manager;
import com.heleeos.blog.common.ManagerState;
import com.heleeos.blog.dao.ManagerMapper;

/**
 * 管理员信息数据服务层, t_manager.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
@Service
public class ManagerService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ManagerMapper managerMapper;
    
    /**
     * 保存管理员信息.
     * 
     * @param bean 管理员
     */
    public boolean save(Manager bean) {
        if(bean == null) return false;
        try {
            if(bean.getId() == 0) {
                return managerMapper.insert(bean) == 1;
            } else {
                return managerMapper.update(bean) == 1;
            }
        } catch (Exception e) {
            logger.error(String.format("保存[管理员信息]异常,原因:%s", e.getMessage()), e);
            return false;
        }
    }
    
    /**
     * 修改管理员状态.
     * 
     * @param id 管理员ID
     * @param state 管理员状态
     * @see ManagerState
     */
    public boolean updateState(Integer id, Byte state) {
        if(id == null || id == 0) return false;
        try {
            return managerMapper.updateState(id, state) == 1;
        } catch (Exception e) {
            logger.error(String.format("修改[管理员状态]异常,原因:%s", e.getMessage()), e);
            return false;
        }
    }
    
    /**
     * 更新管理员登陆时间.
     * 
     * @param id 管理员ID
     */
    public boolean updateLoginTime(Integer id) {
        if(id == null || id == 0) return false;
        try {
            return managerMapper.updateLoginTime(id) == 1;
        } catch (Exception e) {
            logger.error(String.format("修改[管理员时间]异常,原因:%s", e.getMessage()), e);
            return false;
        }
    }

    /**
     * 根据用户名和密码获取管理员
     * 
     * @param username 用户名
     * @param password 密码
     */
    public Manager login(String username, String password) {
        if(StringUtils.trimToNull(username) == null || StringUtils.trimToNull(password) == null) return null;
        try {
            return managerMapper.get(username, password);
        } catch (Exception e) {
            logger.error(String.format("登录[管理员]异常,原因:%s", e.getMessage()), e);
            return null;
        }
    }

    /**
     * 通过令牌获取管理员
     * @param token 令牌
     */
    public Manager getManagerByToken(String token) {
        if(StringUtils.trimToNull(token) == null) return null;
        try {
            return managerMapper.getByToken(token);
        } catch (Exception e) {
            logger.error(String.format("获取[管理员信息]异常,原因:%s", e.getMessage()), e);
            return null;
        }
    }

    /**
     * 更新管理员的令牌
     * @param id 管理员ID
     * @param token 令牌
     */
    public boolean updateToken(Integer id, String token) {
        try {
            return managerMapper.updateToken(id, token) == 1;
        } catch (Exception e) {
            logger.error(String.format("更新[管理员令牌]异常,原因:%s", e.getMessage()), e);
            return false;
        }
    }
}
