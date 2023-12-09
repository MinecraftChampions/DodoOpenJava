package io.github.minecraftchampions.dodoopenjava.event.events.v2.member;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 成员相关事件
 */
public abstract class MemberEvent extends Event {
    public MemberEvent(boolean isAsync) {
        super(isAsync);
    }

    public MemberEvent() {
        this(false);
    }
}
