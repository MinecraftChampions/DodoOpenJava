package io.github.minecraftchampions.dodoopenjava.impl;

import io.github.minecraftchampions.dodoopenjava.Result;
import io.github.minecraftchampions.dodoopenjava.api.*;
import io.github.minecraftchampions.dodoopenjava.message.Emoji;
import io.github.minecraftchampions.dodoopenjava.message.Message;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

/**
 * 消息频道实现
 */
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
    public Result sendMessage(Message message) {
        return null;
    }

    /**
     * 编辑消息
     *
     * @param messageId 消息id
     * @param message   消息
     * @return result
     */
    public Result editMessage(String messageId, Message message) {
        return null;
    }

    /**
     * 撤回消息
     *
     * @param messageId 消息id
     * @return result
     */
    public Result withdrawMessage(String messageId) {
        return null;
    }

    /**
     * 置顶消息
     *
     * @param messageId 消息id
     * @return result
     */
    public Result pinMessage(String messageId) {
        return null;
    }

    /**
     * 取消置顶消息
     *
     * @param messageId 消息id
     * @return result
     */
    public Result unpinMessage(String messageId) {
        return null;
    }

    /**
     * 获取消息反应列表
     *
     * @param messageId 消息id
     * @return 反应列表（前为反应表情，后为数量）
     */
    public Map<Emoji, Integer> getMessageReactionList(String messageId) {
        return null;
    }

    /**
     * 用于获取指定消息反应的成员列表
     *
     * @param messageId 消息id
     * @param emoji     消息反应
     * @return 用户列表
     */
    public List<User> getMessageReactionMemberList(String messageId, Emoji emoji) {
        return null;
    }

    /**
     * 添加表情反应
     *
     * @param messageId 消息id
     * @param emoji     消息反应
     * @return result
     */
    public Result addMessageReactionList(String messageId, Emoji emoji) {
        return null;
    }

    /**
     * 移除表情反应
     *
     * @param messageId 消息id
     * @param emoji     消息反应
     * @return result
     */
    public Result removeMessageReactionMemberList(String messageId, Emoji emoji) {
        return null;
    }
}
