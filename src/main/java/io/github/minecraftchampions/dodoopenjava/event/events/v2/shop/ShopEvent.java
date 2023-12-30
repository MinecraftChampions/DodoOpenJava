package io.github.minecraftchampions.dodoopenjava.event.events.v2.shop;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 商店相关事件
 */
public abstract class ShopEvent extends Event {
    public ShopEvent(boolean isAsync) {
        super(isAsync);
    }

    public ShopEvent() {
        this(false);
    }
}
