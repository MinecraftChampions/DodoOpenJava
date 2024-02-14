package io.github.minecraftchampions.dodoopenjava.message.card.enums;

/**
 * 按钮颜色
 *
 * @author qscbm187531
 */
public enum ButtonColor {
    /**
     * 灰色
     */
    Grey,
    /**
     * 红色
     */
    Red,
    /**
     * 橙色
     */
    Orange,
    /**
     * 绿色
     */
    Green,
    /**
     * 蓝色
     */
    Blue,
    /**
     * 粉色
     */
    Purple,
    /**
     * 默认
     */
    Default;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
