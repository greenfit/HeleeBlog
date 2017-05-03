package com.heleeos.blog.constant;

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
    
    private Byte state;
    
    private ManagerState(Integer state) {
        this.state = state.byteValue();
    }
    
    public Byte getState() {
        return state;
    }
    
    public static ManagerState of(Integer state) {
        switch (state) {
            case 0: return NORMAL;
            case 1: return FORBIDDEN;
            case 2: return ABNORMAL;    
            default: return OTHER;
        }
    }
}
