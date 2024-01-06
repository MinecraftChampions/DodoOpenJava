package io.github.minecraftchampions.dodoopenjava.message.file;

import io.github.minecraftchampions.dodoopenjava.message.AbstractMessage;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

/**
 * 文件消息组件
 */
@RequiredArgsConstructor
@Getter
@Setter
public class FileMessage extends AbstractMessage {
    @NonNull
    private String url;

    @NonNull
    private String name;

    @NonNull
    private long size;

    @Override
    public JSONObject toMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", url);
        jsonObject.put("name", name);
        jsonObject.put("size", size);
        return jsonObject;
    }

    @Override
    public int getType() {
        return 5;
    }
}