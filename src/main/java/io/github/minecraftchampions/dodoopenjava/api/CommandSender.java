package io.github.minecraftchampions.dodoopenjava.api;

import io.github.minecraftchampions.dodoopenjava.message.text.TextMessage;

/**
 * 命令发送者接口
 */
public interface CommandSender extends User {
    void editMessage(TextMessage message);

    String getChannelId();

    String getMessageId();

    boolean isPersonalChat();
}
