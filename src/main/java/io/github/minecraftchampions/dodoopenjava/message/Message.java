package io.github.minecraftchampions.dodoopenjava.message;

import org.json.JSONObject;

public interface Message {
    JSONObject toMessage();

    int getType();
}
