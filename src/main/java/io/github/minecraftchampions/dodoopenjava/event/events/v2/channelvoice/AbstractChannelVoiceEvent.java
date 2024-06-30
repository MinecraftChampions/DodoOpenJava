package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelvoice;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 语音频道相关事件
 *
 * @author qscbm187531
 */
public abstract class AbstractChannelVoiceEvent extends Event {
    public AbstractChannelVoiceEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractChannelVoiceEvent() {
        this(false);
    }
}