package io.github.minecraftchampions.dodoopenjava.message;

import lombok.Getter;

/**
 * markdown组件
 */
@Getter
public class TextComponent {
    private String key;

    private String match;

    private boolean allowNest;

    private String attribute;

    TextComponent(String key, String match, boolean allowNest, String attribute) {
        this.key = key;
        this.match = match;
        this.allowNest = allowNest;
        this.attribute = attribute;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public void setAllowNest(boolean allowNest) {
        this.allowNest = allowNest;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
