package io.github.minecraftchampions.dodoopenjava.message.share;

import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;

/**
 * 分享消息组件
 */
@RequiredArgsConstructor
public class ShareMessage extends Message {
    @NonNull
    private String jumpUrl;

    /**
     * 设置跳转url
     *
     * @param jumpUrl url
     */
    public void setJumpUrl(@NonNull String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }

    @Override
    public JSONObject toMessage() {
        return new JSONObject().put("jumpUrl", jumpUrl);
    }

    @Override
    public int getType() {
        return 4;
    }
}
