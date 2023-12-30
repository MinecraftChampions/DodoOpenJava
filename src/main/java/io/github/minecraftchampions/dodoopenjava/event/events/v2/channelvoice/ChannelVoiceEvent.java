package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelvoice;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 语音频道相关事件
 */
public abstract class ChannelVoiceEvent extends Event {
    public ChannelVoiceEvent(boolean isAsync) {
        super(isAsync);
    }

    public ChannelVoiceEvent() {
        this(false);
    }
}
