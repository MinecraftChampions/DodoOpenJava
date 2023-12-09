package io.github.minecraftchampions.dodoopenjava.event.events.v2.gift;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 赠礼事件
 */
public abstract class GiftEvent extends Event {
    public GiftEvent(boolean isAsync) {
        super(isAsync);
    }

    public GiftEvent() {
        this(false);
    }
}
