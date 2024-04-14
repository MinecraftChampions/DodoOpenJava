package io.github.minecraftchampions.dodoopenjava.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.json.JSONObject;

/**
 * 消息反应
 */
@Data
@AllArgsConstructor(staticName = "of")
public class Emoji {
    @NonNull
    private String id;

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("type", 1);
        return jsonObject;
    }

    public String toString() {
        return toJSONObject().toString();
    }
}
