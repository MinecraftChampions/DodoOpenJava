package io.github.minecraftchampions.dodoopenjava.message.card.enums;

/**
 * 倒计时显示样式
 *
 * @author qscbm187531
 */
public enum CountdownStyle {
    /**
     * 以天为单位
     */
    Day,
    /**
     * 以小时为单位
     */
    Hour;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
