package io.github.minecraftchampions.dodoopenjava.message.card.enums;

/**
 * 按钮颜色
 */
public enum ButtonColor {
    Grey,
    Red,
    Orange,
    Green,
    Blue,
    Purple,
    Default;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
