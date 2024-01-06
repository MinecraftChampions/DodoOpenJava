package io.github.minecraftchampions.dodoopenjava.event.events.v2.shop;

import io.github.minecraftchampions.dodoopenjava.event.AbstractEvent;

/**
 * 商店相关事件
 */
public abstract class AbstractShopEvent extends AbstractEvent {
    public AbstractShopEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractShopEvent() {
        this(false);
    }
}
