package io.github.minecraftchampions.dodoopenjava.message.card.element;

import org.json.JSONObject;

/**
 * 元素
 */
public abstract class Element {
    public abstract JSONObject toJSONObject();

    public abstract static class DataElement extends Element {

    }

    public abstract static class InteractiveElement extends Element {

    }
}
