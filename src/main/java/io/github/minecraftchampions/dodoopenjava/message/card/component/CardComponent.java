package io.github.minecraftchampions.dodoopenjava.message.card.component;

import org.json.JSONObject;

/**
 * 组件
 */
public interface CardComponent {
    String getType();

    JSONObject toJSONObject();
}
