package io.github.minecraftchampions.dodoopenjava.message.card.enums;

import lombok.Getter;

/**
 * 文本类型
 * @author qscbm187531
 */
@Getter
public enum TextType {
    /**
     * MD文本
     */
    Markdown("dodo-md"),
    /**
     * 普通文本
     */
    PlainText("plain-text");

    TextType(String type) {
        this.type = type;
    }

    /**
     * 类型
     * -- GETTER --
     * 获取类型
     */
    private final String type;

    @Override
    public String toString() {
        return getType();
    }
}