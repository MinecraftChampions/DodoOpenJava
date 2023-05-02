package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * ��Ƭ���
 * @author qscbm187531
 */
public enum Theme {
    /**
     * ��ɫ
     */
    Grey("grey"),
    /**
     * ��ɫ
     */
    Red("red"),
    /**
     * ��ɫ
     */
    Orange("orange"),
    /**
     * ��ɫ
     */
    Yellow("yellow"),
    /**
     * ��ɫ
     */
    Green("green"),
    /**
     * ����ɫ
     */
    Indigo("indigo"),
    /**
     * ��ɫ
     */
    Blue("blue"),
    /**
     * ��ɫ
     */
    Purple("purple"),
    /**
     * ��ɫ
     */
    Black("black"),
    /**
     * Ĭ��
     */
    Default("default");

    Theme(String type) {
        this.type = type;
    }

    /**
     * ����
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
