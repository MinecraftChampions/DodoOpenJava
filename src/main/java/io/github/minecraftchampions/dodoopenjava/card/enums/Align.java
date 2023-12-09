package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * 对齐方式
 */
public enum Align {
    /**
     * 左对齐
     */
    Left("left"),
    /**
     * 右对齐
     */
    Right("right");

    Align(String type) {
        this.type = type;
    }

    /**
     * 对齐方式
     */
    private final String type;

    /**
     * 获取类型
     *
     * @return 类型
     */
    public String getType() {
        return type;
    }
}
