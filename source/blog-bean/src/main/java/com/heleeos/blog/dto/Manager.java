package com.heleeos.blog.dto;

import com.heleeos.blog.common.ManagerState;

import java.util.Date;

/**
 * 管理员账户, t_manager 表
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 */
public class Manager {

    /** 自增ID */
    private Integer id;
    /** 用户名 */
    private String userName;
    /** 密码 */
    private String passWord;
    /** 昵称 */
    private String nickName;
    /** 真实姓名 */
    private String realName;
    /** 头像 */
    private String managerPicture;
    /** 上次登录时间 */
    private Date loginTime;
    /** 状态 */
    private byte managerState;
    /** 登录令牌 */
    private String loginToken;
    
    public Manager() {
        setId(0);
        setManagerState(ManagerState.NORMAL.getState());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getManagerPicture() {
        return managerPicture;
    }

    public void setManagerPicture(String managerPicture) {
        this.managerPicture = managerPicture;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public byte getManagerState() {
        return managerState;
    }

    public void setManagerState(byte managerState) {
        this.managerState = managerState;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
}