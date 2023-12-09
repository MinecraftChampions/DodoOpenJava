package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelarticle;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 帖子频道事件
 */
public abstract class ChannelArticleEvent extends Event {
    public ChannelArticleEvent(boolean isAsync) {
        super(isAsync);
    }

    public ChannelArticleEvent() {
        this(false);
    }
}
