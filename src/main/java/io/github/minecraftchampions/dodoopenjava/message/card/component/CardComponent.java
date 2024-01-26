package io.github.minecraftchampions.dodoopenjava.message.card.component;

import org.json.JSONObject;

public interface CardComponent {
    String getType();

    JSONObject toJSONObject();
}
