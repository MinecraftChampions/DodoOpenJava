package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * �ı�����
 * @author qscbm187531
 */
public enum TextType {
    /**
     * MD�ı�
     */
    Markdown("dodo-md"),
    /**
     * ��ͨ�ı�
     */
    PlainText("plain-text");

    TextType(String type) {
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
