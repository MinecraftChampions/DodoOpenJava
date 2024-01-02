package io.github.minecraftchampions.dodoopenjava.message.card.enums;

import lombok.Getter;

/**
 * 时间显示种类
 */
@Getter
public enum Style {
    /**
     * 天
     */
    day("day"),
    /**
     * 小时
     */
    hour("hour");

    private final String style;

    Style(String sty) {
        style = sty;
    }

}
