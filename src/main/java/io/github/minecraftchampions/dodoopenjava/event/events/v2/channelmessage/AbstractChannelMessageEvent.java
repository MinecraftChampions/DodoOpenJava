package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelmessage;

import io.github.minecraftchampions.dodoopenjava.event.AbstractEvent;

/**
 * 频道消息相关事件
 */
public abstract class AbstractChannelMessageEvent extends AbstractEvent {
    public AbstractChannelMessageEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractChannelMessageEvent() {
        this(false);
    }
}
