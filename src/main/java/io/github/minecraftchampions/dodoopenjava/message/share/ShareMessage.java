package io.github.minecraftchampions.dodoopenjava.message.share;

import io.github.minecraftchampions.dodoopenjava.message.AbstractMessage;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

/**
 * 分享消息组件
 */
@RequiredArgsConstructor
@Getter
@Setter
public class ShareMessage extends AbstractMessage {
    @NonNull
    private String jumpUrl;

    @Override
    public JSONObject toMessage() {
        return new JSONObject().put("jumpUrl", jumpUrl);
    }

    @Override
    public int getType() {
        return 4;
    }
}
