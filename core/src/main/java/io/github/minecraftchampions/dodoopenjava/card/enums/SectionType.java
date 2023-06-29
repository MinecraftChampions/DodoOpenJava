package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * �ı�����
 */
public enum SectionType {
    /**
     * md�ı�
     */
    Markdown("dodo-md"),
    /**
     * ��ͨ�ı�
     */
    PlainText("plain-text"),
    /**
     * �����ı�
     */
    Paragraph("Paragraph");

    SectionType(String type) {
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
