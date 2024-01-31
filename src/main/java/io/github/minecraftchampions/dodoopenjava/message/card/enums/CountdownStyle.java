package io.github.minecraftchampions.dodoopenjava.message.card.enums;

/**
 * 倒计时显示样式
 */
public enum CountdownStyle {
    Day,
    Hour;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
