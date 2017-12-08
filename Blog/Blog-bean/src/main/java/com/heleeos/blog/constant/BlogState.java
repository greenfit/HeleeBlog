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

    BlogState(Integer state) {
        this.state = state.byteValue();
    }
    
    private Byte state;

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
    
    public static BlogState of(String state) {
        switch (state.toUpperCase()) {
            case "NORMAL": return NORMAL;
            case "DELETE": return DELETE;
            case "UPDATE": return UPDATE;    
            default: return OTHER;
        }
    }
}
