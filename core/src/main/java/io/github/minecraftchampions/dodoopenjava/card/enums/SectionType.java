package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * 文本类型
 */
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
     */
    private final String type;

    /**
     * 获取类型
     * @return 类型
     */
    public String getType() {
        return type;
    }
}
