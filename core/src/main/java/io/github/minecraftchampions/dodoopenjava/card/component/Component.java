package io.github.minecraftchampions.dodoopenjava.card.component;

import org.json.JSONObject;

/**
 * 组件
 */
public class Component {
    protected JSONObject jsonCard = new JSONObject();

    /**
     * 初始化
     */
    public Component() {}

    public JSONObject getJsonCard() {
        return jsonCard;
    }
}
