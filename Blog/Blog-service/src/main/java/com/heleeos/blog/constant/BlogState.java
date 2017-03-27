package com.heleeos.blog.constant;

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
    
    private BlogState(Integer state) {
        this.state = state.byteValue();
    }
    
    public Byte getState() {
        return state;
    }
    
    public static BlogState of(Integer state) {
        switch (state) {
            case 0: return NORMAL;
            case 1: return DELETE;
            case 2: return UPDATE;    
            default: return OTHER;
        }
    }
}
