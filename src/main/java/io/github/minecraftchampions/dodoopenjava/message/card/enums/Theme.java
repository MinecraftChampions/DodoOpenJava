package io.github.minecraftchampions.dodoopenjava.message.card.enums;

/**
 * 卡片风格
 */
public enum Theme {
    Grey,
    Red,
    Orange,
    Yellow,
    Green,
    Indigo,
    Blue,
    Purple,
    Black,
    Default;

    public String toString() {
        return name().toLowerCase();
    }
}
