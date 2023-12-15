package io.github.minecraftchampions.dodoopenjava.card.enums;

import lombok.Getter;

/**
 * 卡片风格
 */
@Getter
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
     * -- GETTER --
     * 获取类型
     */
    private final String type;

}
