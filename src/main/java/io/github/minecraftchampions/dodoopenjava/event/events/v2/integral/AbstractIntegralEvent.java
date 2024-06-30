package io.github.minecraftchampions.dodoopenjava.event.events.v2.integral;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 积分相关事件
 *
 * @author qscbm187531
 */
public abstract class AbstractIntegralEvent extends Event {
    public AbstractIntegralEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractIntegralEvent() {
        this(false);
    }
}