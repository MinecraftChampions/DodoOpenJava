package io.github.minecraftchampions.dodoopenjava.event.events.v2.gift;

import io.github.minecraftchampions.dodoopenjava.event.AbstractEvent;

/**
 * 赠礼相关事件
 *
 * @author qscbm187531
 */
public abstract class AbstractGiftEvent extends AbstractEvent {
    public AbstractGiftEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractGiftEvent() {
        this(false);
    }
}