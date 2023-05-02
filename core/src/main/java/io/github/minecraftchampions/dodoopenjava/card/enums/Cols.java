package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * ������2-6��
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
     * ����
     */
    private final int col;

    /**
     * ��ȡ����
     * @return ����
     */
    public int getCol() {
        return col;
    }
}
