package io.github.minecraftchampions.dodoopenjava.message.card.enums;

/**
 * 对齐方式
 *
 * @author qscbm187531
 */
public enum AlignType {
    /**
     * 右对齐
     */
    Right,
    /**
     * 左对齐
     */
    Left;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}