package com.heleeos.blog.common;

/**
 * 博客状态的枚举值.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public enum BlogState {
    
    /**
     * 未知状态.
     */
    OTHER(-1),
    /**
     * 正常状态.
     */
    NORMAL(0),
    /**
     * 删除状态.
     */
    DELETE(1),
    /**
     * 草稿状态.
     */
    UPDATE(2);

    private Byte state;

    BlogState(Integer state) {
        this.state = state.byteValue();
    }

    public Byte getState() {
        return state;
    }

    /**
     * 通过数值获取枚举对象
     * @param state 数值
     */
    public static BlogState of(Integer state) {
        switch (state) {
            case 0:  return BlogState.NORMAL;
            case 1:  return BlogState.DELETE;
            case 2:  return BlogState.UPDATE;
            default: return BlogState.OTHER;
        }
    }

    /**
     * 通过字符串获取枚举对象
     * @param state 字符串
     */
    public static BlogState of(String state) {
        switch (state.toUpperCase()) {
            case "NORMAL": return BlogState.NORMAL;
            case "DELETE": return BlogState.DELETE;
            case "UPDATE": return BlogState.UPDATE;
            default:       return BlogState.OTHER;
        }
    }
}
