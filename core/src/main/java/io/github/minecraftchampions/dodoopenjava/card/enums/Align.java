package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * ���뷽ʽ
 * @author qscbm187531
 */
public enum Align {
    /**
     * �����
     */
    Left("left"),
    /**
     * �Ҷ���
     */
    Right("right");

    Align(String type) {
        this.type = type;
    }

    /**
     * ���뷽ʽ
     */
    private final String type;

    /**
     * ��ȡ����
     * @return ����
     */
    public String getType() {
        return type;
    }
}
