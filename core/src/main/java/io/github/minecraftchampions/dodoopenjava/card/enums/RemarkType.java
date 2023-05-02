package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * ��ע��ǩ
 * @author qscbm187531
 */
public enum RemarkType {
    /**
     * MD�ı�
     */
    Markdown("dodo-md"),
    /**
     * ��ͨ�ı�
     */
    PlainText("plain-text"),
    /**
     * ͼƬ
     */
    Image("image");

    RemarkType(String type) {
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
