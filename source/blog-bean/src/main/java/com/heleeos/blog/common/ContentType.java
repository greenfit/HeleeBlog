package com.heleeos.blog.common;

/**
 * 博客内容编写方式的枚举值.
 * Created with Li Yu on 2017/12/16.
 *
 * @author liyu
 * @author kissaoe@gmail.com
 * @version 1.0.1
 */
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

    private Byte type;

    ContentType(Integer type) {
        this.type = type.byteValue();
    }

    public Byte getType() {
        return type;
    }

    /**
     * 通过数值获取枚举对象
     * @param state 数值
     */
    public static ContentType of(Integer state) {
        switch (state) {
            case 0: return  ContentType.HTML;
            case 1: return  ContentType.MARKDOWN;
            default: return ContentType.OTHER;
        }
    }
}
