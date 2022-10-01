package io.github.mcchampions.DodoOpenJava.Card.Enums;

/**
 * 卡片风格
 * @author qscbm187531
 */
public enum Theme {
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
     * 黄色
     */
    Yellow("yellow"),
    /**
     * 绿色
     */
    Green("green"),
    /**
     * 淡蓝色
     */
    Indigo("indigo"),
    /**
     * 蓝色
     */
    Blue("blue"),
    /**
     * 紫色
     */
    Purple("purple"),
    /**
     * 黑色
     */
    Black("black"),
    /**
     * 默认
     */
    Default("default");

    Theme(String type) {
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
