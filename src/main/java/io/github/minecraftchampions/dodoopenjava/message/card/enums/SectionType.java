package io.github.minecraftchampions.dodoopenjava.message.card.enums;

import lombok.Getter;

/**
 * 文本类型
 */
@Getter
public enum SectionType {
    /**
     * md文本
     */
    Markdown("dodo-md"),
    /**
     * 普通文本
     */
    PlainText("plain-text"),
    /**
     * 多栏文本
     */
    Paragraph("Paragraph");

    SectionType(String type) {
        this.type = type;
    }

    /**
     * 行数
     * -- GETTER --
     * 获取类型
     */
    private final String type;

}
