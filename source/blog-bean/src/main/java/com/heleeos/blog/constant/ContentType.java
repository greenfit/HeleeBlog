package com.heleeos.blog.constant;

public enum ContentType {
    
    /**
     * 未知类型.
     */
    OTHER(-1),
    /**
     * HTML.
     */
    HTML(0),
    /**
     * Markdown.
     */
    MARKDOWN(1);

    ContentType(Integer type) {
        this.type = type.byteValue();
    }
    
    private Byte type;

    public Byte getType() {
        return type;
    }
    
    public static ContentType of(Integer state) {
        switch (state) {
            case 0: return HTML;
            case 1: return MARKDOWN;
            default: return OTHER;
        }
    }
}
