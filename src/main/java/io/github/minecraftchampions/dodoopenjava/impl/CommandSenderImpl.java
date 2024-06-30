package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.debug.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.CommandSender;
import io.github.minecraftchampions.dodoopenjava.message.text.TextMessage;
import lombok.Getter;
import lombok.NonNull;
import org.json.JSONObject;

/**
 * 命令发送者实现
 *
 * @author qscbm187531
 */
@Getter
public class CommandSenderImpl extends DodoUserImpl implements CommandSender {
    /**
     * -- GETTER --
     * 获取触发命令的频道号
     */
    private String channelId;

    /**
     * -- GETTER --
     * 获取触发命令的消息ID
     * 私聊命令为null
     */
    @NonNull
    private final String messageId;

    /**
     * -- GETTER --
     * 是否私聊
     */
    @NonNull
    private final boolean isPersonalChat;

    /**
     * 初始化发送者这个类型
     *
     * @param jsontext JSONText
     */
    public CommandSenderImpl(@NonNull JSONObject jsontext, @NonNull Bot bot, boolean personalMessage) {
        super(jsontext.getJSONObject("data").getJSONObject("eventBody").getString("dodoSourceId"),
                jsontext.getJSONObject("data").getJSONObject("eventBody").getString("islandSourceId"),
                bot);
        this.isPersonalChat = personalMessage;
        this.messageId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("messageId");
        if (!personalMessage) {
            this.channelId = jsontext.getJSONObject("data").getJSONObject("eventBody").getString("channelId");
        }
    }

    @Override
    public Result editMessage(TextMessage message) {
        return getBot().getApi().V2.channelMessageApi.editChannelMessage(messageId, message.toString());
    }
}