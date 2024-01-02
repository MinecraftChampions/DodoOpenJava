package io.github.minecraftchampions.dodoopenjava.message.card.component;

import io.github.minecraftchampions.dodoopenjava.message.card.enums.Style;
import lombok.NonNull;

/**
 * 倒计时组件
 */
public class CountdownComponent extends CardComponent {
    /**
     * 初始化
     *
     * @param style   显示样式
     * @param endTime 时间戳
     */
    public CountdownComponent(@NonNull Style style, long endTime) {
        jsonCard.put("type", "countdown");
        jsonCard.put("style", style.toString());
        jsonCard.put("endTime", endTime);
    }

    /**
     * 初始化
     *
     * @param style   显示样式
     * @param endTime 时间戳
     * @param title   标题
     */
    public CountdownComponent(@NonNull Style style, long endTime, String title) {
        jsonCard.put("type", "countdown");
        jsonCard.put("style", style.toString());
        jsonCard.put("endTime", endTime);
        jsonCard.put("title", title);
    }

    /**
     * 编辑显示样式
     *
     * @param style 样式
     */
    public void editStyle(@NonNull Style style) {
        jsonCard.put("style", style);
    }

    /**
     * 编辑结束时间戳
     *
     * @param endTime 时间戳
     */
    public void editCover(long endTime) {
        jsonCard.put("endTime", endTime);
    }

    /**
     * 编辑标题
     *
     * @param title 标题
     */
    public void editTitle(@NonNull String title) {
        jsonCard.put("title", title);
    }
}
