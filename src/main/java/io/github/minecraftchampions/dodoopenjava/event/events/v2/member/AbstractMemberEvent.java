package io.github.minecraftchampions.dodoopenjava.event.events.v2.member;

import io.github.minecraftchampions.dodoopenjava.event.Event;

/**
 * 成员相关事件
 *
 * @author qscbm187531
 */
public abstract class AbstractMemberEvent extends Event {
    public AbstractMemberEvent(boolean isAsync) {
        super(isAsync);
    }

    public AbstractMemberEvent() {
        this(false);
    }
}