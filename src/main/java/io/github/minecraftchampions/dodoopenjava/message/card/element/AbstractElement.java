package io.github.minecraftchampions.dodoopenjava.message.card.element;

import org.json.JSONObject;

/**
 * 元素
 *
 * @author qscbm187531
 */
public abstract class AbstractElement {
    /**
     * 转换为JSON
     *
     * @return json
     */
    public abstract JSONObject toJsonObject();

    public abstract static class AbstractDataElement extends AbstractElement {
    }

    public abstract static class AbstractInteractiveElement extends AbstractElement {
    }
}