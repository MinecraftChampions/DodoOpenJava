package io.github.minecraftchampions.dodoopenjava.message.share;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

/**
 * 分享消息组件
 *
 * @author qscbm187531
 */
@RequiredArgsConstructor
@Getter
@Setter
public class ShareMessage implements Message {
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
