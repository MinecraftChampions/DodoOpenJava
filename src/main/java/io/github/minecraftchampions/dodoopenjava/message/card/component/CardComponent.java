package io.github.minecraftchampions.dodoopenjava.message.card.component;

import org.json.JSONObject;

public abstract class CardComponent {
    public abstract String getType();

    public abstract JSONObject toJSONObject();
}
