package io.github.minecraftchampions.dodoopenjava.message;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public boolean isAllowNest() {
        return allowNest;
    }

    public void setAllowNest(boolean allowNest) {
        this.allowNest = allowNest;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
