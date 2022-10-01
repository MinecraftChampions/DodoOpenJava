package io.github.mcchampions.DodoOpenJava.Card.Enums;

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
        this.row = row;
    }

    /**
     * 行数
     */
    private final int row;

    /**
     * 获取行数
     * @return 行数
     */
    public int getRow() {
        return row;
    }
}
