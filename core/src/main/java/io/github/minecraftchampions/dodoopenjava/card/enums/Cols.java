package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * 行数（2-6）
 * @author qscbm187531
 */
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
     */
    private final int col;

    /**
     * 获取行数
     * @return 行数
     */
    public int getCol() {
        return col;
    }
}
