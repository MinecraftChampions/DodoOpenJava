package io.github.minecraftchampions.dodoopenjava.event.events.v2.integral;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 积分事件
 */
public abstract class IntegralEvent extends Event {
    public IntegralEvent(boolean isAsync) {
        super(isAsync);
    }

    public IntegralEvent() {
        this(false);
    }
}
