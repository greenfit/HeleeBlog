package com.heleeos.blog.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

/**
 * 前后端交互的结果集.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 4466004470986993081L;

    //结果返回码
    private int code;

    //结果返回的消息
    private Map<String, Object> message;
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public Map<String, Object> getMessage() {
        return message;
    }
    
    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }
    
    public Result putInfo(Object value) {
        return putMessage("info", value);
    }
    
    public Result putBean(Object value) {
        return putMessage("beans", value);
    }
    
    public Result putMessage(String key, Object value) {
        if(message == null)
            message = new HashMap<>();
        message.put(key, value);
        return this;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
