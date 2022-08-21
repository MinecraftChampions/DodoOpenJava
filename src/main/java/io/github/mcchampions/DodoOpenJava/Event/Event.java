package io.github.mcchampions.DodoOpenJava.Event;

import java.util.EventObject;

/**
 * 事件源
 */
public class Event extends EventObject {
    private String jsontext;

    public String getParam() {
        return jsontext;
    }

    public Event(Object source, String jsontext) {
        super(source);
        this.jsontext = jsontext;
    }
}
