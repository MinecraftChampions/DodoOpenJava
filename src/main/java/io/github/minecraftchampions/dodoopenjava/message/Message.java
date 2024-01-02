package io.github.minecraftchampions.dodoopenjava.message;

import org.json.JSONObject;

public abstract class Message {
    public abstract JSONObject toMessage();

    public abstract int getType();
}
