package io.github.mcchampions.DodoOpenJava.Event;

import com.alibaba.fastjson2.JSONObject;
import io.github.mcchampions.DodoOpenJava.Event.events.CardMessageButtonClickEvent;

import java.util.Vector;

/**
 * 事件的相关方法（包含监听器注册等）
 */
public class EventManage {
    private static Vector<Listener> listeners = new Vector<>();
    public void register(Listener l,String Authorization) {
        listeners.add(l);
        EventTrigger.main(Authorization);
    }
    public void notifyListeners(EventObject e) {
        for (Listener listener : listeners) {
            EventObject a = new CardMessageButtonClickEvent(new JSONObject());
            listener.event(e);
        }
    }
    protected void trigger(EventObject e) {
        notifyListeners(e);
    }
}
