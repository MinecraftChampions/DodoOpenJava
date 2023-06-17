package io.github.minecraftchampions.dodoopenjava.card.component;

import io.github.minecraftchampions.dodoopenjava.card.enums.Style;

/**
 * 倒计时组件
 */
public class CountdownComponent extends Component {
    /**
     * 初始化
     * @param style 显示样式
     * @param endTime 时间戳
     */
    public CountdownComponent(Style style, long endTime) {
        jsonCard.put("type","countdown");
        jsonCard.put("style",style.toString());
        jsonCard.put("endTime",endTime);
    }

    /**
     * 初始化
     * @param style 显示样式
     * @param endTime 时间戳
     * @param title 标题
     */
    public CountdownComponent(Style style, long endTime,String title) {
        jsonCard.put("type","countdown");
        jsonCard.put("style",style.toString());
        jsonCard.put("endTime",endTime);
        jsonCard.put("title",title);
    }

    /**
     * 编辑显示样式
     * @param style 样式
     */
    public void editStyle(Style style) {
        jsonCard.put("style",style);
    }

    /**
     * 编辑结束时间戳
     * @param endTime 时间戳
     */
    public void editCover(long endTime) {
        jsonCard.put("endTime",endTime);
    }

    /**
     * 编辑标题
     * @param title 标题
     */
    public void editTitle(String title) {
        jsonCard.put("title",title);
    }
}
