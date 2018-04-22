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
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 4466004470986993081L;

    /** 结果返回码 */
    private int code;
    /** 结果返回的消息 */
    private String message;
    /** 结果返回的数据 */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    /**
     * 构建一个返回体
     * @param code 返回码
     * @param message 返回消息
     * @param data 返回的数据
     */
    public static <T> Result<T> of(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 构建一个空数据的返回体
     * @param code 返回码
     * @param message 返回消息
     */
    public static Result<Object> of(int code, String message) {
        return of(code, message, null);
    }

    /**
     * 根据布尔类型构建
     * @param success 是否成功
     */
    public static Result<Object> of(boolean success) {
        if(success) {
            return SUCCESS();
        } else {
            return FAILED();
        }
    }

    /**
     * 构建一个Map
     * @param code 返回码
     * @param message 返回信息
     * @param keyAndValue map的 key,value 组合
     */
    public static Result<Map> ofMap(int code, String message, Object...keyAndValue) {
        Map<Object, Object> map = new HashMap<>();
        if(keyAndValue.length % 2 != 0) {
            return of(code, message, map);
        } else {
            for(int i = 0; i < keyAndValue.length; i = i + 2) {
                map.put(keyAndValue[i], keyAndValue[i + 1]);
            }
            return of(code, message, map);
        }
    }

    /**
     * 构建一个成功的返回体
     */
    public static Result<Object> SUCCESS() {
        return of(200, "操作成功", null);
    }

    /**
     * 构建一个带数据的成功返回体
     * @param data 数据
     */
    public static <T> Result<T> SUCCESS(T data) {
        return of(200, "操作成功", data);
    }

    /**
     * 构建一个参数错误的返回体
     * @param message 提示信息
     */
    public static Result<Object> PARAMETER_ERROR(String message) {
        return of(300, message, null);
    }

    /**
     * 构建一个授权失败的返回体
     */
    public static Result<Object> AUTHOR_ERROR() {
        return of(301, "登录失效", null);
    }

    /**
     * 构建一个业务错误的返回体
     */
    public static Result<Object> FAILED() {
        return of(400, "操作失败", null);
    }

    /**
     * 构建一个业务错误的返回体
     */
    public static Result<Object> FAILED(String message) {
        return of(400, message, null);
    }

    /**
     * 构建一个系统错误的返回体
     */
    public static Result<Object> ERROR() {
        return of(500, "系统错误", null);
    }
}
