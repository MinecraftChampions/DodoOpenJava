package io.github.minecraftchampions.dodoopenjava.card.component;

import org.json.JSONObject;

/**
 * ���
 */
public class Component {
    protected JSONObject jsonCard = new JSONObject();

    /**
     * ��ʼ��
     */
    public Component() {}

    public JSONObject getJsonCard() {
        return jsonCard;
    }
}
