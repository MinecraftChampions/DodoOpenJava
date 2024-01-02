package io.github.minecraftchampions.dodoopenjava.message.card.enums;

import lombok.Getter;

/**
 * 按钮颜色
 */
@Getter
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
     * -- GETTER --
     * 获取类型
     */
    private final String type;

}
