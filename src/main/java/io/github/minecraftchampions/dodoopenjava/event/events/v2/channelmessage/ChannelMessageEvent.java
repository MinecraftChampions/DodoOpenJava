package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 频道消息事件
 */
public abstract class ChannelMessageEvent extends Event {
    public ChannelMessageEvent(boolean isAsync) {
        super(isAsync);
    }

    public ChannelMessageEvent() {
        this(false);
    }
}
