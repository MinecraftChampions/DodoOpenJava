package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.message.text.TextMessage;

/**
 * 命令发送者接口
 *
 * @author qscbm187531
 */
public interface CommandSender extends User {
    /**
     * 编辑消息
     *
     * @param message 编辑后的消息
     */
    void editMessage(TextMessage message);

    /**
     * 获取触发事件的频道ID
     *
     * @return id
     */
    String getChannelId();

    /**
     * 获取消息ID
     *
     * @return id
     */
    String getMessageId();

    /**
     * 是否私聊命令
     *
     * @return boolean
     */
    boolean isPersonalChat();
}
