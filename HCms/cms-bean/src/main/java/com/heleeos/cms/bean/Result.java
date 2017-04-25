package com.heleeos.cms.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class Result implements Serializable {

    private static final long serialVersionUID = 4466004470986993081L;
    
    private int code;
    
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
