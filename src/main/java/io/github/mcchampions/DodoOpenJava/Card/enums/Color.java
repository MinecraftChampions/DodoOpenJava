package io.github.mcchampions.DodoOpenJava.Card.enums;

/**
 * 按钮颜色
 * @author qscbm187531
 */
public enum Color {
    /**
     * 灰色
     */
    Grey("grey"),
    /**
     * 红色
     */
    Red("red"),
    /**
     * 橙色
     */
    Orange("orange"),
    /**
     * 绿色
     */
    Green("green"),
    /**
     * 蓝色
     */
    Blue("blue"),
    /**
     * 紫色
     */
    Purple("purple"),
    /**
     * 默认
     */
    Default("default");

    Color(String type) {
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
