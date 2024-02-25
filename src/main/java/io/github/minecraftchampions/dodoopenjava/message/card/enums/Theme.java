package io.github.minecraftchampions.dodoopenjava.message.card.enums;

/**
 * 卡片风格
 *
 * @author qscbm187531
 */
public enum Theme {
    /**
     * 灰色调
     */
    Grey,
    /**
     * 红色调
     */
    Red,
    /**
     * 橙色调
     */
    Orange,
    /**
     * 黄色调
     */
    Yellow,
    /**
     * 绿色调
     */
    Green,
    /**
     * 靛蓝色调
     */
    Indigo,
    /**
     * 蓝色调
     */
    Blue,
    /**
     * 粉色调
     */
    Purple,
    /**
     * 黑色调
     */
    Black,
    /**
     * 默认风格
     */
    Default;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}