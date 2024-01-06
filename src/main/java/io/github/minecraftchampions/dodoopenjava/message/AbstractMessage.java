package io.github.minecraftchampions.dodoopenjava.message;

import org.json.JSONObject;

public abstract class AbstractMessage {
    public abstract JSONObject toMessage();

    public abstract int getType();
}
