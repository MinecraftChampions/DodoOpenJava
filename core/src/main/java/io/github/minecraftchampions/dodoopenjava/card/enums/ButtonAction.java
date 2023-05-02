package io.github.minecraftchampions.dodoopenjava.card.enums;

/**
 * ��ť�����ö��
 * @author Administrator
 */

public enum ButtonAction {
    /**
     * ��תURL
     */
    link_url("link_url"),
    /**
     * �ش�����
     */
    call_back("call_back"),
    /**
     * �����ı�
     */
    copy_content("copy_content"),
    /**
     * �ش���
     */
    form("form");
    ButtonAction(String type) {
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
