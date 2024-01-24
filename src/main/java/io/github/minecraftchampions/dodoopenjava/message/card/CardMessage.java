package io.github.minecraftchampions.dodoopenjava.message.card;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 卡片消息
 */
public class CardMessage implements Message {

    @Override
    public JSONObject toMessage() {

        return null;
    }

    @Override
    public int getType() {
        return 6;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Builder {
        private final List<Object> components = new ArrayList<>();
    }
}
