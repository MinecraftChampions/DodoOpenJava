package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelvoice;

import io.github.minecraftchampions.dodoopenjava.event.AbstractEvent;

/**
 * 语音频道相关事件
 */
public abstract class AbstractChannelVoiceEvent extends AbstractEvent {
    public AbstractChannelVoiceEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractChannelVoiceEvent() {
        this(false);
    }
}
