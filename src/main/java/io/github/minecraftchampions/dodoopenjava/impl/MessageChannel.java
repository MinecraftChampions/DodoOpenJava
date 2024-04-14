package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.Bot;
import io.github.minecraftchampions.dodoopenjava.api.Channel;
import io.github.minecraftchampions.dodoopenjava.api.ChannelType;
import io.github.minecraftchampions.dodoopenjava.api.User;
import io.github.minecraftchampions.dodoopenjava.message.Emoji;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息频道实现
 */
@Slf4j
public class MessageChannel extends ChannelImpl {

    private MessageChannel(@NonNull String channelId, @NonNull String islandSourceId, @NonNull Bot bot) {
        super(channelId, islandSourceId, bot);
    }

    public static MessageChannel of(@NonNull Channel channel) {
        if (channel.getChannelType() != ChannelType.MESSAGE) {
            throw new RuntimeException("错误的频道类型");
        }
        return new MessageChannel(channel.getChannelId(), channel.getIslandSourceId(), channel.getBot());
    }

    /**
     * 发送消息
     *
     * @param message message
     * @return result
     */
    public Result sendMessage(@NonNull Message message) {
        return getBot().getApi().V2.getChannelMessageApi().sendMessage(getChannelId(), message);
    }

    /**
     * 编辑消息
     *
     * @param messageId 消息id
     * @param message   消息
     * @return result
     */
    public Result editMessage(@NonNull String messageId, @NonNull Message message) {
        return getBot().getApi().V2.getChannelMessageApi().editChannelMessage(messageId, message);
    }

    /**
     * 回复消息
     *
     * @param messageId 消息id
     * @param message   消息
     * @return result
     */
    public Result referencedMessage(@NonNull String messageId, @NonNull Message message) {
        return getBot().getApi().V2.getChannelMessageApi().referencedMessage(getChannelId(), message, messageId);
    }

    /**
     * 发送频道私信
     *
     * @param dodoSourceId id
     * @param message      消息
     * @return result
     */
    public Result sendPersonalMessage(@NonNull String dodoSourceId, @NonNull Message message) {
        return getBot().getApi().V2.getChannelMessageApi().sendChannelPersonalMessage(getChannelId(), message, dodoSourceId);
    }

    /**
     * 撤回消息
     *
     * @param messageId 消息id
     * @param reason    理由(传null视为不传参数)
     * @return result
     */
    public Result withdrawMessage(@NonNull String messageId, String reason) {
        if (reason != null) {
            return getBot().getApi().V2.getChannelMessageApi().withdrawChannelMessageWithReason(messageId, reason);
        }
        return getBot().getApi().V2.getChannelMessageApi().withdrawChannelMessage(messageId);
    }

    /**
     * 置顶消息
     *
     * @param messageId 消息id
     * @return result
     */
    public Result pinMessage(@NonNull String messageId) {
        return getBot().getApi().V2.getChannelMessageApi().setChannelMessageTop(messageId,1);
    }

    /**
     * 取消置顶消息
     *
     * @param messageId 消息id
     * @return result
     */
    public Result unpinMessage(@NonNull String messageId) {
        return getBot().getApi().V2.getChannelMessageApi().setChannelMessageTop(messageId,0);
    }

    /**
     * 获取消息反应列表
     *
     * @param messageId 消息id
     * @return 反应列表（前为反应表情，后为数量）
     */
    public Map<Emoji, Integer> getMessageReactionList(@NonNull String messageId) {
        Result result = getBot().getApi().V2.getChannelMessageApi().getChannelMessageReactionList(messageId);
        if (result.isFailure()) {
            log.error("获取消息信息失败, 错误消息:{};状态code:{};错误数据:{}", result.getMessage(), result.getStatusCode(), result.getJSONObjectData());
            return null;
        }
        List<JSONObject> list = result.getJSONObjectData().getJSONArray("data").toList().stream().map((o) -> {
            if (o instanceof Map<?, ?> map) {
                return new JSONObject(map);
            }
            return new JSONObject();
        }).toList();
        Map<Emoji, Integer> map = new HashMap<>();
        list.forEach((jsonObject) -> {
            Emoji emoji = Emoji.of(jsonObject.getJSONObject("emoji").getString("id"));
            map.put(emoji, jsonObject.getInt("count"));
        });
        return map;
    }

    /**
     * 用于获取指定消息反应的成员列表
     *
     * @param messageId 消息id
     * @param emoji     消息反应
     * @return 用户列表
     */
    public List<User> getMessageReactionMemberList(@NonNull String messageId, @NonNull Emoji emoji) {
        return null;
    }

    /**
     * 添加表情反应
     *
     * @param messageId 消息id
     * @param emoji     消息反应
     * @return result
     */
    public Result addMessageReaction(@NonNull String messageId, @NonNull Emoji emoji) {
        return null;
    }

    /**
     * 移除表情反应
     *
     * @param messageId 消息id
     * @param emoji     消息反应
     * @return result
     */
    public Result removeMessageReaction(@NonNull String messageId, @NonNull Emoji emoji) {
        return null;
    }
}
