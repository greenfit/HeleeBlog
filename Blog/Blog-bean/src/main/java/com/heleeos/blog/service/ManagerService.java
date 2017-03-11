package com.heleeos.blog.service;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.heleeos.blog.bean.Manager;
import com.heleeos.blog.mapper.ManagerMapper;

/**
 * 管理员信息数据服务层.
 * 
 * @author liyu
 */
@Service
public class ManagerService {
    
    private final Logger logger = LogManager.getLogger(getClass());
    
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
            logger.error("保存[管理员信息]异常,原因:" + e.getMessage());
            return false;
        }
    }
    
    /**
     * 禁用管理员.
     * 
     * @param id 管理员ID
     */
    public boolean delete(Integer id) {
        if(id == null || id == 0) return false;
        try {
            return managerMapper.delete(id) == 1;
        } catch (Exception e) {
            logger.error("删除[管理员信息]异常,原因:" + e.getMessage());
            return false;
        }
    }

    /**
     * 根据用户名和密码获取管理员
     * 
     * @param username 用户名
     * @param password 密码
     */
    public Manager get(String username, String password) {
        if(StringUtils.trimToNull(username) == null || StringUtils.trimToNull(password) == null) return null;
        try {
            return managerMapper.get(username, password);
        } catch (Exception e) {
            logger.error("获取[管理员信息]异常,原因:" + e.getMessage());
            return null;
        }
    }
}
