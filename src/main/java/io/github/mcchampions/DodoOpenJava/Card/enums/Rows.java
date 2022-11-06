package io.github.mcchampions.DodoOpenJava.Card.enums;

/**
 * �����߶�
 * @author qscbm187531
 */
public enum Rows {
    /**
     * 1
     */
    one(1),
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
    four(4);

    Rows(int row) {
        this.row = row;
    }

    /**
     * ����
     */
    private final int row;

    /**
     * ��ȡ����
     * @return ����
     */
    public int getRow() {
        return row;
    }
}
