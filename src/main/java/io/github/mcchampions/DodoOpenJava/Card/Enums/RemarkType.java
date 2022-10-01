package io.github.mcchampions.DodoOpenJava.Card.Enums;

/**
 * 备注标签
 * @author qscbm187531
 */
public enum RemarkType {
    /**
     * MD文本
     */
    Markdown("dodo-md"),
    /**
     * 普通文本
     */
    PlainText("plain-text"),
    /**
     * 图片
     */
    Image("image");

    RemarkType(String type) {
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
