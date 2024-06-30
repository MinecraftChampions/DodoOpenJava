package io.github.minecraftchampions.dodoopenjava.event.events.v2.personal;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 私信相关事件
 *
 * @author qscbm187531
 */
public abstract class AbstractPersonalEvent extends Event {
    public AbstractPersonalEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractPersonalEvent() {
        this(false);
    }
}