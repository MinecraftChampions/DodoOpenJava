package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * ��ť��ɫ
 */
public enum Color {
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
    Green("green"),
    /**
     * ��ɫ
     */
    Blue("blue"),
    /**
     * ��ɫ
     */
    Purple("purple"),
    /**
     * Ĭ��
     */
    Default("default");

    Color(String type) {
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
