package io.github.minecraftchampions.dodoopenjava.event.events.v2.channelarticle;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 帖子频道相关事件
 *
 * @author qscbm187531
 */
public abstract class AbstractChannelArticleEvent extends Event {
    public AbstractChannelArticleEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractChannelArticleEvent() {
        this(false);
    }
}