package io.github.minecraftchampions.dodoopenjava.event.events.v2.personal;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 私信事件
 */
public abstract class PersonalEvent extends Event {
    public PersonalEvent(boolean isAsync) {
        super(isAsync);
    }

    public PersonalEvent() {
        super(false);
    }
}
