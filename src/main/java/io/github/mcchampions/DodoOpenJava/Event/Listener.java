package io.github.mcchampions.DodoOpenJava.Event;

import java.util.EventListener;

/**
 * 事件处理的接口
 */
public interface Listener extends EventListener {
    void event(EventObject e);
}
