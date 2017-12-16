package com.heleeos.blog.common;

/**
 * 管理员账号状态枚举值.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
public enum ManagerState {

    /**
     * 未知状态.
     */
    OTHER(-1),
    /**
     * 正常状态.
     */
    NORMAL(0),
    /**
     * 禁止状态.
     */
    FORBIDDEN(1), 
    /**
     * 异常状态,必须修改密码.
     */
    ABNORMAL(2);

    ManagerState(Integer state) {
        this.state = state.byteValue();
    }

    private Byte state;
    
    public Byte getState() {
        return state;
    }

    /**
     * 通过数值获取枚举对象
     * @param state 数值
     */
    public static ManagerState of(Integer state) {
        switch (state) {
            case 0: return  ManagerState.NORMAL;
            case 1: return  ManagerState.FORBIDDEN;
            case 2: return  ManagerState.ABNORMAL;
            default: return ManagerState.OTHER;
        }
    }
}
