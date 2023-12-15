package io.github.minecraftchampions.dodoopenjava.card.enums;

import lombok.Getter;

/**
 * 对齐方式
 */
@Getter
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
     * -- GETTER --
     * 获取类型
     */
    private final String type;

}
