package io.github.minecraftchampions.dodoopenjava.card.enums;

import lombok.Getter;

/**
 * 行数（2-6）
 */
@Getter
public enum Cols {
    /**
     * 2
     */
    two(2),
    /**
     * 3
     */
    three(3),
    /**
     * 4
     */
    four(4),
    /**
     * 5
     */
    five(5),
    /**
     * 6
     */
    six(6);

    Cols(int row) {
        this.col = row;
    }

    /**
     * 行数
     * -- GETTER --
     * 获取行数
     */
    private final int col;

}
