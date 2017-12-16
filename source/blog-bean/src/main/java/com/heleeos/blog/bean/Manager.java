package com.heleeos.blog.bean;

import com.heleeos.blog.common.ManagerState;

import java.util.Date;

/**
 * 管理员账户.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public class Manager {
    private Integer id;

    private String userName;

    private String passWord;
    
    private String nickName;
    
    private String realName;

    private String managerPicture;

    private Date loginTime;

    private byte managerState;
    
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
}