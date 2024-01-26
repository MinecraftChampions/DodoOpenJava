package io.github.minecraftchampions.dodoopenjava.message.card;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.AllArgsConstructor;
import org.json.JSONObject;

/**
 * 卡片消息
 */
@AllArgsConstructor
public class CardMessage implements Message {
    private final JSONObject json = new JSONObject();

    @Override
    public String toString() {
        return json.toString();
    }


    @Override
    public JSONObject toMessage() {
        return json;
    }

    @Override
    public int getType() {
        return 6;
    }
}